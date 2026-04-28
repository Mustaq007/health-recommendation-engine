package com.healthcare.recomendation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.recomendation.models.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long>{
	
	 Optional<UserDetails> findByEmailAddress(String emailAddress);
	 
	 
	 
	 List<UserDetails> findByRequireConsultationTrue();
	 
	 
	 

}
