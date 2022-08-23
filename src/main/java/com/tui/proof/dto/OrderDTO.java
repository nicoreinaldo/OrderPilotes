package com.tui.proof.dto;

import com.tui.proof.model.PilotesEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class OrderDTO implements Serializable {
    private BigInteger number;
    private AddressDTO address;
    private PilotesEnum count;
    private double total;
    private Timestamp time;
    private BigInteger clientId;
}
