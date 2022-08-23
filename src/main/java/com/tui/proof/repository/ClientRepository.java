package com.tui.proof.repository;

import com.tui.proof.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, BigInteger> {

    Optional<Client> findClientById(BigInteger clientId);
     List<Optional<Client>> findByFirstNameLikeIgnoreCase(String firstName);
    //List<Optional<Client>> findClientByLastNameLike(String lastName);
}
