package com.bookathlon.controller;


import java.util.ArrayList;
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
import com.bookathlon.entities.ChallengeRisp;
import com.bookathlon.entities.Libro;
import com.bookathlon.entities.Utente;
import com.bookathlon.repos.UtenteRepository;
import com.bookathlon.service.ChallengeRispService;
import com.bookathlon.service.ChallengeService;
import com.bookathlon.service.ClassificaService;
import com.bookathlon.service.LibroService;


@Controller
public class ChallengeController {

	@Autowired
	private ClassificaService classificaService;
	
	@Autowired
	private UtenteRepository utRepo;//errore, il controller non deve utilizzare le repo
	
	@Autowired
	private ChallengeRispService rispServ;
	
	@Autowired
	private ChallengeService challServ;
	
	@Autowired
	private LibroService libroService;
	
	
	
	@GetMapping("/challenge")
    public String challengePage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
		
		Utente utente = utRepo.findByUsername(userDetails.getUsername());

		List<Challenge> inviate = challServ.getChallengeInviate(utente.getId());
		List<Challenge> ricevute = challServ.getChallengeRicevute(utente.getId());
		
		

		List<Long> ids = new ArrayList<>();
		for (Challenge c : inviate) {
		    ids.add(c.getId());}
		    for (Challenge c : ricevute) {
		        ids.add(c.getId());
		}

		List<ChallengeRisp> risposte = rispServ.trovaRispId(ids);

		Map<Long, ChallengeRisp> rispmap = new HashMap<>();
		for (ChallengeRisp r : risposte) {
		    rispmap.put(r.getChallengeId(), r);
		}
		
		Map<Long, Libro> libriChallenge = new HashMap<>();
		for (Challenge c : inviate) {
		    libriChallenge.put(c.getId(), libroService.getLibroById(c.getLibroId()));
		}
		for (Challenge c : ricevute) {
		    libriChallenge.put(c.getId(), libroService.getLibroById(c.getLibroId()));
		}
	    
		model.addAttribute("challengeRisposte", rispmap);
		model.addAttribute("classificaGlobale", classificaService.getClassificaGlobale());
		model.addAttribute("classificaAmici", classificaService.getClassificaAmici(userDetails));
		model.addAttribute("challengeRicevute", ricevute);
		model.addAttribute("challengeInviate", inviate);
		model.addAttribute("libriChallenge", libriChallenge);

        return "challenge";
    }
	
	
	
}
	

