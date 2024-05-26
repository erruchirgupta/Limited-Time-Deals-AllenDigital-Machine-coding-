package com.ruchir.demo.repository.service;

import com.ruchir.demo.constants.Messages;
import com.ruchir.demo.enums.ExceptionCode;
import com.ruchir.demo.exception.EntityNotFoundException;
import com.ruchir.demo.repository.model.Customer;
import com.ruchir.demo.repository.repo.CustomerRepository;
import com.ruchir.demo.repository.repo.readonly.CustomerReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepositoryService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerReadRepository customerReadRepository;

    public Optional<Customer> findCustomerById(Integer id){ return  customerReadRepository.findById(id); }

    public Optional<Customer> findCustomerByContact(Long contact){ return  customerReadRepository.findByContact(contact); }

    public Customer validateAndGetCustomerById(Integer customerId){
        return findCustomerById(customerId)
                .orElseThrow(()->
                        new EntityNotFoundException(ExceptionCode.R101,
                                String.format(Messages.ENTITY_NOT_FOUND, "Customer", "id", customerId)));
    }

    public Customer validateAndGetCustomerByContact(Long contact){
        return findCustomerByContact(contact)
                .orElseThrow(()->
                        new EntityNotFoundException(ExceptionCode.R101,
                                String.format(Messages.ENTITY_NOT_FOUND, "Customer", "contact", contact)));
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
