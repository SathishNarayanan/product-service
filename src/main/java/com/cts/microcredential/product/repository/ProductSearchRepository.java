package com.cts.microcredential.product.repository;

import com.cts.microcredential.product.model.SKU;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSearchRepository extends CrudRepository<SKU, String> {
}
