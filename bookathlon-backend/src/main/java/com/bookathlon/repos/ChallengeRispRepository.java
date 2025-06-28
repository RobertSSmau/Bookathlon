package com.bookathlon.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookathlon.entities.ChallengeRisp;

public interface ChallengeRispRepository extends JpaRepository<ChallengeRisp, Long> {

    List<ChallengeRisp> findByChallengeId(Long challengeId);
    
    List<ChallengeRisp> findByUtenteId(Long utenteId);
}


