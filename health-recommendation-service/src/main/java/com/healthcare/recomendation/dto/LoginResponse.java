package com.healthcare.recomendation.dto;

public class LoginResponse {
	
	private String emailId;
    private String role;
    private String message;

    public LoginResponse(String emailId, String role, String message) {
        this.emailId = emailId;
        this.role = role;
        this.message = message;
    }

    public String getEmailId() { return emailId; }
    public String getRole() { return role; }
    public String getMessage() { return message; }

}
