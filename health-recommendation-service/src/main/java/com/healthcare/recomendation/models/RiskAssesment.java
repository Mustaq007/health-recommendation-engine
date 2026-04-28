package com.healthcare.recomendation.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "risk_assessment")
public class RiskAssesment {
	
	
	@Id
    private Long  id;
	
	 private Integer minScore;
	 
	 private Integer maxScore;
	    	
	private String recommendation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getMinScore() {
		return minScore;
	}

	public void setMinScore(Integer minScore) {
		this.minScore = minScore;
	}

	public Integer getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(Integer maxScore) {
		this.maxScore = maxScore;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

}
