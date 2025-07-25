package com.nidhi.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nidhi.security.JwtRequestFilter;
import com.nidhi.service.AppUserDetailsService;

//import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration 
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final AppUserDetailsService appUserDetailsService;
	private final JwtRequestFilter jwtRequestFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		 httpSecurity.cors(Customizer.withDefaults())
		.csrf(AbstractHttpConfigurer:: disable)
		.authorizeHttpRequests(auth-> auth.requestMatchers("/status","/login","/health","/register","/activate","/test","/categories","/dashboard").permitAll()
		.anyRequest()
		.authenticated())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		// .exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint()));
		 .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
		}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(List.of("*"));
		configuration.setAllowedMethods(List.of("GET", "PUT","POST","DELETE","OPTIONS"));
		configuration.setAllowedHeaders(List.of("Authorization","Content-Tpye","Accept"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source =  new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
	@SuppressWarnings("deprecation")
	@Bean 
	public AuthenticationProvider  authenticationProvider( ) {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		provider.setUserDetailsService( appUserDetailsService);
		return provider;
	}
	
	
	
	
	
	@SuppressWarnings("deprecation")
	@Bean
	public AuthenticationManager authenticationManager() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		authenticationProvider.setUserDetailsService(appUserDetailsService);
		
		return new ProviderManager(authenticationProvider);
	}
	
	
//	@Bean
//	public AuthenticationEntryPoint authenticationEntryPoint() {
//	    return (request, response, authException) -> {
//	        response.setContentType("application/json");
//	        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//	        response.getWriter().write("{\"error\": \"Unauthorized - Login required\"}");
//	    };
//	}


	

}
