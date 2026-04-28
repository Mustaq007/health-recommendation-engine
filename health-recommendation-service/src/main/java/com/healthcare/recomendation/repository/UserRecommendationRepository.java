package com.healthcare.recomendation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthcare.recomendation.models.UserRecomendation;

@Repository
public interface UserRecommendationRepository extends JpaRepository<UserRecomendation, Long>{
	
	
	@Query("SELECT u FROM UserRecomendation u WHERE u.disease IN :diseases")
    List<UserRecomendation> findByDiseases(@Param("diseases") List<String> diseases);

}
