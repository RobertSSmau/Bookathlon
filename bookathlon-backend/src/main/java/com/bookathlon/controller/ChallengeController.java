package com.bookathlon.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookathlon.entities.Challenge;
import com.bookathlon.entities.Utente;
import com.bookathlon.repos.ChallengeRepository;
import com.bookathlon.repos.UtenteRepository;
import com.bookathlon.service.ClassificaService;


@Controller
public class ChallengeController {

	@Autowired
	private ClassificaService classificaService;
	
	@Autowired
	private ChallengeRepository challRepo;
	
	@Autowired
	private UtenteRepository utRepo;
	
	@GetMapping("/challenge")
    public String challengePage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		Utente utente = utRepo.findByUsername(userDetails.getUsername());

	    List<Challenge> ricevute = challRepo.findByDestinatarioId(utente.getId());
	    List<Challenge> inviate = challRepo.findByAutoreId(utente.getId());
        model.addAttribute("classificaGlobale", classificaService.getClassificaGlobale());
        model.addAttribute("classificaAmici", classificaService.getClassificaAmici(userDetails));
        model.addAttribute("challengeRicevute", ricevute);
        model.addAttribute("challengeInviate", inviate);

        return "challenge";
    }
	
	
	
}
	

