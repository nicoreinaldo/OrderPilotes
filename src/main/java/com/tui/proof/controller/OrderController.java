package com.tui.proof.controller;

import com.tui.proof.dto.ClientDTO;
import com.tui.proof.dto.OrderDTO;
import com.tui.proof.model.Order;
import com.tui.proof.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("new")
    public ResponseEntity<Order> newOrder(@RequestBody OrderDTO order) {
        return orderService.create(order);
    }

    @PutMapping()
    public ResponseEntity<Order> updateOrder(@RequestBody OrderDTO order) {
        if(order.getNumber() == null){
            return new ResponseEntity("The number of the order is empty", HttpStatus.BAD_REQUEST);
        }else{
            return orderService.update(order);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Order>> getClient(@RequestParam(value = "firstName", required = false) String firstName,
                                               @RequestParam(value = "lastName", required = false) String lastName,
                                               @RequestParam(value = "user", required = false) String userName,
                                               @RequestParam(value = "email", required = false) String email){
        ClientDTO client = new ClientDTO(firstName,lastName,email,userName,null);
        List<Order> listOrder =  orderService.getClient(client);
        if(listOrder.isEmpty() || listOrder == null) return new ResponseEntity("Not finded results", HttpStatus.BAD_REQUEST);
        return new ResponseEntity(listOrder,HttpStatus.OK);
    }
}
