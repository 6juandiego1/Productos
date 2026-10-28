package com.aws.cognito.productos;

import com.aws.cognito.productos.controller.ProductoController;
import com.aws.cognito.productos.dto.ProductoDTO;
import com.aws.cognito.productos.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductosApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Spy
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    private ProductoDTO productoDTO;

    @BeforeEach
    void setUp() {
        // Construye MockMvc manualmente para este controlador
        mockMvc = MockMvcBuilders.standaloneSetup(productoController).build();
    }

    @Test
    //@WithMockUser(username = "juan.perez", authorities = {"ROLE_Reader", "ROLE_writer"})
    void getProductById_ShouldReturn200() throws Exception {
        productoDTO = new ProductoDTO();
        productoDTO.setId("1");
        productoDTO.setNombre("Balon de futbol");
        productoService.save(productoDTO);

        mockMvc.perform(get("/productos/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Balon de futbol"));
    }

}
