package com.renanCompany.dioBanck.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;

import com.renanCompany.dioBanck.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority ,Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private UserRole role;

	
	public UserRole getRole() {
		return role;
	}

	
	public void setRole(UserRole role) {
		this.role = role;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(id, role);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id) && Objects.equals(role, other.role);
	}


	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + "]";
	}


	@Override
	public String getAuthority() {
		return this.role.toString();
	}

}
