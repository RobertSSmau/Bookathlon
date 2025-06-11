package com.bookathlon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookathlon.entities.LibreriaUtente;
import com.bookathlon.entities.Utente;
import com.bookathlon.repos.UtenteRepository;
import com.bookathlon.service.LibreriaUtenteService;

@Controller
@RequestMapping("/libreria")
public class LibreriaController {

	@Autowired
    private LibreriaUtenteService libreriaService;
	
	@Autowired
	private UtenteRepository utenteRepo;

	 @GetMapping
	    public String mostraLibreria(Model m, @AuthenticationPrincipal UserDetails userDetails) {
	        String username = userDetails.getUsername();
	        Utente utente = utenteRepo.findByUsername(username);
	        Long utenteId = utente.getId();

	        List<LibreriaUtente> letti = libreriaService.getLibriByStato(utenteId, "LETTO");
	        List<LibreriaUtente> daLeggere = libreriaService.getLibriByStato(utenteId, "DA_LEGGERE");

	        m.addAttribute("letti", letti);
	        m.addAttribute("daLeggere", daLeggere);
	        return "libreria";
	    }
	 
	  @PostMapping("/aggiungi")
	    public String aggiungiLibro(
	            @RequestParam Long libroId,
	            @RequestParam String stato,
	            @AuthenticationPrincipal UserDetails userDetails) {

	        String username = userDetails.getUsername();
	        Utente utente = utenteRepo.findByUsername(username);

	        libreriaService.aggiungiLibro(utente.getId(), libroId, stato);

	        return "redirect:/libreria";
	  }
	  
	  @PostMapping("/rimuovi")
	  public String rimuoviLibro(
		        @RequestParam Long libroId,
		        @AuthenticationPrincipal UserDetails userDetails) {

		    String username = userDetails.getUsername();
		    Utente utente = utenteRepo.findByUsername(username);

		    libreriaService.rimuoviLibro(utente.getId(), libroId);

		    return "redirect:/libreria";
	  }
	 
}
