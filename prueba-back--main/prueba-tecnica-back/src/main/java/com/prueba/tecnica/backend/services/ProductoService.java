package com.prueba.tecnica.backend.services;

import java.util.List;
import java.util.Optional;

import com.prueba.tecnica.backend.dtos.*;
import com.prueba.tecnica.backend.entities.Producto;


public interface ProductoService {


	ProductoResponse createProduct(CreateProductDTO createProductDTO) throws Exception;

	List<ProductDTO> getAllProduct();


	Optional<Producto> getProductById(int idUser);

	void deteleProductById(int idProducto);

	UpdateProductDTO updateProduct(UpdateProductDTO userDto, int idUser) throws Exception;
}
