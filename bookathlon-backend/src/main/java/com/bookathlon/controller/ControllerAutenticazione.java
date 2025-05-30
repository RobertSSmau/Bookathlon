package com.bookathlon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookathlon.entities.Utente;
import com.bookathlon.service.UtenteService;

@Controller
public class ControllerAutenticazione {
	
	@Autowired
	private UtenteService utenteService;
	
	//WARNING!!! Mettere una mail gia registrata manda in errore il DB , va gestito più elegantemente!!
	@GetMapping("/register")
	public String mostraFormRegistrazione(Model model) {
		model.addAttribute("utente", new Utente());
        return "register";
	}
	
	
	 @PostMapping("/register")
	    public String processRegister(@ModelAttribute Utente utente) {
	        utente.setRuolo("USER");
	        utenteService.addUtente(utente);
	        return "redirect:/login";
	 }
	 
	 @GetMapping("/login")
	    public String showLoginForm() {
	        return "login";
	 }
}
