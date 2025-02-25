package com.prueba.tecnica.backend.entities;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "\"productos\"", schema = "public")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "precio", nullable = false)
    private Integer precio;

    @Column(name="cantidad",nullable = false)
    private int cantidad;

    @Column(name = "id_categoria", nullable = false)
    private int  id_categoria;


}
