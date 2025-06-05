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
	
	@GetMapping("/register")
	public String mostraFormRegistrazione(Model model) {
		model.addAttribute("utente", new Utente());
        return "register";
	}
	
	
	@PostMapping("/register")
public String processRegister(@Valid @ModelAttribute("utente") Utente utente,
                              BindingResult result,
                              Model model) {

    if (utenteService.existsByEmail(utente.getEmail())) {
        result.rejectValue("email", "error.utente", "Email già registrata");
    }

    if (utenteService.existsByUsername(utente.getUsername())) {
        result.rejectValue("username", "error.utente", "Username già esistente");
    }

    if (result.hasErrors()) {
        return "register";
    }

     utente.setRuolo("USER");
    utenteService.addUtente(utente);
    return "redirect:/login";

}
	 
	 @GetMapping("/login")
	    public String showLoginForm() {
	        return "login";
	 }
}
