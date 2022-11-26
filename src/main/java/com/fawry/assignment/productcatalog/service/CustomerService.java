package com.fawry.assignment.productcatalog.service;

import com.fawry.assignment.productcatalog.model.Customer;
import com.fawry.assignment.productcatalog.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }
}
