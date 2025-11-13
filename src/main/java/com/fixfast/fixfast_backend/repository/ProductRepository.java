package com.fixfast.fixfast_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fixfast.fixfast_backend.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
