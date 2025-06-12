package com.bookathlon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookathlon.entities.Utente;
import com.bookathlon.service.UtenteService;

import jakarta.validation.Valid;

@Controller
public class ControllerAutenticazione {
	
	@Autowired
	private UtenteService utenteService;
    /**
     * Questo metodo mostra il form di registrazione utente.
     * Prepara un nuovo oggetto Utente da associare al form.
     */
	
	@GetMapping("/register")
	public String mostraFormRegistrazione(Model model) {
		model.addAttribute("utente", new Utente());
        return "register";
	}
	    /**
     * Questo metodo processa la richiesta di registrazione di un nuovo utente.
     * Valida i dati dell'utente, verifica l'unicità di email e username,
     * e salva l'utente nel sistema.
     */
	
	@PostMapping("/register")
    // Verifica se l'email è già registrata
	public String processRegister(@Valid @ModelAttribute("utente") Utente utente,
                              BindingResult result,
                              Model model) {

    if (utenteService.existsByEmail(utente.getEmail())) {
         // Verifica se un utente con una determinata email esiste già nel sistema. 

        result.rejectValue("email", "error.utente", "Email già registrata");
    }

    if (utenteService.existsByUsername(utente.getUsername())) {
        // Verifica se un utente con un determinato username esiste già nel sistema. 
        result.rejectValue("username", "error.utente", "Username già esistente");
    }

    if (result.hasErrors()) {

        return "register";
    }

     utente.setRuolo("USER");
    utenteService.addUtente(utente);
    return "redirect:/login";
/**
     * Questo metodo mostra il form di login.
     *
     * @return Il nome della vista "login".
     */
}
	 
	 @GetMapping("/login")
	    public String showLoginForm() {
	        return "login";
	 }
}