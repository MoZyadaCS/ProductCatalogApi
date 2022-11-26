package com.fawry.assignment.productcatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameAr;
    private String nameEn;

    private Byte[] image;

    private int buyingCount;

    @OneToMany(mappedBy = "product")
    private List<Variant> variants;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_has_orders", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "orders_id"))
    @JsonIgnore
    private List<Order> orders;


}
