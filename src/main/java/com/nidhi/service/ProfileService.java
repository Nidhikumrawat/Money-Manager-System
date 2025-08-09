package com.nidhi.service;

import java.util.Map;

import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nidhi.dto.AuthDTO;
import com.nidhi.dto.ProfileDTO;
import com.nidhi.entity.ProfileEntity;
import com.nidhi.repository.ProfileRepository;
import com.nidhi.util.JwtUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;  // ✅ Correct one


@Service
@RequiredArgsConstructor

public class ProfileService {
	
   

	private final ProfileRepository profileRepository ;
	private final EmailService emailService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;
	private final AppUserDetailsService appUserDetailsService;
	
	 @Value("${app.activation.url}")
	    private String activationURL;
	

	public ProfileDTO registerProfile(ProfileDTO profileDTO) {
		
		  
	    if (profileRepository.existsByEmail(profileDTO.getEmail())) {
	        throw new RuntimeException("Email already registered.");
	    }

		ProfileEntity newProfile = toEntity(profileDTO);
		newProfile.setActivationToken(UUID.randomUUID().toString());
		
		newProfile = profileRepository.save(newProfile);
		// send activation email
		String activationLink = activationURL+ "/api/v1.0/activate?token="+ newProfile.getActivationToken();
		String subject = "Activate your account ";
		String body = "Click on the following Link to Create your account "+ activationLink;
		emailService.sendEmail(newProfile.getEmail(), subject, body);
		
		return toDTO(newProfile);
	}

	public ProfileEntity toEntity(ProfileDTO profileDTO) {
		return ProfileEntity.builder()
				.id(profileDTO.getId())
				.fullName(profileDTO.getFullName())
				.email(profileDTO.getEmail())
				.password(passwordEncoder.encode(profileDTO.getPassword()))
				.profileImageUrl(profileDTO.getProfileImageUrl())
				.createdAt(profileDTO.getCreatedAt())
				.updatedAt(profileDTO.getUpdatedAt())
				.build();
	}

	public ProfileDTO toDTO(ProfileEntity profileEntity) {
		return ProfileDTO.builder()
				.id(profileEntity.getId())
				.fullName(profileEntity.getFullName())
				.email(profileEntity.getEmail())
				.profileImageUrl(profileEntity.getProfileImageUrl())
				.createdAt(profileEntity.getCreatedAt())
				.updatedAt(profileEntity.getUpdatedAt())
				.build();
	}
	
	public boolean activateProfile(String activationToken) {
		return profileRepository.findByActivationToken(activationToken)
				.map( profile->{
					profile.setIsActive(true);
					profileRepository.save(profile);
					return true;
				})
				.orElse(false);
	}
	
	public boolean isAccountActive(String email) {
		return profileRepository.findByEmail(email)
				.map(ProfileEntity::getIsActive)
				.orElse(false);
}
	
	public ProfileEntity getCurrentProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		return profileRepository.findByEmail(authentication.getName())
		.orElseThrow(()-> new UsernameNotFoundException("Profile not found with email" + authentication.getName()));
	}
	
	public ProfileDTO getPublicProfile(String email) {
	    ProfileEntity currentUser;

	    if (email == null) {
	        currentUser = getCurrentProfile(); // Ensure this works!
	    } else {
	        currentUser = profileRepository.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("Profile not found with email " + email));
	    }

	    return ProfileDTO.builder()
	            .id(currentUser.getId())
	            .fullName(currentUser.getFullName())
	            .email(currentUser.getEmail())
	            .profileImageUrl(currentUser.getProfileImageUrl())
	            .createdAt(currentUser.getCreatedAt())
	            .updatedAt(currentUser.getUpdatedAt())
	            .build();
	}

	
	public Map<String, Object> authenticationAndGenerateToken(AuthDTO authDTO){
		 
	try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
				authDTO.getEmail(),
				authDTO.getPassword()
				));
		
		UserDetails userDetails = appUserDetailsService.loadUserByUsername(authDTO.getEmail());
		String token = jwtUtil.generateToken(userDetails.getUsername());
		System.out.println( "generate token "+token);
	    return Map.of(
	    		"token",token,
	    		"user", getPublicProfile(authDTO.getEmail())
	    		);
	}catch (BadCredentialsException e) {
        throw new RuntimeException("Invalid email or password.");
    } catch (UsernameNotFoundException e) {
        throw new RuntimeException("User not found.");
    } catch (Exception e) {
        e.printStackTrace(); // Print full stack trace for debugging
        throw new RuntimeException("Authentication failed: " + e.getMessage());
    }
}
}

