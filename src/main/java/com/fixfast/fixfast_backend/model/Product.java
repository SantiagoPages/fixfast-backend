package com.fixfast.fixfast_backend.model; // Paquete ajustado

import jakarta.persistence.*; 
import lombok.Data; 
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "productos")
@Data   
@NoArgsConstructor // Añadido: Constructor sin argumentos
@AllArgsConstructor // Añadido: Constructor con todos los argumentos
public class Product { // Nombre de clase ajustado

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column(name = "stock_actual")
    private Integer stockActual;

    private String proveedor;
}
