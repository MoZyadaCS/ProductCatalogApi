package com.fawry.assignment.productcatalog.repository;

import com.fawry.assignment.productcatalog.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
