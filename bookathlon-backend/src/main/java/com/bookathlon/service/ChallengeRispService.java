package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.ChallengeRisp;

public interface ChallengeRispService {

	ChallengeRisp salva(ChallengeRisp risposta);
   
    List<ChallengeRisp> trovaRispId(List<Long> ids);
    
}
