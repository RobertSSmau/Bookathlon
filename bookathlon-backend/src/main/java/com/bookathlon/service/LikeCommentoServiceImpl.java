package com.bookathlon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookathlon.entities.LikeCommento;
import com.bookathlon.repos.CommentoRepository;
import com.bookathlon.repos.LikeCommentoRepository;

import jakarta.transaction.Transactional;

@Service
public class LikeCommentoServiceImpl implements LikeCommentoService {

	 	@Autowired
	    private LikeCommentoRepository likeRepo;



	    @Override
	    public boolean haGiaMessoLike(Long commentoId, Long utenteId) {
	        return likeRepo.haGiaMessoLike(commentoId, utenteId); 
	    }
	    
	    
	    @Override
	    @Transactional
	    public void mettiLike(Long commentoId, Long utenteId) {
	        if (!haGiaMessoLike(commentoId, utenteId)) {
	            LikeCommento lc = new LikeCommento();
	            lc.setCommentoId(commentoId);
	            lc.setUtenteId(utenteId);
	            likeRepo.save(lc);
	        }
	    }

	    @Override
	    @Transactional
	    public void rimuoviLike(Long commentoId, Long utenteId) {
	        likeRepo.rimuoviLike(commentoId, utenteId); 
	    }
	
}
