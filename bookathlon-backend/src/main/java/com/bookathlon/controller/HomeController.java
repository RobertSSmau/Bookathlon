package com.bookathlon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookathlon.entities.Libro;
import com.bookathlon.service.LibroService;

@Controller
public class HomeController {

	@Autowired
	private LibroService libroService;
	
	@GetMapping("/")
	public String homePage(Model m) {
		
		// I libri piu letti (i primi 5 almeno)
        List<Libro> libriTendenza = libroService.getLibriDiTendenza();

        // List di tutti i libri
        List<Libro> tuttiLibri = libroService.getLibri();

        // Hashmap per raggrupparli per generi per le card della home
        Map<String, List<Libro>> libriPerGenere = new HashMap<>();
        for (Libro libro : tuttiLibri) {
            String genere = libro.getGenere();
            if (genere != null) {
                libriPerGenere.putIfAbsent(genere, new ArrayList<>());
                libriPerGenere.get(genere).add(libro);
            }
        }

        // passaggio dati a thymeleaf
        m.addAttribute("tendenze", libriTendenza);
        m.addAttribute("libriPerGenere", libriPerGenere);

        return "home"; 
	}
	
		@GetMapping("/cerca")
		public String cerca(@RequestParam String q, Model m) {
	    List<Libro> risultati = libroService.cerca(q);
	    m.addAttribute("filtrati", risultati);
	    return "risultati-filtrati"; // questo HTML dovrà esistere nella cartella dei template
	}
	
	
}
