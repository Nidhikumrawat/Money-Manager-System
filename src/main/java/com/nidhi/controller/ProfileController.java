package com.nidhi.controller;


import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nidhi.dto.AuthDTO;
import com.nidhi.dto.ProfileDTO;
import com.nidhi.service.ProfileService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProfileController {
 
	private final ProfileService profileService ;
	@PostMapping("/register")
	public ResponseEntity <ProfileDTO> registerProfile(@RequestBody ProfileDTO profileDTO){
		ProfileDTO registeredProfile =profileService.registerProfile(profileDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(registeredProfile);
		
}
	
    @GetMapping("/activate")
	public ResponseEntity<String> activateProfile( @RequestParam String token){
		boolean isActivated = profileService.activateProfile(token);
		if(isActivated) {
			return ResponseEntity.ok("Profile Activated SuccessFully");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Activation Token is Not Found or already used.");
		}
	}
    
    @GetMapping("/csrf-token")
	 public CsrfToken getCsrfToken(HttpServletRequest request) {
		return  (CsrfToken)request.getAttribute("_csrf");
		
	}
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthDTO authDTO){
    	try {
    		if(!profileService.isAccountActive(authDTO.getEmail())) {
    			
    			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
    					"message","Account is not active. Please activate your account first."
    					));
    		}
    		
    		Map<String, Object> response = profileService.authenticationAndGenerateToken(authDTO);
    		return ResponseEntity.ok(response);
    	}catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
    				"message",e.getMessage()
    				));
    	}
    }
    
    @GetMapping("/test")
    public String Test() {
    	return "Test Succesfull";
    }
	
}

