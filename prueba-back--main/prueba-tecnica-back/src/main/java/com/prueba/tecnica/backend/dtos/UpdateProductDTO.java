package com.prueba.tecnica.backend.dtos;

import lombok.Data;

@Data
public class UpdateProductDTO {


    private String nombre;

    private int precio;

    private int cantidad;
    private int id_categoria;

}
