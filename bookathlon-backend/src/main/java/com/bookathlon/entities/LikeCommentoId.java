package com.bookathlon.entities;

import java.io.Serializable;
import java.util.Objects;

public class LikeCommentoId implements Serializable{

	
	private Long commentoId;
    private Long utenteId;
    
    public LikeCommentoId() {}

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
    
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        LikeCommentoId other = (LikeCommentoId) o;
        if (commentoId == null) {
            if (other.commentoId != null) {
                return false;
            }
        } else if (!commentoId.equals(other.commentoId)) {
            return false;
        }
        if (utenteId == null) {
            if (other.utenteId != null) {
                return false;
            }
        } else if (!utenteId.equals(other.utenteId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentoId, utenteId);
    }
	
}
