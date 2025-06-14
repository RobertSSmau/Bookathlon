package com.bookathlon.controller;

import java.util.ArrayList;
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

import com.bookathlon.dto.AmicoDTO;
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
        List<Amicizia> ricevute = amiciziaService.getRichiesteRicevute(utenteId);
        List<Amicizia> inviate = amiciziaService.getRichiesteInviate(utenteId);
        
        List<AmicoDTO> amiciDTO = new ArrayList<>();
        for (Amicizia a : amici) {
            Long utente1 = a.getUtente1();
            Long utente2 = a.getUtente2();

            // trovo l'altro utente (non il mio)
            Long altroId;
            if (utente1.equals(utenteId)) {
                altroId = utente2;
            } else {
                altroId = utente1;
            }

            // recupero l'utente dal DB e creo il DTO
            Utente altroUtente = utenteRepo.findById(altroId).orElse(null);
            if (altroUtente != null) {
                AmicoDTO dto = new AmicoDTO(altroUtente.getId(),
                		altroUtente.getUsername());
                amiciDTO.add(dto);
            }
        }
        
        // Aggiunge le liste al modello per la visualizzazione nella vista.
        m.addAttribute("letti", letti);
        m.addAttribute("daLeggere", daLeggere);
        m.addAttribute("amici", amiciDTO);
        m.addAttribute("richiesteRicevute", ricevute);
        m.addAttribute("richiesteInviate", inviate);
        m.addAttribute("loggedId", utenteId); // per i form Thytmeleaf
        
        
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
    
    @PostMapping("/amici/invia")
    public String inviaRichiestaAmicizia(@RequestParam Long destinatarioId,
                                         @AuthenticationPrincipal UserDetails userDetails) {
    	Utente utente = utenteRepo.findByUsername(userDetails.getUsername());
        amiciziaService.inviaRichiesta(utente.getId(), destinatarioId);
    	return "redirect:/area-personale";
    }
    
    @PostMapping("/amici/accetta")
    public String accettaRichiestaAmicizia(@RequestParam Long utente1,
                                           @AuthenticationPrincipal UserDetails userDetails) {
    	Utente utente2 = utenteRepo.findByUsername(userDetails.getUsername());
        amiciziaService.accettaRichiesta(utente1, utente2.getId());
        return "redirect:/area-personale";
    }
    
    @PostMapping("/amici/rifiuta")
    public String rifiutaRichiestaAmicizia(@RequestParam Long utente1,
                                           @AuthenticationPrincipal UserDetails userDetails) {
    	Utente utente2 = utenteRepo.findByUsername(userDetails.getUsername());
        amiciziaService.rifiutaRichiesta(utente1, utente2.getId());
        return "redirect:/area-personale";
    }
    
}











