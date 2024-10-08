package com.renanCompany.dioBanck.entities;

import java.math.BigDecimal;

import com.renanCompany.dioBanck.enums.AccountStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_account")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private Long agencyNumber;
	private Long accountNumber;
	private BigDecimal balance;
	
    @OneToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
	private Client client;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AccountStatus status;

}
