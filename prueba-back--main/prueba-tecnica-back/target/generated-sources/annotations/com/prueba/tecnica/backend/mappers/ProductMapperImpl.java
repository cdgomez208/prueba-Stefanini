package com.prueba.tecnica.backend.mappers;

import com.prueba.tecnica.backend.dtos.CreateProductDTO;
import com.prueba.tecnica.backend.dtos.CreateProductDTO.CreateProductDTOBuilder;
import com.prueba.tecnica.backend.dtos.ProductDTO;
import com.prueba.tecnica.backend.dtos.UpdateProductDTO;
import com.prueba.tecnica.backend.entities.Producto;
import java.util.Objects;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-25T16:33:23-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO map(Producto source) {
        if ( source == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        if ( source.getIdProducto() != null ) {
            productDTO.setIdProducto( source.getIdProducto() );
        }
        productDTO.setNombre( source.getNombre() );
        if ( source.getPrecio() != null ) {
            productDTO.setPrecio( source.getPrecio() );
        }
        productDTO.setId_categoria( source.getId_categoria() );
        productDTO.setCantidad( source.getCantidad() );

        return productDTO;
    }

    @Override
    public Producto map(ProductDTO source) {
        if ( source == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setIdProducto( source.getIdProducto() );
        producto.setNombre( source.getNombre() );
        producto.setPrecio( source.getPrecio() );
        producto.setId_categoria( source.getId_categoria() );
        producto.setCantidad( source.getCantidad() );

        return producto;
    }

    @Override
    public CreateProductDTO mapCreate(Producto source) {
        if ( source == null ) {
            return null;
        }

        CreateProductDTOBuilder createProductDTO = CreateProductDTO.builder();

        createProductDTO.nombre( source.getNombre() );
        if ( source.getPrecio() != null ) {
            createProductDTO.precio( source.getPrecio() );
        }
        createProductDTO.id_categoria( source.getId_categoria() );
        createProductDTO.cantidad( source.getCantidad() );

        return createProductDTO.build();
    }

    @Override
    public Producto mapCreate(CreateProductDTO source) {
        if ( source == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setNombre( source.getNombre() );
        producto.setPrecio( source.getPrecio() );
        producto.setId_categoria( source.getId_categoria() );
        producto.setCantidad( source.getCantidad() );

        return producto;
    }

    @Override
    public UpdateProductDTO mapUpdate(Producto source) {
        if ( source == null ) {
            return null;
        }

        UpdateProductDTO updateProductDTO = new UpdateProductDTO();

        updateProductDTO.setNombre( source.getNombre() );
        if ( source.getPrecio() != null ) {
            updateProductDTO.setPrecio( source.getPrecio() );
        }
        updateProductDTO.setCantidad( source.getCantidad() );
        updateProductDTO.setId_categoria( source.getId_categoria() );

        return updateProductDTO;
    }

    @Override
    public Producto mapUpdate(UpdateProductDTO source) {
        if ( source == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setNombre( source.getNombre() );
        producto.setPrecio( source.getPrecio() );
        producto.setId_categoria( source.getId_categoria() );
        producto.setCantidad( source.getCantidad() );

        return producto;
    }
}
