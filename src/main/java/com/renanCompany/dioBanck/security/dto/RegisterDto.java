package com.renanCompany.dioBanck.security.dto;

import com.renanCompany.dioBanck.security.enums.Role;

public record RegisterDto(String login, String password, Role role) {

}
