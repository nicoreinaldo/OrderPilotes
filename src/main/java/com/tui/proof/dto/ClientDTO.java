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
public class ClientDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String user;
    private String telephone;
}
