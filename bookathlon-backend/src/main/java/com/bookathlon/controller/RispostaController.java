package com.bookathlon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookathlon.entities.Challenge;
import com.bookathlon.entities.ChallengeRisp;
import com.bookathlon.entities.Utente;
import com.bookathlon.repos.UtenteRepository;
import com.bookathlon.service.ChallengeRispService;
import com.bookathlon.service.ChallengeService;

@Controller
@RequestMapping("/challenge")
public class RispostaController {

	@Autowired
    private ChallengeService challServ;

    @Autowired
    private ChallengeRispService challRisp;

    @Autowired
    private UtenteRepository uRepo;//ERRORE, il controller non deve utilizzare le repo
    
    @GetMapping("/risposta-quiz")
    public String mostraQuiz(@RequestParam Long id, Model model) {

        Challenge c = passaQuizvalido(id);
        if (c == null) 
        	return "redirect:/challenge";
        
        model.addAttribute("challenge", c);
        
        return "risposta-quiz";
    }
    
    @PostMapping("/risposta-quiz")
    public String rispostaQuiz(@RequestParam Long challengeId,
            					@RequestParam String risposta,
            					@AuthenticationPrincipal UserDetails user) {
    	
    	Challenge c = passaQuizvalido(challengeId);
    	if (c == null) 
    		return "redirect:/challenge";
    	
    	Utente u = uRepo.findByUsername(user.getUsername());
    	
    	ChallengeRisp r = new ChallengeRisp();
    	
    	r.setChallengeId(challengeId);
        r.setUtenteId(u.getId());
        r.setRisposta(risposta);
    	
        boolean corretta = risposta.equalsIgnoreCase(c.getRispostaCorretta());
        r.setCorretta(corretta);
        challRisp.salva(r);

        c.setStato("RISPOSTA_INVIATA");
        challServ.salvaChallenge(c);

        if (corretta) {
        	uRepo.incrementaScore(u.getId());
        }
        
        return "redirect:/challenge/esito?id=" 
        + challengeId 
        + "&corretta=" 
        + corretta;
    	
    }
    
    
    @GetMapping("/esito")
    public String esito(@RequestParam boolean corretta,
                                  @RequestParam Long id,
                                  Model model) {
        model.addAttribute("corretta", corretta);
        model.addAttribute("id", id);
        return "esito";
    }

    private Challenge passaQuizvalido(Long id) {

        Challenge quiz = challServ.getById(id);

        return quiz;
    }
    
    
}
