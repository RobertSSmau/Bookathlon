package com.bookathlon.entities;

import com.bookathlon.enums.Ruolo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
@Entity   // Indica che questa classe rappresenta una tabella nel database
@Table(name = "utente")   // Specifica che è collegata alla tabella chiamata "utente"
public class Utente {

    @Id  // Questo campo è la chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Il valore viene generato automaticamente dal DB,autoincremento
    private Long userId;

    @NotBlank(message = "Lo username è obbligatorio")
    @Size(min = 3, max = 50, message = "Lo username deve avere tra 3 e 50 caratteri")
    @Column(nullable = false, unique = true)// Lo username è un campo obbligatorio e deve essere unico nel DB
    private String username;

    @NotBlank(message = "La password è obbligatoria")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$",
        message = "La password deve contenere almeno 8 caratteri, un numero, una lettera maiuscola, una minuscola e un carattere speciale"
         )
    @Column(nullable = false)  //La password è obbligatoria e deve essere cifrata prima di salvarla nel DB
    private String password;
    
    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Email non valida")
    @Column(nullable = false, unique = true)
    private String email;

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
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
