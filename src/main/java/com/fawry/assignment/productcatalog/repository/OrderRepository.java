package com.fawry.assignment.productcatalog.repository;

import com.fawry.assignment.productcatalog.model.Customer;
import com.fawry.assignment.productcatalog.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> getAllByCustomer_Id(Long id);
}
