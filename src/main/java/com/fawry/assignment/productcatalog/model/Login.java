package com.fawry.assignment.productcatalog.model;

import lombok.CustomLog;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "username")
    private String userName;

    private String pass;

    @OneToOne
    private Customer customer;


}
