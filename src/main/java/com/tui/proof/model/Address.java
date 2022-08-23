package com.tui.proof.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "ADDRESS")
public class Address {
  @Id
  @Column(name = "STREET")
  private String street;

  @Column(name = "POST_CODE")
  private Integer postcode;

  @Column(name = "CITY")
  private String city;

  @Column(name = "COUNTRY")
  private String country;
}
