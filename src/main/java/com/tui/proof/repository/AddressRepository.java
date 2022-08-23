package com.tui.proof.repository;

import com.tui.proof.model.Address;
import com.tui.proof.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
}
