package com.fawry.assignment.productcatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phone;

    @OneToOne
    @JoinColumn(name = "login_id")
    @JsonIgnore
    private Login login;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Order> orders;

}
