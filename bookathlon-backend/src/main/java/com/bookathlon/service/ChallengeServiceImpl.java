package com.bookathlon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookathlon.entities.Challenge;

@Service
public class ChallengeServiceImpl implements ChallengeService {

	@Autowired
	private ChallengeRepository repo;

 	@Override
    	public Challenge salva(Challenge challenge) {
        
			return repo.save(challenge);
    }

	@Override
	public List<Challenge> getChallengeInviate(Long autoreId) {

		return repo.findByAutoreId(autoreId);
	}

	@Override
    public List<Challenge> getChallengeRicevute(Long destinatarioId) {
        return repo.findByDestinatarioId(destinatarioId);
    }
	

	@Override
    public Challenge getById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
