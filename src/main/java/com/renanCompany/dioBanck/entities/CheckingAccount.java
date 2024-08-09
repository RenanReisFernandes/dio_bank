package com.renanCompany.dioBanck.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_checking_account")
public class CheckingAccount extends Account {

}
