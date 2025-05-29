package com.bookathlon.entities;

import com.bookathlon.enums.Ruolo;
import jakarta.persistence.*;

@Entity   // Indica che questa classe rappresenta una tabella nel database
@Table(name = "utente")   // Specifica che è collegata alla tabella chiamata "utente"
public class Utente {

    @Id  // Questo campo è la chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Il valore viene generato automaticamente dal DB,autoincremento
    private Long userId;

    @Column(nullable = false, unique = true)  // Lo username è un campo obbligatorio e deve essere unico nel DB
    private String username;

    @Column(nullable = false)  //La password è obbligatoria e deve essere cifrata prima di salvarla nel DB
    private String password;

    @Enumerated(EnumType.STRING)  // Salva il ruolo come stringa ( "ADMIN" o "USER" )
    @Column(nullable = false)  // Campo obbligatorio
    private String type;

// I getter e setter servono per accedere o modificare i valori degli attributi.
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
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

    public String getRuolo() {
        return type;
    }

    public void setRuolo(String type) {
        this.type = type;
    }
}
