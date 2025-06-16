package com.bookathlon.dto;

public class AmicoDTO {
	
	private Long id;
    private String username;
    private int score;

    public AmicoDTO(Long id, String username, int score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    // Costruttore compatibile con vecchio codice
    public AmicoDTO(Long id, String username) {
        this.id = id;
        this.username = username;
        this.score = 0; // default
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public int getScore() { return score; }

    
	
}
