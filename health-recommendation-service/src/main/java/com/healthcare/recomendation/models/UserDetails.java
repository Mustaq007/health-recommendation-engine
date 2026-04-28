package com.healthcare.recomendation.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Entity
@Table(name = "user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Date of Birth is required")
    @Column(name = "dob", nullable = false)
    @Past(message = "Date of birth must be a past date")
    private LocalDate dob;
    
    @NotBlank(message = "Contact number is required")
    @javax.validation.constraints.Pattern(regexp = "\\d{10}", message = "Contact number must be a 10-digit number")
    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    
    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
    @Column(name = "address", nullable = false)
    private String address;

    
    @NotBlank(message = "Gender is required")
    @Column(name = "gender", nullable = false)
    private String gender;

    @NotBlank(message = "Email address is required")
    @Email(message = "Invalid email format")
    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    
    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be at least 1")
    @Max(value = 120, message = "Age must be at most 120")
    @Column(name = "age", nullable = false)
    private String age;

    
    @NotBlank(message = "Medical history is required")
    @Column(name = "medical_history", nullable = false)
    private String medicalHistory;

    @Column(name = "risk_recommendation")
    private String riskRecommendation;

    @Column(name = "personalized_recommendation")
    private String personalizedRecommendation;

    @Column(name = "doctors_recommendation")
    private String doctorsRecommendation;
    
    @Column(name = "require_consultation")
    private Boolean requireConsultation;
    
    @Transient
    private String password;
    
    @NotBlank(message = "Lifestyle habit is required")
    @Column(name = "life_style")
    private String lifeStyleHabit;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getRiskRecommendation() {
        return riskRecommendation;
    }

    public void setRiskRecommendation(String riskRecommendation) {
        this.riskRecommendation = riskRecommendation;
    }

    public String getPersonalizedRecommendation() {
        return personalizedRecommendation;
    }

    public void setPersonalizedRecommendation(String personalizedRecommendation) {
        this.personalizedRecommendation = personalizedRecommendation;
    }

    public String getDoctorsRecommendation() {
        return doctorsRecommendation;
    }

    public void setDoctorsRecommendation(String doctorsRecommendation) {
        this.doctorsRecommendation = doctorsRecommendation;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLifeStyleHabit() {
		return lifeStyleHabit;
	}

	public void setLifeStyleHabit(String lifeStyleHabit) {
		this.lifeStyleHabit = lifeStyleHabit;
	}

	public Boolean getRequireConsultation() {
		return requireConsultation;
	}

	public void setRequireConsultation(Boolean requireConsultation) {
		this.requireConsultation = requireConsultation;
	}
	
	 
    
}
