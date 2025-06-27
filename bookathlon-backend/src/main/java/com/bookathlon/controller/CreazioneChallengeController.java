package com.bookathlon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookathlon.service.LibroService;

@Controller
@RequestMapping("/challenge")
public class CreazioneChallengeController {

	@Autowired
	private LibroService libroService;
	
	 @GetMapping("/nuova")
	    public String mostraFormChallenge(@RequestParam Long libroId,
	                                      @RequestParam String tipo,
	                                      Model model,
	                                      @AuthenticationPrincipal UserDetails userDetails) {
	        model.addAttribute("libro", libroService.getLibroById(libroId));
	        model.addAttribute("tipo", tipo);
	        return tipo.equalsIgnoreCase("QUIZ") ? "form-challenge-quiz" : "form-challenge-aperta";
	    }
}
