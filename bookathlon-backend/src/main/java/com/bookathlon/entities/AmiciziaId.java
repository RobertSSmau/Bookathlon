package com.bookathlon.entities;

import java.io.Serializable;
import java.util.Objects;

//classe per gestione chiave composta della tabella amicizia

public class AmiciziaId implements Serializable {

	private Long utente1;
    private Long utente2;

    public AmiciziaId() {}

    public Long getUtente1() {
        return utente1;
    }

    public void setUtente1(Long utente1) {
        this.utente1 = utente1;
    }

    public Long getUtente2() {
        return utente2;
    }

    public void setUtente2(Long utente2) {
        this.utente2 = utente2;
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
        AmiciziaId other = (AmiciziaId) o;
        if (utente1 == null) {
            if (other.utente1 != null) {
                return false;
            }
        } else if (!utente1.equals(other.utente1)) {
            return false;
        }
        if (utente2 == null) {
            if (other.utente2 != null) {
                return false;
            }
        } else if (!utente2.equals(other.utente2)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(utente1, utente2);
    }
	
}
