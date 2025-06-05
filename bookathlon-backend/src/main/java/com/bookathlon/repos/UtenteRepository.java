package com.bookathlon.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookathlon.entities.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    // login 
    Utente findByUsername(String username);
    Utente findByEmail(String email);
}