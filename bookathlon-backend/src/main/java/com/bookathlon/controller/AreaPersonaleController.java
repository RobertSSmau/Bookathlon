package com.bookathlon.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookathlon.dto.UtenteDTO;
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
    public String mostraAreaPersonale(Model m, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        Utente utente = utenteRepo.findByUsername(username);
        Long utenteId = utente.getId();

        caricaLibreria(m, utenteId);
        caricaAmicizie(m, utenteId);

        m.addAttribute("loggedId", utenteId);
        m.addAttribute("score", utente.getScore());
        return "area-personale";
    }
    
    private void caricaLibreria(Model m, Long utenteId) {
        List<LibreriaUtente> letti = libreriaService.getLibriByStato(utenteId, "LETTO");
        List<LibreriaUtente> daLeggere = libreriaService.getLibriByStato(utenteId, "DA_LEGGERE");
        m.addAttribute("letti", letti);
        m.addAttribute("daLeggere", daLeggere);
    }
    
    private void caricaAmicizie(Model model, Long mioId) {

        // Recupero le liste di amicizie dal servizio
        List<Amicizia> listaAmici = amiciziaService.getAmici(mioId);
        List<Amicizia> richiesteRicevute = amiciziaService.getRichiesteRicevute(mioId);
        List<Amicizia> richiesteInviate = amiciziaService.getRichiesteInviate(mioId);

        // Preparo la lista dei miei amici come DTO
        List<UtenteDTO> amiciDTO = new ArrayList<>();
        for (Amicizia amicizia : listaAmici) {
            Long idAltroUtente = null;

            if (amicizia.getUtente1().equals(mioId)) {
                idAltroUtente = amicizia.getUtente2();
            } else {
                idAltroUtente = amicizia.getUtente1();
            }

            Utente altroUtente = utenteRepo.findById(idAltroUtente).orElse(null);
            if (altroUtente != null) {
            	UtenteDTO dto = new UtenteDTO(
            		    altroUtente.getId(),
            		    altroUtente.getUsername(),
            		    altroUtente.getScore()
            		);
                amiciDTO.add(dto);
            }
        }

        // Preparo la lista delle richieste inviate come DTO
        List<UtenteDTO> inviateDTO = new ArrayList<>();
        for (Amicizia richiesta : richiesteInviate) {
            Long idDestinatario = richiesta.getUtente2();
            Utente destinatario = utenteRepo.findById(idDestinatario).orElse(null);
            if (destinatario != null) {
                UtenteDTO dto = new UtenteDTO(destinatario.getId(), destinatario.getUsername());
                inviateDTO.add(dto);
            }
        }

        // Preparo la lista delle richieste ricevute come DTO
        List<UtenteDTO> ricevuteDTO = new ArrayList<>();
        for (Amicizia richiesta : richiesteRicevute) {
            Long idMittente = richiesta.getUtente1();
            Utente mittente = utenteRepo.findById(idMittente).orElse(null);
            if (mittente != null) {
                UtenteDTO dto = new UtenteDTO(mittente.getId(), mittente.getUsername());
                ricevuteDTO.add(dto);
            }
        }

        // Aggiungo le liste al model
        model.addAttribute("amici", amiciDTO);
        model.addAttribute("richiesteRicevute", ricevuteDTO);
        model.addAttribute("richiesteInviate", inviateDTO);
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
    
    @PostMapping("/inizia")
    public String iniziaLettura(
            @RequestParam Long libroId,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        Utente utente = utenteRepo.findByUsername(username);

        libreriaService.iniziaLettura(utente.getId(), libroId);

        return "redirect:/area-personale";
    }
    
    @GetMapping("/cerca")
    public String paginaCercaUtente(Model m,
                                    @AuthenticationPrincipal UserDetails userDetails) {
        Utente utente = utenteRepo.findByUsername(userDetails.getUsername());
        m.addAttribute("loggedId", utente.getId());
        return "cerca-utente";
    }

    
    @GetMapping("/cerca-utente")
    public String cercaUtente(@RequestParam String q,
                              Model m,
                              @AuthenticationPrincipal UserDetails userDetails) {
        Utente utente = utenteRepo.findByUsername(userDetails.getUsername());
        Long mioId = utente.getId();

        List<Utente> risultati = utenteRepo.cercaPerUsername(q);
        List<Amicizia> amici = amiciziaService.getAmici(mioId);
        List<Amicizia> richiesteInviate = amiciziaService.getRichiesteInviate(mioId);
        List<Amicizia> richiesteRicevute = amiciziaService.getRichiesteRicevute(mioId);
        
        Set<Long> idEscludi = getIdUtentiEsclusi(mioId, amici, richiesteInviate, richiesteRicevute);
        
        List<Utente> filtrati = filtraUtenti(risultati, mioId, idEscludi);
             
        caricaLibreria(m, mioId);
        caricaAmicizie(m, mioId);

        m.addAttribute("risultatiUtenti", filtrati);
        m.addAttribute("query", q);
        m.addAttribute("loggedId", mioId);

        return "cerca-utente";
    }
    
    private List<Utente> filtraUtenti(List<Utente> candidati, Long mioId, Set<Long> idEscludi) {
        List<Utente> filtrati = new ArrayList<>();

        for (Utente u : candidati) {
            Long idUtente = u.getId();
            if (!idUtente.equals(mioId) && 
            		!idEscludi.contains(idUtente)) {
                filtrati.add(u);
            }
        }

        return filtrati;
    }
    
    // uso set perchè non accetta duplicati ee è molto veloce nel fare contins()
    
    private Set<Long> getIdUtentiEsclusi(Long mioId,
            List<Amicizia> amici,
            List<Amicizia> richiesteInviate,
            List<Amicizia> richiesteRicevute) {

	Set<Long> idEscludi = new HashSet<>();
	
	for (Amicizia a : amici) {
	Long altro = a.getUtente1().equals(mioId) ? a.getUtente2() : a.getUtente1();
	idEscludi.add(altro);
	}
	
	for (Amicizia a : richiesteInviate) {
	idEscludi.add(a.getUtente2());
	}
	
	for (Amicizia a : richiesteRicevute) {
	idEscludi.add(a.getUtente1());
	}
	
	return idEscludi;
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
    
    @PostMapping("/amici/rimuovi")
    public String rimuoviAmicizia(@RequestParam Long altroUtenteId,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        Utente utente = utenteRepo.findByUsername(userDetails.getUsername());
        amiciziaService.rimuoviAmicizia(utente.getId(), altroUtenteId);
        return "redirect:/area-personale";
    }
    
}











