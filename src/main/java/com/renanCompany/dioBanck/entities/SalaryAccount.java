package com.renanCompany.dioBanck.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_salary_account")
public class SalaryAccount extends Account {

}
