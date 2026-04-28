package com.healthcare.recomendation.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.healthcare.recomendation.exception.ApplicationException;
import com.healthcare.recomendation.exception.DatabaseOperationException;
import com.healthcare.recomendation.models.RiskAssesment;
import com.healthcare.recomendation.models.UserDetails;
import com.healthcare.recomendation.models.UserRecomendation;
import com.healthcare.recomendation.models.Users;
import com.healthcare.recomendation.repository.RiskScoreRepository;
import com.healthcare.recomendation.repository.UserDetailsRepository;
import com.healthcare.recomendation.repository.UserRecommendationRepository;
import com.healthcare.recomendation.repository.UserRepository;

@Service
public class UserDetailsService {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private RiskScoreRepository riskScoreRepository;

	@Autowired
	private UserRecommendationRepository userRecommendationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Transactional
	public UserDetails saveUserDetails(UserDetails userDetails) {
		
		try {
			userDetails.setRiskRecommendation(assessHealthRisk(userDetails));
			userDetails.setPersonalizedRecommendation(getRecommendationsByDiseases(userDetails.getMedicalHistory()));
			persistToUserTable(userDetails);
			return userDetailsRepository.save(userDetails);
		}
		catch (DataAccessException e) {
			 throw new DatabaseOperationException("Error saving user details to the database", e);
		}
		catch (Exception e) {
            throw new ApplicationException("Unexpected error occurred while saving user details", e);
        }

		
	}

	public Optional<UserDetails> findByEmailAddress(UserDetails userDetails) {
		return userDetailsRepository.findByEmailAddress(userDetails.getEmailAddress());
	}

	public String assessHealthRisk(UserDetails userDetails) {
		Integer riskScore = 0;
		
		try {
			
			Integer userAge = Integer.parseInt(userDetails.getAge());

			if (userAge > 60) {
				riskScore += 2;

			} else if (userAge > 40) {
				riskScore += 1;
			}

			if (userDetails.getMedicalHistory().contains("heart disease")
					|| userDetails.getMedicalHistory().contains("diabetes")) {
				riskScore += 3;
			}

			if (userDetails.getLifeStyleHabit().contains("smoking")) {
				riskScore += 2;
			}

			if (userDetails.getLifeStyleHabit().contains("alcohol")) {
				riskScore += 1;
			}

			Optional<RiskAssesment> risks = riskScoreRepository.findRecommendationByRiskScore(riskScore);

			if (risks.isPresent()) {
				RiskAssesment riskAssesment = risks.get();
				return riskAssesment.getRecommendation();

			} else
				return "";
			
		} catch (DataAccessException e) {
			 throw new DatabaseOperationException("Error saving user details to the database", e);
		}
		catch (Exception e) {
           throw new ApplicationException("Unexpected error occurrekd while saving user details", e);
       }

		

	}

	public String getRecommendationsByDiseases(String diseases) {
		try {
			List<String> diseaseList = Arrays.asList(diseases.split("\\s*,\\s*"));
			List<UserRecomendation> recommendations = userRecommendationRepository.findByDiseases(diseaseList);

			return recommendations.stream().map(UserRecomendation::getRecommendation).collect(Collectors.joining(", "));

		} catch (DataAccessException e) {
			 throw new DatabaseOperationException("Error saving user details to the database", e);
		}
		catch (Exception e) {
          throw new ApplicationException("Unexpected error occurrekd while saving user details", e);
      }

	}
	
	public UserDetails updateUserDetails(UserDetails userDetails) {
		return userDetailsRepository.save(userDetails);
	}
	
	@Transactional
	public void persistToUserTable(UserDetails userDetails) {
		Users usersData = new Users();
		try {
			usersData.setEmailId(userDetails.getEmailAddress());
			usersData.setPassword(userDetails.getPassword());
			usersData.setRoles("user");
			userRepository.save(usersData);
		} catch (DataAccessException e) {
			 throw new DatabaseOperationException("Error saving user details to the database", e);
		}
		catch (Exception e) {
         throw new ApplicationException("Unexpected error occurrekd while saving user details", e);
     }
		
		
	}

}
