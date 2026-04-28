package com.healthcare.recomendation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.healthcare.recomendation.models.RiskAssesment;

@Repository
public interface RiskScoreRepository extends JpaRepository<RiskAssesment, Long>{
	
	
	@Query("SELECT r FROM RiskAssesment r WHERE :score BETWEEN r.minScore AND r.maxScore")
	 Optional<RiskAssesment> findRecommendationByRiskScore(@Param("score") Integer score);

}
