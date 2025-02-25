package test;


import com.prueba.tecnica.backend.dtos.ProductDTO;
import com.prueba.tecnica.backend.dtos.UpdateProductDTO;
import com.prueba.tecnica.backend.dtos.CreateProductDTO;
import com.prueba.tecnica.backend.mappers.ProductMapper;
import com.prueba.tecnica.backend.servicesImpl.ProductoServiceImpl;
import com.prueba.tecnica.backend.entities.Producto;
import com.prueba.tecnica.backend.repositories.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductoServiceImpl productoService;

    private Producto producto;
    private CreateProductDTO createProductDTO;
    private ProductDTO productDTO;
    private UpdateProductDTO updateProductDTO;

    @BeforeEach
    void setUp() {
        producto = new Producto();
        producto.setIdProducto(1);
        producto.setNombre("Producto 1");
        producto.setPrecio(100);
        producto.setCantidad(10);

        productDTO = new ProductDTO();
        productDTO.setNombre("Producto 1");
        productDTO.setPrecio(100);
        productDTO.setCantidad(10);

        updateProductDTO = new UpdateProductDTO();
        updateProductDTO.setNombre("Producto Actualizado");
        updateProductDTO.setPrecio(200);
        updateProductDTO.setCantidad(20);
    }



    @Test
    void getAllProductTest() {
        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto));
        when(productMapper.map(any(Producto.class))).thenReturn(productDTO);

        List<ProductDTO> productos = productoService.getAllProduct();

        assertNotNull(productos);
        assertEquals(1, productos.size());
        verify(productoRepository, times(1)).findAll();
    }

    @Test
    void getProductByIdTest() {
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        Optional<Producto> result = productoService.getProductById(1);

        assertTrue(result.isPresent());
        assertEquals(producto.getNombre(), result.get().getNombre());
        verify(productoRepository, times(1)).findById(1);
    }

    @Test
    void deleteProductByIdTest() {
        doNothing().when(productoRepository).deleteById(1);

        productoService.deteleProductById(1);

        verify(productoRepository, times(1)).deleteById(1);
    }

    @Test
    void updateProductTest() throws Exception {
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));
        when(productoRepository.save(any(Producto.class))).thenReturn(producto);
        when(productMapper.mapUpdate(any(Producto.class))).thenReturn(updateProductDTO);

        UpdateProductDTO result = productoService.updateProduct(updateProductDTO, 1);

        assertNotNull(result);
        assertEquals(updateProductDTO.getNombre(), result.getNombre());
        verify(productoRepository, times(1)).findById(1);
        verify(productoRepository, times(1)).save(any(Producto.class));
    }

    @Test
    void updateProductNotFoundTest() {
        when(productoRepository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productoService.updateProduct(updateProductDTO, 1);
        });

        assertEquals("Producto no encontrado con ID", exception.getMessage());
        verify(productoRepository, times(1)).findById(1);
    }
}