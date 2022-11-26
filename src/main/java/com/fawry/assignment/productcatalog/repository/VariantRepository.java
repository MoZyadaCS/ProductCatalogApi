package com.fawry.assignment.productcatalog.repository;

import com.fawry.assignment.productcatalog.model.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {


    @Query("select v from Variant v where v.product.id = ?1")
    List<Variant> getAllByProductId(Long id);

    void deleteAllByIdIn(List<Long> ids);
}
