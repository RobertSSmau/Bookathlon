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

import com.bookathlon.entities.Amicizia;
import com.bookathlon.entities.LibreriaUtente;
import com.bookathlon.entities.Utente;
import com.bookathlon.repos.UtenteRepository;
import com.bookathlon.service.AmiciziaService;
import com.bookathlon.service.LibreriaUtenteService;

/**
 * Controller per la gestione della libreria personale dell'utente.
 * Gestisce le operazioni di visualizzazione, aggiunta e rimozione di libri
 * dalla libreria di un utente autenticato.
 */
@Controller
@RequestMapping("/area-personale")
 // Tutte le richieste a questo controller iniziano con libreria
public class AreaPersonaleController {

    @Autowired
    private LibreriaUtenteService libreriaService;
	 // Servizio per le operazioni sulla libreria dell'utente.

    @Autowired
    private UtenteRepository utenteRepo; 
	// Repository per l'accesso ai dati dell'utente.
    
    @Autowired
    private AmiciziaService amiciziaService;
    /**
     * Mostra la libreria personale dell'utente autenticato.
     * Recupera i libri letti e da leggere per l'utente corrente e li aggiunge al modello.
     */
    @GetMapping
    public String mostraLibreria(Model m, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername(); 
        Utente utente = utenteRepo.findByUsername(username);
        Long utenteId = utente.getId(); 

        // Recupera i libri con stato "LETTO" e "DA_LEGGERE" per l'utente.
        List<LibreriaUtente> letti = libreriaService.getLibriByStato(utenteId, "LETTO");
        List<LibreriaUtente> daLeggere = libreriaService.getLibriByStato(utenteId, "DA_LEGGERE");

        // Amicizie
        List<Amicizia> amici = amiciziaService.getAmici(utenteId);
        List<Amicizia> richiesteRicevute = amiciziaService.getRichiesteRicevute(utenteId);
        List<Amicizia> richiesteInviate = amiciziaService.getRichiesteInviate(utenteId);
        
        // Aggiunge le liste al modello per la visualizzazione nella vista.
        m.addAttribute("letti", letti);
        m.addAttribute("daLeggere", daLeggere);
        
        m.addAttribute("amici", amici);
        m.addAttribute("richiesteRicevute", richiesteRicevute);
        m.addAttribute("richiesteInviate", richiesteInviate);
        
        
        return "area-personale";

    }

    /**
     * Aggiunge un libro alla libreria personale dell'utente con uno stato specifico.
     */
    @PostMapping("/aggiungi")
    public String aggiungiLibro(
            @RequestParam Long libroId,
            @RequestParam String stato,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
		 // Recupera l'username dell'utente.
        Utente utente = utenteRepo.findByUsername(username);
		 // Trova l'oggetto Utente.

        libreriaService.aggiungiLibro(utente.getId(), libroId, stato); 
		// Chiama il servizio per aggiungere il libro.

        return "redirect:/area-personale"; 
		// Reindirizza alla pagina della libreria per mostrare i cambiamenti.
    }

    /**
     * Rimuove un libro dalla libreria personale dell'utente.
     */
    @PostMapping("/rimuovi")
    public String rimuoviLibro(
            @RequestParam Long libroId,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername(); 
		// Recupera l'username dell'utente.
        Utente utente = utenteRepo.findByUsername(username);
		 // Trova l'oggetto Utente.

        libreriaService.rimuoviLibro(utente.getId(), libroId);
		 // Chiama il servizio per rimuovere il libro.

        return "redirect:/area-personale"; 
		// Reindirizza alla pagina della libreria per mostrare i cambiamenti.
    }
}











