package com.bookathlon.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookathlon.entities.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    List<Challenge> findByAutoreId(Long autoreId);

    List<Challenge> findByDestinatarioId(Long destinatarioId);
}


