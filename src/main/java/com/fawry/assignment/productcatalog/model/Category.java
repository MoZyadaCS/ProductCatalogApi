package com.fawry.assignment.productcatalog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

    private String name;
    private String description;


    @OneToMany(mappedBy = "category" , fetch = FetchType.EAGER)
    private List<Product> products;



}
