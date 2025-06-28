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
    private UtenteRepository uRepo;
    
    @GetMapping("/risposta-quiz")
    public String mostraQuiz(@RequestParam Long id, Model model) {

        Challenge c = passaQuizvalido(id);
        if (c == null) 
        	return "redirect:/challenge";
        
        model.addAttribute("challenge", c);
        
        return "rispondi-quiz";
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
            u.setScore(u.getScore() + 1);
            uRepo.save(u);
        }
        
    	return "redirect:/challenge";
    	
    }
    
    @GetMapping("/risposta-aperta")
    public String mostraAperta(@RequestParam Long id, 
    							Model model) {
    	
    	Challenge c = challServ.getById(id);
    	
    	model.addAttribute("challenge", c);
    	
    	return "risposta-aperta";
    }
    
    @PostMapping("/rispondi-aperta")
    public String inviaDomandaAperta(@RequestParam Long challengeId,
                                     @RequestParam String risposta,
                                     @AuthenticationPrincipal UserDetails user) {
    	
    	Challenge c = passaDomandavalida(challengeId);
        if (c == null) return "redirect:/challenge";

        Utente u = uRepo.findByUsername(user.getUsername());

        ChallengeRisp r = new ChallengeRisp();
        r.setChallengeId(challengeId);
        r.setUtenteId(u.getId());
        r.setRisposta(risposta);	
        r.setValutata(false);//da valutrare	
        r.setApprovata(null);//in attesa di approvazione

        challRisp.salva(r);
        c.setStato("RISPOSTA_INVIATA");
        challServ.salvaChallenge(c);
    	
    	return "redirect:/challenge";
    }

    private Challenge passaQuizvalido(Long id) {

        Challenge quiz = challServ.getById(id);

        if (quiz == null) 
        	return null;

        if (!"QUIZ".
        		equalsIgnoreCase(quiz.getTipo())) 
        	return null;

        return quiz;
    }
    
    private Challenge passaDomandavalida(Long id) {
    	
       Challenge dom = challServ.getById(id);
       
        if (dom == null) 
        	return null;
        
        if (!"APERTO".
        		equalsIgnoreCase(dom.getTipo())) 
        	return null;
        
        return dom;
    }
    
}
