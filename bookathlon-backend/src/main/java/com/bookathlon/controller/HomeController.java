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

/**
 * Controller per la gestione della homepage e della funzionalità di ricerca libri.
 * Gestisce le richieste GET per la visualizzazione della homepage e dei risultati di ricerca.
 */
@Controller
public class HomeController {

    @Autowired
    private LibroService libroService;

 /**
     * Gestisce la richiesta per la homepage dell'applicazione.
     * Recupera i libri di tendenza e tutti i libri, li raggruppa per genere
     * e li aggiunge al modello per la visualizzazione sulla pagina "home".
     */
    @GetMapping("/")
    public String homePage(Model m) {
        // Recupera i libri più letti (i primi 5 almeno)
        List<Libro> libriTendenza = libroService.getLibriDiTendenza();

        // Recupera la lista di tutti i libri
        List<Libro> tuttiLibri = libroService.getLibri();

        // HashMap per raggruppare i libri per genere per le card della home
        Map<String, List<Libro>> libriPerGenere = new HashMap<>();
        for (Libro libro : tuttiLibri) {
            String genere = libro.getGenere();
            if (genere != null) {
                libriPerGenere.putIfAbsent(genere, new ArrayList<>());
                libriPerGenere.get(genere).add(libro);
            }
        }

        // Passaggio dati a Thymeleaf
        m.addAttribute("tendenze", libriTendenza);
        m.addAttribute("libriPerGenere", libriPerGenere);

        return "home";
         // Ritorna il nome della vista "home".
    }

    /**
     * Gestisce la richiesta di ricerca libri tramite una parola chiave.
     * Esegue una ricerca sui libri basandosi sulla parola chiave fornita
     * e aggiunge i risultati al modello per la visualizzazione sulla pagina "risultati-filtrati".
     */
    @GetMapping("/cerca")
    public String cerca(@RequestParam String q, Model m) {
        List<Libro> risultati = libroService.cerca(q);
        m.addAttribute("filtrati", risultati);
        return "risultati-filtrati"; 
        // Questo HTML dovrà esistere nella cartella dei template.
    }
}