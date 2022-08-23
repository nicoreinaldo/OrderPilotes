package com.tui.proof.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "CLIENT")
public class Client {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private BigInteger id;

  @OneToOne
  @JoinColumn(name="ID_USER",referencedColumnName="ID")
  private User id_user;

  @Column(name = "FIRST_NAME")
  private String firstName;

  @Column(name = "LAST_NAME")
  private String lastName;

  @Column(name = "TELEPHONE")
  private String telephone;
}
