package com.prueba.tecnica.backend.mappers;

import java.util.Objects;

import com.prueba.tecnica.backend.dtos.CreateProductDTO;
import com.prueba.tecnica.backend.dtos.ProductDTO;
import com.prueba.tecnica.backend.dtos.UpdateProductDTO;
import com.prueba.tecnica.backend.entities.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper( imports = Objects.class )
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO map(Producto source);
    @InheritInverseConfiguration(name = "map")
    @Mapping(source= "idProducto", target= "idProducto")
    @Mapping(source= "nombre", target= "nombre")
    @Mapping(source= "precio", target= "precio")
    @Mapping(source= "id_categoria", target= "id_categoria")
    Producto map(ProductDTO source);


    CreateProductDTO mapCreate(Producto source);
    @InheritInverseConfiguration(name = "mapCreate")
    @Mapping(source= "nombre", target= "nombre")
    @Mapping(source= "precio", target= "precio")
    @Mapping(source= "id_categoria", target= "id_categoria")
    Producto mapCreate(CreateProductDTO source);


    UpdateProductDTO mapUpdate(Producto source);
    @InheritInverseConfiguration(name = "mapUpdate")
    @Mapping(source= "nombre", target= "nombre")
    @Mapping(source= "precio", target= "precio")
    @Mapping(source= "id_categoria", target= "id_categoria")
    Producto mapUpdate(UpdateProductDTO source);


}