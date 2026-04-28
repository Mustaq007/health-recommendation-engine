package com.healthcare.recomendation.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.recomendation.models.UserDetails;
import com.healthcare.recomendation.repository.UserDetailsRepository;
import com.healthcare.recomendation.service.UserDetailsService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserProfileController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private UserDetailsRepository userDetails;

	// below api is for admin screen to save all the user details
	@PostMapping("/save")
	public ResponseEntity<?> saveUser(@Valid @RequestBody UserDetails userDetails) {

		try {

			Optional<UserDetails> existingUser = userDetailsRepository
					.findByEmailAddress(userDetails.getEmailAddress());

			if (existingUser.isPresent()) {
				return ResponseEntity.badRequest().body("Error: Email already registered!");
			}
			UserDetails savedUser = userDetailsService.saveUserDetails(userDetails);

			return ResponseEntity.ok(savedUser);

		} catch (Exception e) {
			System.out.println("Inside Exception Block::::::::::::::::");
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	//below api works fetch user details on user screen
	@GetMapping("/details/{email}")
	public ResponseEntity<UserDetails> getUserDetails(@PathVariable String email) {
		Optional<UserDetails> user = userDetails.findByEmailAddress(email);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	//below api is for admin screen to update user details
	@PatchMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UserDetails userDetails) {
		UserDetails savedUser = userDetailsService.updateUserDetails(userDetails);
		return ResponseEntity.ok(savedUser);
	}

	//below api is for doctors screen to get all the consulted user
	@GetMapping("/details")
	public List<UserDetails> getAllUserDetails() {
		List<UserDetails> user = userDetails.findByRequireConsultationTrue();
		return user;
	}

}
