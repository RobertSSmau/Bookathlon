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
import com.bookathlon.entities.Utente;
import com.bookathlon.repos.UtenteRepository;
import com.bookathlon.service.ChallengeService;
import com.bookathlon.service.LibroService;

@Controller
@RequestMapping("/challenge")
public class CreazioneChallengeController {

	@Autowired
	private ChallengeService challengeService;
	
	@Autowired
	private UtenteRepository utenteRepo;
	
	@Autowired
	private LibroService libroService;
	
	 @GetMapping("/nuova")
	    public String mostraFormChallenge(@RequestParam Long libroId,
	                                      @RequestParam String tipo,
	                                      Model model,
	                                      @AuthenticationPrincipal UserDetails userDetails) {
	        model.addAttribute("libro", libroService.getLibroById(libroId));
	        model.addAttribute("tipo", tipo);
	        if (tipo.equalsIgnoreCase("QUIZ")) {
	            return "form-challenge-quiz";
	        } else {
	            return "form-challenge-aperta";
	        }
	    }
	 
		 @PostMapping("/crea")
		 public String creaChallenge(@RequestParam Long libroId,
		                             @RequestParam String tipo,
		                             @RequestParam String domanda,
		                             @RequestParam(required = false) String opzioneA,
		                             @RequestParam(required = false) String opzioneB,
		                             @RequestParam(required = false) String opzioneC,
		                             @RequestParam(required = false) String rispostaCorretta,
		                             @AuthenticationPrincipal UserDetails userDetails,
		                             Model model) {
	
		     Utente autore = utenteRepo.findByUsername(userDetails.getUsername());
	
		     Challenge newchall = new Challenge();
		     newchall.setLibroId(libroId);
		     newchall.setAutoreId(autore.getId());
		     newchall.setTipo(tipo.toUpperCase());
		     newchall.setDomanda(domanda);
		     newchall.setStato("PENDING");
		     newchall.setApprovata(null);
	
		     if (tipo.equalsIgnoreCase("QUIZ")) {
		    	 newchall.setOpzioneA(opzioneA);
		    	 newchall.setOpzioneB(opzioneB);
		    	 newchall.setOpzioneC(opzioneC);
		         newchall.setRispostaCorretta(rispostaCorretta);
		     }
	
		     Challenge salvata = challengeService.salva(newchall);
	
		     return "redirect:/challenge/seleziona-amici?id=" + salvata.getId();
		 }
	 
}
