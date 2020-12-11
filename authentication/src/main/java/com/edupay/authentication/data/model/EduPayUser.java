package com.edupay.authentication.data.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



/**
 * @author Harsh
 *
 */
@Entity
@Table(name="ep_user")
public class EduPayUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1449430293672692153L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_ep_user_id")
	private Integer pkEPUserId;
	
	@Column(name="user_name",nullable = false,unique = true)
	private String userName;
	
	@Column(name="password",nullable = false)
	private String password;
	
	@Column(name="is_deleted", columnDefinition = "tinyint(1) default 0",nullable = false)
	private boolean isDeleted=false;

	public Integer getPkEPUserId() {
		return pkEPUserId;
	}

	public void setPkEPUserId(Integer pkEPUserId) {
		this.pkEPUserId = pkEPUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return !isDeleted();
	}


}
