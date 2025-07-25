package com.nidhi.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;
import lombok.*;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.PrePersist;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import jakarta.persistence.*;


@Entity
@Table(name = "tbl_profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProfileEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	
	private Long id;
	
	private String fullName;
	
	@Column(unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	
	private String profileImageUrl;
	
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	private Boolean isActive;
	
	private String activationToken;
	
	@PrePersist
	public void prePersist() {
		if(this.isActive == null) {
			isActive =false;
		}
		
	}
}
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public ProfileEntity() {
//		
//	}
//	public String getActivationToken() {
//		return activationToken;
//	}
//
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//
//	public String getProfileImageUrl() {
//		return profileImageUrl;
//	}
//
//	public void setProfileImageUrl(String profileImageUrl) {
//		this.profileImageUrl = profileImageUrl;
//	}
//
//	public LocalDateTime getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(LocalDateTime createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public LocalDateTime getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(LocalDateTime updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//	public Boolean getIsActive() {
//		return isActive;
//	}
//
//	public void setIsActive(Boolean isActive) {
//		this.isActive = isActive;
//	}
//
//	
//
//	
//
//	public void setActivationToken(String string) {
//		// TODO Auto-generated method stub
//		
//	}
//	public void getFullName() {
//		// TODO uto-generated method stub
//		this.fullName = fullName;
//	}
////	public static Object builder() {
////		// TODO Auto-generated method stub
////		return Object;
////	}
//
//
//
//	
//
//
//}
