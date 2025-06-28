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

import com.bookathlon.entities.Amicizia;
import com.bookathlon.entities.Challenge;
import com.bookathlon.entities.Utente;
import com.bookathlon.repos.UtenteRepository;
import com.bookathlon.service.AmiciziaService;
import com.bookathlon.service.ChallengeService;
import com.bookathlon.service.LibroService;

@Controller
@RequestMapping("/challenge")
public class CreazioneChallengeController {

	@Autowired
	private ChallengeService challengeService;
	
	@Autowired
	private UtenteRepository utenteRepo;
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private AmiciziaService amicoService;
	
	 @GetMapping("/nuova")
	    public String mostraFormChallenge(@RequestParam Long libroId,
	                                      Model model,
	                                      @AuthenticationPrincipal UserDetails userDetails) {
	        model.addAttribute("libro", libroService.getLibroById(libroId));

	        return "form-challenge-quiz";
	        
	    }
	 
	 
	 	//FA SCHIFO
		 @PostMapping("/crea")
		 public String creaChallenge(@RequestParam Long libroId,
		                             @RequestParam String domanda,
		                             @RequestParam String opzioneA,
		                             @RequestParam String opzioneB,
		                             @RequestParam String opzioneC,
		                             @RequestParam String opzioneD,
		                             @RequestParam String rispostaCorretta,
		                             @AuthenticationPrincipal UserDetails userDetails,
		                             Model model) {
	
		     Utente autore = utenteRepo.findByUsername(userDetails.getUsername());
	
		     Challenge newchall = new Challenge();
		     newchall.setLibroId(libroId);
		     newchall.setAutoreId(autore.getId());
		     newchall.setDomanda(domanda);
		     newchall.setOpzioneA(opzioneA);
		     newchall.setOpzioneB(opzioneB);
		     newchall.setOpzioneC(opzioneC);
		     newchall.setOpzioneD(opzioneD);
		     newchall.setRispostaCorretta(rispostaCorretta);
		     newchall.setStato("PENDING");

	
		     Challenge salvata = challengeService.salvaChallenge(newchall);
		     return "redirect:/challenge/seleziona-amici?id=" + salvata.getId();
		 }
		 
		 //parte dove seleziono l'amico a cui inviare la challenge
		 @GetMapping("/seleziona-amici")
		 public String mostraSelezioneAmici(@RequestParam Long id, 
		                                    Model model,
		                                    @AuthenticationPrincipal UserDetails userDetails) {
		
	     //recupero l'utente loggato
	     String username = userDetails.getUsername();
	     Utente autore = utenteRepo.findByUsername(username);
		
	     //qua ho tutta la lista delle mie amicizie
	     List<Amicizia> relazioni = amicoService.getAmici(autore.getId());
	     
	     //e mostro la lista
	     List<Utente> amici = new ArrayList<>();
	     
	     for (Amicizia relazione : relazioni) {
	    	    Long idUtente1 = relazione.getUtente1();
	    	    Long idUtente2 = relazione.getUtente2();
	    	    Long idAltro;

	    	    // confronto utente1 e utente2
	    	    if (idUtente1.equals(autore.getId())) {
	    	        idAltro = idUtente2;
	    	        
	    	    } else {
	    	        idAltro = idUtente1;
	    	        
	    	    }

	    	    // recupero l'utente se esiste
	    	    Utente altroUtente = utenteRepo.findById(idAltro).orElse(null);
	    	    if (altroUtente != null) {
	    	        amici.add(altroUtente);
	    	    }
	    	}
	     
	     model.addAttribute("amici", amici);
	     model.addAttribute("challengeId", id);
	     
	     return "seleziona-amici";
		 }
		 
		 //workaround schifoso
		 @PostMapping("/invia")
		 public String inviaChallenge(@RequestParam Long challengeId,
		                              @RequestParam(name = "destinatari") List<Long> destinatari,
		                              @AuthenticationPrincipal UserDetails userDetails) {

		     // recupero autore
		     Utente autore = utenteRepo.findByUsername(userDetails.getUsername());

		     // recupero challenge base
		     Challenge base = challengeService.getById(challengeId);

		     // copio per ogni destinatario
		     for (Long destId : destinatari) {
		         Challenge copychall = new Challenge();

		         copychall.setLibroId(base.getLibroId());
		         copychall.setAutoreId(autore.getId());
		         copychall.setDestinatarioId(destId);
		         copychall.setDomanda(base.getDomanda());
		         copychall.setOpzioneA(base.getOpzioneA());
		         copychall.setOpzioneB(base.getOpzioneB());
		         copychall.setOpzioneC(base.getOpzioneC());
		         copychall.setOpzioneD(base.getOpzioneD());
		         copychall.setRispostaCorretta(base.getRispostaCorretta());
		         copychall.setStato("PENDING");

		         challengeService.salvaChallenge(copychall);
		     }
		     
		     //elimina il record template che rimane nel db, spazzatura
		     challengeService.eliminaDuplicato(challengeId);

		     return "redirect:/challenge";
		 }
		 
	 
}
