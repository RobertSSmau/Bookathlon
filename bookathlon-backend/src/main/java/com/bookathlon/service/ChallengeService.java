package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.Challenge;

public interface ChallengeService {

	Challenge salvaChallenge(Challenge challenge);
	
	//schifo
	void eliminaDuplicato(Long id);

    List<Challenge> getChallengeInviate(Long autoreId);

    List<Challenge> getChallengeRicevute(Long destinatarioId);

    Challenge getById(Long id);
    
    
	
}
