package com.tui.proof.repository;

import com.tui.proof.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, BigInteger> {
    Optional<Order> findOrderByNumber(BigInteger Order);

    @Query("select ord from Order ord \n" +
            "join Client cli on cli.id = ord.clientId.id \n" +
            "join User usr on usr.user = cli.id_user \n" +
            "where (:firstName is null or cli.firstName like %:firstName%) \n" +
            "and (:lastName is null or cli.lastName like %:lastName%) \n" +
            "and (:user is null or usr.user like %:user%) \n" +
            "and (:email is null or usr.email like %:email%) ")
    List<Order> findByParam(@Param("firstName") String firstName,
                            @Param("lastName") String lastName,
                            @Param("user") String user,
                            @Param("email") String email);


    //List<Order> findByClientIdContainingIgnoreCase(String firstName);
    //List<Optional<Client>> findOrderByClientId_LastNameLike(String lastName);
}
