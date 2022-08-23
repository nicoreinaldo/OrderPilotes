package com.tui.proof.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ORDERS")
public class Order {
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger number;

  @ManyToOne(optional=false)
  @JoinColumn(name="ADDRESS_ID",referencedColumnName="STREET")
  private Address address_id;

  @Column(name = "COUNT")
  private Integer count;

  @Column(name = "TOTAL")
  private double total;

  @Column(name = "TIME")
  private Timestamp time;

  @ManyToOne(optional=false)
  @JoinColumn(name="CLIENT_ID",referencedColumnName="ID")
  private Client clientId;

}
