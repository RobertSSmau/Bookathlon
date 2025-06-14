package com.bookathlon.dto;

public class AmicoDTO {
	
	private Long id;
    private String username;

    public AmicoDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
	
}
