package com.bookathlon.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookathlon.entities.Amicizia;
import com.bookathlon.entities.AmiciziaId;
import com.bookathlon.repos.AmiciziaRepo;
/**
 * Implementazione del servizio {@link AmiciziaService} per la gestione delle relazioni di amicizia.
 * Fornisce la logica di business per recuperare lo stato delle amicizie e gestire le richieste.
 */
@Service 
public class AmiciziaServiceImpl implements AmiciziaService {

	@Autowired
	private AmiciziaRepo repo;
	
	@Override
    public List<Amicizia> getAmici(Long userId) {
        return repo.trovaAmiciAccettati(userId);
    }

    @Override
    public List<Amicizia> getRichiesteRicevute(Long userId) {
        return repo.trovaRichiesteRicevute(userId);
    }

    @Override
    public List<Amicizia> getRichiesteInviate(Long userId) {
        return repo.trovaRichiesteInviate(userId);
    }

    @Override
    public void inviaRichiesta(Long daUtenteId, Long aUtenteId) {
    	 if (daUtenteId.equals(aUtenteId)) return; // non posso inviare a me stesso

         AmiciziaId id = new AmiciziaId(daUtenteId, aUtenteId);

         if (repo.existsById(id)) return; // già esiste una relazione

         Amicizia a = new Amicizia();
         a.setUtente1(daUtenteId); // mittente
         a.setUtente2(aUtenteId); // destinatario
         a.setStato("PENDING");
         a.setDataRichiesta(LocalDateTime.now());

         repo.save(a);
    }

	@Override
	public void accettaRichiesta(Long daUtenteId, Long aUtenteId) {
		AmiciziaId id = new AmiciziaId(daUtenteId, aUtenteId);
        Amicizia a = repo.findById(id).orElseThrow();
        a.setStato("ACCEPTED");
        repo.save(a);

	}

	@Override
	public void rifiutaRichiesta(Long daUtenteId, Long aUtenteId) {
		AmiciziaId id = new AmiciziaId(daUtenteId, aUtenteId);
        repo.deleteById(id);

	}
	
	@Override
	public void rimuoviAmicizia(Long utenteA, Long utenteB) {
		AmiciziaId id1 = new AmiciziaId(utenteA, utenteB);
	    AmiciziaId id2 = new AmiciziaId(utenteB, utenteA);

	    if (repo.existsById(id1)) {
	        repo.deleteById(id1);
	    } else if (repo.existsById(id2)) {
	        repo.deleteById(id2);
	    }
	}

}
