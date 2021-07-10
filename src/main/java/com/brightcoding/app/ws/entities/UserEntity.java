package com.brightcoding.app.ws.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity

@Table(name="users")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class UserEntity implements Serializable {

	
	private static final long serialVersionUID = -5763827745308343856L;


	@Id
	@GeneratedValue
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(nullable=true)
	private String userId;
	

	
	@Column(nullable=false, length=120, unique=true)
	private String email;
	
	@Column(nullable=true)
	private int admin = 0;
	
	@Column(nullable=false)
	private String encryptedPassword;
	
	@Column(nullable=true)
	private String emailVerificationToken;
	
	@Column(nullable=false)
	private Boolean emailVerificationStatus = false;
	@Column(name = "reset_token")
	private String resetToken;

	public Integer isEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	@Column(name = "enabled")
	private Integer enabled;

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}
/*@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private List<AddressEntity> addresses;
	
	@OneToOne(mappedBy="user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private ContactEntity contact;*/
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="users")
	private Set<GroupEntity> groups = new HashSet<>();
	


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
	public void setEmailVerficationStatus(Boolean emailVerficationStatus) {
		this.emailVerificationStatus = emailVerficationStatus;
	}

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}




	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

}
