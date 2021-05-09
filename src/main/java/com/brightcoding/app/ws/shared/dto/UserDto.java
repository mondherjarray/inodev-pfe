package com.brightcoding.app.ws.shared.dto;

import com.brightcoding.app.ws.entities.CondidatEntity;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

	private static final long serialVersionUID = -2624881664878912922L;

	private long id;
	private String userId;

	private String email;
	private String password;
	private int admin;
	private String enabled;
	private String encryptedPassword;
	private String emailVerificationToken;
	private Boolean emailVerificationStatus = false;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}
	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}
	public Boolean isEmailVerficationStatus() {
		return emailVerificationStatus;
	}
	public void setEmailVerficationStatus(Boolean emailVerficationStatus) {
		this.emailVerificationStatus = emailVerficationStatus;
	}

	/*
	public List<AddressDto> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDto> addresses) {
		this.addresses = addresses;
	}
	public ContactDto getContact() {
		return contact;
	}
	public void setContact(ContactDto contact) {
		this.contact = contact;
	}*/
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	
}
