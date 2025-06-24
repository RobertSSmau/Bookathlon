package com.bookathlon.dto;

public class UtenteDTO {
	
	private Long id;
    private String username;
    private int score;

    public UtenteDTO(Long id, String username, int score) {
        this.id = id;
        this.username = username;
        this.score = score;
    }

    public UtenteDTO(Long id, String username) {
        this.id = id;
        this.username = username;
        this.score = 0;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public int getScore() { return score; }

    
	
}
