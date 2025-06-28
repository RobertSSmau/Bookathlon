package com.bookathlon.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookathlon.entities.Challenge;

public interface ChallengeRepository extends JpaRepository<Challenge, Long> {

    List<Challenge> findByAutoreId(Long autoreId);

    List<Challenge> findByDestinatarioId(Long destinatarioId);
    
    @Query(value = """
            SELECT * 
            FROM "better-mockup-schema-2".challenge 
            WHERE "destinatario_id" = :utenteId 
            ORDER BY "data_creazione" DESC
        """, nativeQuery = true)
        List<Challenge> queryChallengeRicevute(@Param("utenteId") Long utenteId);

}



