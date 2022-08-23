package com.tui.proof.service;

import com.tui.proof.dto.UserDTO;
import com.tui.proof.model.User;
import com.tui.proof.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserService {

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  ClientService clientService;

    public Boolean create(UserDTO user) {
        try{
            User newUser = new User();
            newUser.setUser(user.getUser());
            newUser.setPassword(user.getPassword());
            newUser.setEmail(user.getEmail());
            save(newUser);
            clientService.create(newUser, user.getFirstName(), user.getLastName(), user.getTelephone());
            return true;
        }catch (Exception e){
            log.info("No is posible insert user, error "+ e);
            return false;
        }
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByUser(String username, String pwd) {
        return userRepository.findByUserAndPassword(username,pwd);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

}
