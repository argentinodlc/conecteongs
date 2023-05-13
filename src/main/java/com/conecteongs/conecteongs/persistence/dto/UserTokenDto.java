package com.conecteongs.conecteongs.persistence.dto;

import java.util.List;

import com.conecteongs.conecteongs.persistence.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTokenDto {
	private Long id;
	private String username;
	private String token;
	private List<Role> roles;
	private boolean enabled;
	private String email; 
	private String image;
	private String name;
	private String address;
	private String phone;
	private String message;
}
