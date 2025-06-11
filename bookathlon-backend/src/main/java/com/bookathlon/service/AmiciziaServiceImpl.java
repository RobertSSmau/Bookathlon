package com.bookathlon.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookathlon.entities.Amicizia;
import com.bookathlon.repos.AmiciziaRepo;

@Service 
public class AmiciziaServiceImpl implements AmiciziaService {

	private AmiciziaRepo repo;
	
	@Override
	public List<Amicizia> getAmici(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Amicizia> getRichiesteRicevute(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Amicizia> getRichiesteInviate(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inviaRichiesta(Long daUtenteId, Long aUtenteId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void accettaRichiesta(Long daUtenteId, Long aUtenteId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rifiutaRichiesta(Long daUtenteId, Long aUtenteId) {
		// TODO Auto-generated method stub

	}

}
