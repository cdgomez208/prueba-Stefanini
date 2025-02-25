package com.prueba.tecnica.backend.dtos;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonInclude;


@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder(toBuilder = true)
public class CreateProductDTO {



	private String nombre;

	private int precio;

	private int id_categoria;

	private int cantidad;


}
