package com.bookathlon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookathlon.entities.ChallengeRisp;

import com.bookathlon.repos.ChallengeRispRepository;

@Service
public class ChallengeRispServiceImpl implements ChallengeRispService {

    @Autowired
    private ChallengeRispRepository repo;

    @Override
    public ChallengeRisp salva(ChallengeRisp risposta) {
        return repo.save(risposta);
    }

    @Override
    public List<ChallengeRisp> getByChallengeId(Long challengeId) {
        return repo.findByChallengeId(challengeId);
    }
    
    @Override
    public List<ChallengeRisp> trovaRisposte(Long utenteId) {
        return repo.findByUtenteId(utenteId);
    }
    
    @Override
    public List<ChallengeRisp> trovaRispId(List<Long> ids) {
        return repo.trovaChallId(ids);
    }
}
