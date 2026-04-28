package com.healthcare.recomendation.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserRecomendation {
	
	
	
	@Id
    private Long  id;
	
	private String disease;
	
	private String recommendation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	@Override
	public String toString() {
		return "UserRecomendation [id=" + id + ", disease=" + disease + ", recommendation=" + recommendation + "]";
	}
	
	
		
	

}
