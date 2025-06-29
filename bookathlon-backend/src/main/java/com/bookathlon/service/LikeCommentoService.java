package com.bookathlon.service;

public interface LikeCommentoService {

    boolean haGiaMessoLike(Long commentoId, Long utenteId);
    void mettiLike(Long commentoId, Long utenteId);
    void rimuoviLike(Long commentoId, Long utenteId);
	
}
