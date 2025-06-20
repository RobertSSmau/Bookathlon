package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.Amicizia;
/**
 * Questa interfaccia definisce le operazioni per la gestione delle amicizie tra utenti. 
 * Include la visualizzazione degli amici e la gestione (invio, accettazione, rifiuto) delle richieste di amicizia.
 */
public interface AmiciziaService {

	 	List<Amicizia> getAmici(Long userId);
	    List<Amicizia> getRichiesteRicevute(Long userId);
	    List<Amicizia> getRichiesteInviate(Long userId);
	    void inviaRichiesta(Long daUtenteId, Long aUtenteId);
	    void accettaRichiesta(Long daUtenteId, Long aUtenteId);
	    void rifiutaRichiesta(Long daUtenteId, Long aUtenteId);
	    void rimuoviAmicizia(Long utenteA, Long utenteB);
	
}
