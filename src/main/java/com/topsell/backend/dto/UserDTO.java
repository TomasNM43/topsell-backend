package com.topsell.backend.dto;

import com.topsell.backend.entity.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Role role;
}