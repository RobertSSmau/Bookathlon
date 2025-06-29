package com.bookathlon.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.bookathlon.entities.Challenge;
import com.bookathlon.repos.ChallengeRepository;


@Service
public class ChallengeServiceImpl implements ChallengeService {

	@Autowired
	private ChallengeRepository repo;

 	@Override
    	public Challenge salvaChallenge(Challenge challenge) {
        
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
	
	//si crea un duplicato inutile nel DB
	@Override
	public void eliminaDuplicato(Long id) {
	    repo.deleteById(id);
	}
	
	@Override
	public List<Challenge> getChallAttive(Long destinatarioId) {
	    return repo.queryChallAttive(destinatarioId);
	}
	
}
