package com.bookathlon.service;

import java.util.List;
import java.util.Optional;

import com.bookathlon.entities.ChallengeRisp;

public interface ChallengeRispService {

	ChallengeRisp salva(ChallengeRisp risposta);

    List<ChallengeRisp> getByChallengeId(Long challengeId);
    
    List<ChallengeRisp> trovaRisposte(Long utenteId);
   
	
}
