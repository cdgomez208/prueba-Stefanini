package com.prueba.tecnica.backend.servicesImpl;

import com.prueba.tecnica.backend.dtos.*;
import com.prueba.tecnica.backend.entities.Producto;
import com.prueba.tecnica.backend.mappers.ProductMapper;
import com.prueba.tecnica.backend.repositories.ProductoRepository;
import com.prueba.tecnica.backend.services.ProductoService;
import java.util.*;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    ProductMapper productMapper;


    private Producto convertirEntidad(CreateProductDTO dto){
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setCantidad(dto.getCantidad());
        return producto;

    }

    @Override
    public ProductoResponse createProduct(CreateProductDTO createProductDTO) throws Exception {
        Producto producto = convertirEntidad(createProductDTO);

        ProductoResponse productoResponse = new ProductoResponse();
        producto = productoRepository.save(producto);
        ProductDTO productDTO= convertirResponseDto(producto);
        productoResponse.setProducto(productDTO);
        return productoResponse;
    }

private ProductDTO convertirResponseDto(Producto producto){
    ProductDTO productDTO= new ProductDTO();

    producto.setIdProducto(producto.getIdProducto());
    productDTO.setNombre(producto.getNombre());
            productDTO.setPrecio(producto.getPrecio());
            productDTO.setCantidad(producto.getCantidad());
            return productDTO;
}

    @Override
    public List<ProductDTO> getAllProduct() {
        return productoRepository.findAll().stream().map((user) -> mapToDto(user)
        ).collect(Collectors.toList());
    }



    @Override
    public Optional<Producto> getProductById(int idProducto) {

        return productoRepository.findById(idProducto);
    }

    @Override
    public void deteleProductById(int idProducto) {
         productoRepository.deleteById(idProducto);
    }

    private ProductDTO mapToDto(Producto producto) {
        ProductDTO dto = productMapper.map(producto);
        return dto;

    }

    @Override
    @Transactional
    public UpdateProductDTO updateProduct(UpdateProductDTO updateProductDTO, int IdProducto) throws Exception {

     return productoRepository.findById(IdProducto)
             .map(productoExistente ->
                     {productoExistente.setNombre(updateProductDTO.getNombre());
                         productoExistente.setPrecio(updateProductDTO.getPrecio());
                         productoExistente.setCantidad(updateProductDTO.getCantidad());
                         Producto productoActualizado = productoRepository.save(productoExistente);
                         return productMapper.mapUpdate(productoActualizado);
                     })
             .orElseThrow(()-> new RuntimeException("Producto no encontrado con ID"));

    }



}
