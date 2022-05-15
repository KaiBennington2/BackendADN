package com.ceiba.cliente.controlador;

import com.ceiba.ApplicationMock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorCliente.class)
@ContextConfiguration(classes = ApplicationMock.class)
class ConsultaControladorClienteTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Debería listar todos los clientes")
    void listarTodos() throws Exception {
        // arrange
        // act - assert
        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].nitCliente", is("1234567")))
                .andExpect(jsonPath("$[0].razonSocial", is("Pruebas S.A.S.")))
                .andExpect(jsonPath("$[0].nombreRepresentante", is("Kai Bennington")))
                .andExpect(jsonPath("$[0].telefono", is("3013101550")))
                .andExpect(jsonPath("$[0].direccion", is("Cll 000 # 01 - 02")));
    }

    @Test
    @DisplayName("Debería listar un cliente con el id dado")
    void listarPorId() throws Exception {
        // arrange
        // act - assert
        mockMvc.perform(get("/clientes/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nitCliente", is("0987654")))
                .andExpect(jsonPath("$.razonSocial", is("Tests S.A.S.")))
                .andExpect(jsonPath("$.nombreRepresentante", is("Alonso Bennington")))
                .andExpect(jsonPath("$.telefono", is("3023111660")))
                .andExpect(jsonPath("$.direccion", is("Cll 111 # 03 - 04")));
    }

    @Test
    @DisplayName("Debería lanzar excepcion por id no existente")
    void deberiaLanzarExceptionPorIdNoExistente() throws Exception {
        // arrange
        // act - assert
        mockMvc.perform(get("/clientes/12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

        mockMvc.perform(get("/clientes/find?nit=101")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("Debería listar cliente con el nit dado")
    void listarPorNit() throws Exception {
        // arrange
        // act - assert
        mockMvc.perform(get("/clientes/find?nit=1029384")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.razonSocial", is("Empresa S.A.S.")))
                .andExpect(jsonPath("$.nombreRepresentante", is("Cliente Prueba")))
                .andExpect(jsonPath("$.telefono", is("3023013065")))
                .andExpect(jsonPath("$.direccion", is("Cll 222 # 13 - 24")));
    }
}