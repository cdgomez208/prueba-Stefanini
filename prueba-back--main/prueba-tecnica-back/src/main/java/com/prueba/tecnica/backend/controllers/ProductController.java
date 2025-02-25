package com.prueba.tecnica.backend.controllers;

import java.net.URI;

import com.prueba.tecnica.backend.dtos.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.prueba.tecnica.backend.servicesImpl.ProductoServiceImpl;

import static com.prueba.tecnica.backend.utils.contants.*;

@RequestMapping("products")
@CrossOrigin(origins = "*")
@RestController
public class ProductController {

	@Autowired
	private ProductoServiceImpl productService;

	@RequestMapping(value = "getId/{idProduct}", method = RequestMethod.GET)
	public ResponseEntity<?> getProduct(@PathVariable int idProduct) {
		return ResponseEntity.ok(productService.getProductById(idProduct));
	}

	@ApiOperation(value = "deleteProduct")
	@RequestMapping(value = "delete/{idProducto}", method = RequestMethod.DELETE)
	public ResponseEntity<StatusDTO> deteleProduct(
			@PathVariable Integer idProducto
	) {
		try {
			productService.deteleProductById(idProducto);
			StatusDTO status = new StatusDTO(DELETE_PRODUCT, HttpStatus.NO_CONTENT.value());
		return new ResponseEntity<>(status,HttpStatus.NO_CONTENT);
		}catch (RuntimeException e){
			StatusDTO status = new StatusDTO(ERROR_DELETE +e.getMessage(),HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(status,HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@ApiOperation(value = "getAlls")
	@RequestMapping(value = "getAlls", method = RequestMethod.GET)
	public ResponseEntity<?> getAllProducts() {

		return ResponseEntity.ok(productService.getAllProduct());
	}

	@ApiOperation(value = "createProduct")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public ResponseEntity<StatusDTO> createProduct(@RequestBody CreateProductDTO createProductDTO) throws Exception {
		try {
			 productService.createProduct(createProductDTO);
			StatusDTO status = new StatusDTO(PRODUCT_CREATED, HttpStatus.CREATED.value());
			return new ResponseEntity<>(status, HttpStatus.CREATED);
		} catch (Exception e) {
			StatusDTO status = new StatusDTO(ERROR_CREATED + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);

		}

    }
	@ApiOperation(value = "updateProduct")
	@RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<StatusDTO> updateProduct(@PathVariable int id, @RequestBody UpdateProductDTO dto)
			throws Exception {
		try {
			 productService.updateProduct(dto, id);
			StatusDTO status = new StatusDTO(UPDATE_PRODUCT, HttpStatus.OK.value());
			return new ResponseEntity<>(status, HttpStatus.OK);
		} catch (Exception e) {
			StatusDTO status = new StatusDTO(ERROR_UPDATE_PRODUCT+e.getMessage(), HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<>(status, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		}



}