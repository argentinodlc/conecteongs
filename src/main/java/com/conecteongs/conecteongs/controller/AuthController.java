package com.conecteongs.conecteongs.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.conecteongs.conecteongs.persistence.dto.LoginDto;
import com.conecteongs.conecteongs.persistence.dto.UserTokenDto;
import com.conecteongs.conecteongs.persistence.model.Donor;
import com.conecteongs.conecteongs.persistence.model.Image;
import com.conecteongs.conecteongs.persistence.model.Role;
import com.conecteongs.conecteongs.persistence.model.User;
import com.conecteongs.conecteongs.service.impl.DonorService;
import com.conecteongs.conecteongs.service.impl.TokenService;
import com.conecteongs.conecteongs.service.impl.UserService;
import com.conecteongs.conecteongs.utils.enums.Roles;

@RestController
public class AuthController {

	private static final String CLIENT_ID = "437844580607-6f2df61l4udt8mqbvdn5iu6judmho3s2.apps.googleusercontent.com";
	private static final String UNAUTHORIZED_MSG = "Invalid token";
	private static final String INTERNAL_SERVER_ERROR_MSG = "Error occurred during token verification";
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TokenService tokenService;
	
    @Autowired
	private UserService userService;
    
    @Autowired
	private DonorService donorService;
	
	@PostMapping("/auth")
	public ResponseEntity<UserTokenDto> auth(@RequestBody LoginDto login) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
				new UsernamePasswordAuthenticationToken(
						login.username(), 
						login.password());
		
		Authentication authentication = 
				this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	
		User user = (User) authentication.getPrincipal();
		String token = tokenService.createToken(user);
		
		return returnUserWithToken(user, token);
	}
	
	@PostMapping("/auth/google")
    public ResponseEntity<UserTokenDto> authWithGoogle(@RequestBody LoginDto login) {
        try {
           
        	 HttpTransport transport = new NetHttpTransport();
             JsonFactory jsonFactory = new GsonFactory();
        	
        	GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
        		    .setAudience(Collections.singletonList(CLIENT_ID))
        		    .build();
        		    
        		    
            GoogleIdToken idToken = verifier.verify(login.token());
            if (idToken != null) {
            	
                Payload payload = idToken.getPayload();     
                String email = payload.getEmail();
                User user = userService.findByEmail(email);
                
        		if (user == null) {
        			user = createNewUserFromGoogleAndReturn(payload, email);
        		}
        		
        		String token = tokenService.createToken(user);
        		
        		return returnUserWithToken(user, token);
            } else {
                return returnErrorMessage(HttpStatus.UNAUTHORIZED);
            }
        } catch (GeneralSecurityException | IOException  e) {
            return returnErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	private User createNewUserFromGoogleAndReturn(Payload payload, String email) {
		Image newImage = Image.builder()
				.url((String) payload.get("picture"))
				.createdAt(new Date())
				.build();
		
		User newUser = User.builder()
    			.name((String) payload.get("name"))
    			.email(email)
    			.username((String) payload.get("username"))
    			.roles(
    					List.of(
    							new Role(Roles.USER.toString())
    							))
    			.image(newImage)
    			.build();
		
		Donor newDonor = Donor.builder()
				.user(newUser)
				.build();
		
		donorService.save(newDonor);
		
		return userService.findByEmail(email);
	}
	
	private ResponseEntity<UserTokenDto> returnUserWithToken(User user, String token) {
		UserTokenDto dto = modelMapper.map(user, UserTokenDto.class);
		dto.setToken(token);
		return ResponseEntity.ok(dto);
	}

	private ResponseEntity<UserTokenDto> returnErrorMessage(HttpStatus httpStatus) {
		UserTokenDto dto;
		switch (httpStatus) {
		case UNAUTHORIZED: {
			dto = UserTokenDto.builder()
			.message(UNAUTHORIZED_MSG)
			.build();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(dto);
		}
		case INTERNAL_SERVER_ERROR: {
			dto = UserTokenDto.builder()
					.message(INTERNAL_SERVER_ERROR_MSG)
					.build();
			return ResponseEntity.internalServerError().body(dto);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + httpStatus);
		}
	}
		
	
	@GetMapping("/home")
	public String home() {
		return passwordEncoder.encode("123");
	}
	
	@GetMapping("/admin") 
	@Secured("ADMIN")
	public String admin(){
		return "Ok";
	}
	
}
