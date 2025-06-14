package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.Amicizia;
/**
 * Interfaccia per il servizio di gestione delle relazioni di amicizia.
 * Definisce le operazioni disponibili per interagire con le amicizie tra utenti,
 * inclusa la visualizzazione degli amici, la gestione delle richieste
 * e l'invio/accettazione/rifiuto delle stesse.
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
