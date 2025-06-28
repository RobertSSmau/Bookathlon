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
        Challenge c = challServ.getById(id);
     
        
        model.addAttribute("challenge", c);
        
        return "rispondi-quiz";
    }
    
    @PostMapping("/risposta-quiz")
    public String rispostaQuiz(@RequestParam Long challengeId,
            					@RequestParam String risposta,
            					@AuthenticationPrincipal UserDetails user) {
    	
    	
    	return "redirect:/challenge";
    }
    
    @GetMapping("/risposta-aperta")
    public String mostraAperta(@RequestParam Long id, Model model) {
    	
    	Challenge c = challServ.getById(id);
    	
    	model.addAttribute("challenge", c);
    	
    	return "risposta-aperta";
    }
    
    @PostMapping("/rispondi-aperta")
    public String inviaDomandaAperta(@RequestParam Long challengeId,
                                     @RequestParam String risposta,
                                     @AuthenticationPrincipal UserDetails user) {
    	
    	return "redirect:/challenge";
    }

}
