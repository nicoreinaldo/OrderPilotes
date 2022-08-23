package com.tui.proof.service;

import com.tui.proof.dto.ClientDTO;
import com.tui.proof.dto.OrderDTO;
import com.tui.proof.model.Address;
import com.tui.proof.model.Client;
import com.tui.proof.model.Order;
import com.tui.proof.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private AddressService addressService;

    public ResponseEntity create(OrderDTO order) {
        try {
            Order newOrder = new Order();
            Address address = new Address();
            Optional<Client> client = clientService.findClient(order.getClientId());
            if (client.isPresent()) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                address.setStreet(order.getAddress().getStreet());
                address.setCity(order.getAddress().getCity());
                address.setCountry(order.getAddress().getCountry());
                address.setPostcode(order.getAddress().getPostcode());
                addressService.save(address);

                newOrder.setAddress_id(address);
                newOrder.setCount(order.getCount().value);
                newOrder.setTime(timestamp);
                newOrder.setClientId(client.get());
                newOrder.setTotal(order.getCount().value * 1.33);
                save(newOrder);
                return new ResponseEntity<>(newOrder, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("The client not exist", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            log.info("No is posible insert order, error " + e);
            return new ResponseEntity<>("No is posible insert order" + e, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity update(OrderDTO order) {
        Optional<Order> oldOrder = orderRepository.findOrderByNumber(order.getNumber());
        if (oldOrder.isPresent()) {
            if (checkTime(oldOrder.get())) {
                if (checkAddres(order, oldOrder.get()) && checkCount(order, oldOrder.get())) {
                    if (!order.getClientId().equals(oldOrder.get().getClientId().getId())) {
                        return new ResponseEntity<>("It is not possible to assign an order from another customer", HttpStatus.BAD_REQUEST);
                    }
                    return new ResponseEntity<>("No data to modify", HttpStatus.BAD_REQUEST);
                } else {
                    if (!checkAddres(order, oldOrder.get())) {
                        Address address = new Address();
                        address.setPostcode(order.getAddress().getPostcode());
                        address.setCity(order.getAddress().getCity());
                        address.setStreet(order.getAddress().getStreet());
                        address.setCountry(order.getAddress().getCountry());
                        addressService.save(address);
                        oldOrder.get().setAddress_id(address);
                    }
                    if (!checkCount(order, oldOrder.get())) {
                        oldOrder.get().setCount(order.getCount().value);
                        oldOrder.get().setTotal(order.getCount().value * 1.33);
                    }
                    oldOrder.get().setTime(new Timestamp(System.currentTimeMillis()));
                    save(oldOrder.get());
                    return new ResponseEntity<>(oldOrder, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("Not be possible to modify any data of\n" +
                        "the order because Miquel will be occupied cooking the pilotes", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("The number of order no exist", HttpStatus.BAD_REQUEST);
        }
    }

    public List<Order> getClient(ClientDTO client) {
        return orderRepository.findByParam(client.getFirstName(),client.getLastName(),client.getUser(),client.getEmail());
    }

    private Boolean checkTime(Order old) {
        Long oldTime = old.getTime().getTime();
        Long currentTime = new Timestamp(System.currentTimeMillis()).getTime();
        long diff = currentTime - oldTime;
        long diffMinutes = diff / (60 * 1000);
        return diffMinutes <= 5 ? true : false;
    }

    private Boolean checkCount(OrderDTO order, Order oldOrder) {
        return oldOrder.getCount().equals(order.getCount().value) ? true : false;
    }

    private Boolean checkAddres(OrderDTO order, Order oldOrder) {
        if (!oldOrder.getAddress_id().getStreet().equals(order.getAddress().getStreet())) return false;
        if (!oldOrder.getAddress_id().getCity().equals(order.getAddress().getCity())) return false;
        if (!oldOrder.getAddress_id().getCountry().equals(order.getAddress().getCountry())) return false;
        if (!oldOrder.getAddress_id().getPostcode().equals(order.getAddress().getPostcode())) return false;
        return true;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

}
