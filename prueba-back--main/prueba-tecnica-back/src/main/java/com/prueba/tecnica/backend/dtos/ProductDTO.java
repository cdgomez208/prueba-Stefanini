package com.prueba.tecnica.backend.dtos;

import lombok.Data;

@Data
public class ProductDTO {

private int idProducto;
    private String nombre;

    private int precio;

    private int id_categoria;

    private int cantidad;

}
