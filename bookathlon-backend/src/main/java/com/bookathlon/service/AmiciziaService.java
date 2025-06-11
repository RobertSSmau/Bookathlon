package com.bookathlon.service;

import java.util.List;

import com.bookathlon.entities.Amicizia;

public interface AmiciziaService {

	 	List<Amicizia> getAmici(Long userId);
	    List<Amicizia> getRichiesteRicevute(Long userId);
	    List<Amicizia> getRichiesteInviate(Long userId);
	    void inviaRichiesta(Long daUtenteId, Long aUtenteId);
	    void accettaRichiesta(Long daUtenteId, Long aUtenteId);
	    void rifiutaRichiesta(Long daUtenteId, Long aUtenteId);
	
}
