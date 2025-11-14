package com.fixfast.fixfast_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fixfast.fixfast_backend.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 2. Extiende JpaRepository. ¡Esto es lo MÁGICO!
    
    // Aquí no necesitamos escribir ningún código.
    // Spring Data JPA ya nos da automáticamente métodos como:
    // - save(Product product): Guarda o actualiza un producto.
    // - findAll(): Lista todos los productos.
    // - findById(Long id): Busca un producto por su ID.
    // - deleteById(Long id): Borra un producto por su ID.
}
