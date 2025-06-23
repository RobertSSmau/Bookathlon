package com.bookathlon.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "like_commento")
@IdClass(LikeCommentoId.class)
public class LikeCommento implements Serializable{

	  @Id
	  @Column(name = "commento_id")
	  private Long commentoId;

	  @Id
	  @Column(name = "utente_id")
	  private Long utenteId;

	  public LikeCommento() {}

	  public LikeCommento(Long commentoId, Long utenteId) {
	        this.commentoId = commentoId;
	        this.utenteId = utenteId;
	    }

	  public Long getCommentoId() {
	        return commentoId;
	    }

	  public void setCommentoId(Long commentoId) {
	        this.commentoId = commentoId;
	    }

	  public Long getUtenteId() {
	        return utenteId;
	    }

	  public void setUtenteId(Long utenteId) {
	        this.utenteId = utenteId;
	    }
	
}
