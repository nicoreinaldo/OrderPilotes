package com.tui.proof.service;

import com.tui.proof.model.Address;
import com.tui.proof.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
