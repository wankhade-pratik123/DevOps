package com.bs.stockmasterapi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bs.stockmasterapi.model.User;
import com.bs.stockmasterapi.model.VerificationToken;

@Repository
public interface VarificationTokenRepository extends JpaRepository<VerificationToken, Long> {

	Optional<VerificationToken> findByToken(String token);
	
	Optional<VerificationToken> findByUser(User user);
}
