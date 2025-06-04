package com.bookathlon.entities;

import com.bookathlon.enums.Ruolo;
import jakarta.persistence.*;

/**
 * Entità che rappresenta un utente del sistema
 * Ogni utente ha un ID, un username univoco, una password e un ruolo (es. ADMIN, USER)
 */
@Entity
@Table(name = "utente") // Mappa la classe alla tabella "utente" nel database
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificativo univoco generato automaticamente

    @Column(nullable = false, unique = true)
    private String username; // Nome utente, deve essere univoco e non nullo

    @Column(nullable = false)
    private String password; // Password dell'utente (si consiglia di cifrarla nel database)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Ruolo ruolo; // Ruolo dell'utente, definito come enum (es. ADMIN, USER)

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }
}
