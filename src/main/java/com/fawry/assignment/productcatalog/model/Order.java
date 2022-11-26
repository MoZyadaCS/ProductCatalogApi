package com.fawry.assignment.productcatalog.model;

import lombok.Data;
import org.aspectj.weaver.ast.Var;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(mappedBy = "orders", fetch = FetchType.EAGER)
    private List<Product> products;

}
