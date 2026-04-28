package com.healthcare.recomendation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.recomendation.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
	
	Optional<Users> findByEmailId(String emailId);

}
