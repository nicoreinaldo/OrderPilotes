package com.tui.proof.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class UserDTO implements Serializable {
    private String user;
    private String password;
    private String token;
    private String email;
    private String firstName;
    private String lastName;
    private String telephone;
}
