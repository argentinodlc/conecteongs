package com.conecteongs.conecteongs.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.conecteongs.conecteongs.persistence.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

@Service
public class TokenService {
	
	private static final String ISSUER = "ConecteOngs";
	private static final String AUDIENCE = "UsersForConecteOngs";
	private static final String SECRET = "W7?uvHj:Wr3EeD(:";
	private static final Algorithm ALGORITH = Algorithm.HMAC256(SECRET);
    private static final JWTVerifier VERIFIER = JWT.require(ALGORITH).build();
    
    public boolean isTokenValid(String token) {
        try {
        	
        	if (token == null || token.trim().isEmpty() || token.equals("Bearer")) {
                return false;
            }

            DecodedJWT jwt = VERIFIER.verify(token);
            
            String issuer = jwt.getIssuer();
            if (issuer == null || !ISSUER.equals(issuer)) {
                return false;
            }

            Date expiration = jwt.getExpiresAt();
            if (expiration == null || expiration.before(new Date())) {
                return false;
            }

            String subject = jwt.getSubject();
            if (subject == null) {
                return false;
            }
            
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

	public String createToken(User user) {
		return JWT.create()
				.withIssuer(ISSUER)
				.withAudience(AUDIENCE)
				.withSubject(user.getUsername())
				.withClaim("id", user.getId())
				.withExpiresAt(LocalDateTime.now()
						.plusMinutes(60)
						.toInstant(ZoneOffset.of("-03:00"))
				).sign(Algorithm.HMAC256(SECRET));
	}

	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256(SECRET))
				.withIssuer(ISSUER)
				.withAudience(AUDIENCE)
				.build().verify(token).getSubject();
	}

	public void createTokenFromGoogle(Payload payload) {
		
	}

}
