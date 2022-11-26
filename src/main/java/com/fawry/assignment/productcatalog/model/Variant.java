package com.fawry.assignment.productcatalog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "variant")
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "types")
    private String type;
    private int quantity;
    private double price;
    @Column(name = "buying_limit")
    private int limit;





    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;


}
