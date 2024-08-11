package com.renanCompany.dioBanck.DTO.response;

import java.util.Set;
import com.renanCompany.dioBanck.entities.Account;
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
public class ClientResponse {

	private String name;
	private Set<Account> account;
}
