package com.conecteongs.conecteongs.persistence.dto;

public record LoginDto(
		String username, 
		String password, 
		String token
		) {

}
