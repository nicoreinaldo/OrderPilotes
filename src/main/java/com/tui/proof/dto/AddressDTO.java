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
public class AddressDTO implements Serializable {
    private String street;
    private Integer postcode;
    private String city;
    private String country;
}
