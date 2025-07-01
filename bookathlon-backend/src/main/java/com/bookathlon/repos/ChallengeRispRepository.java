package com.bookathlon.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bookathlon.entities.ChallengeRisp;

public interface ChallengeRispRepository extends JpaRepository<ChallengeRisp, Long> {

    List<ChallengeRisp> findByChallengeId(Long challengeId);
    
    List<ChallengeRisp> findByUtenteId(Long utenteId);
    
    @Query(value = """
    	    SELECT * 
    	    FROM "better-mockup-schema-2".challenge_risposta 
    	    WHERE challenge_id IN (:ids)
    	""", nativeQuery = true)
    	List<ChallengeRisp> trovaChallId(@Param("ids") List<Long> ids);
}



