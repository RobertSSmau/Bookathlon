package com.bookathlon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bookathlon.entities.Utente;
import com.bookathlon.repos.UtenteRepository;
import com.bookathlon.service.AmiciziaService;

@Controller
public class ChallengeController {

	@Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private AmiciziaService amiciziaService;
	
    @GetMapping("/challenge")
    public String challengePage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Utente utente = getUtenteCorrente(userDetails);

        List<Utente> classificaGlobale = getClassificaGlobale();
        List<Utente> classificaAmici = getClassificaAmici(utente);

        model.addAttribute("classificaGlobale", classificaGlobale);
        model.addAttribute("classificaAmici", classificaAmici);

        return "challenge";
    }
	
	
}
