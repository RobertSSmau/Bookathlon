package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.ChallengeRisp;

public interface ChallengeRispService {

	ChallengeRisp salva(ChallengeRisp risposta);

    List<ChallengeRisp> getByChallengeId(Long challengeId);
    
    List<ChallengeRisp> trovaRisposte(Long utenteId);
   
    List<ChallengeRisp> trovaRispId(List<Long> ids);
    
}
