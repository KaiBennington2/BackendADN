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
    @DisplayName("Deberia listar todos los clientes")
    void listarTodos() throws Exception {
        // arrange
        // act - assert
        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].nitCustomer", is("1234567")))
                .andExpect(jsonPath("$[0].companyName", is("Pruebas S.A.S.")))
                .andExpect(jsonPath("$[0].representantName", is("Kai Bennington")))
                .andExpect(jsonPath("$[0].phone", is("3013101550")))
                .andExpect(jsonPath("$[0].adress", is("Cll 000 # 01 - 02")));
    }

    @Test
    @DisplayName("Deberia listar un cliente con el id dado")
    void listarPorId() throws Exception {
        // arrange
        // act - assert
        mockMvc.perform(get("/clientes/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nitCustomer", is("0987654")))
                .andExpect(jsonPath("$.companyName", is("Tests S.A.S.")))
                .andExpect(jsonPath("$.representantName", is("Alonso Bennington")))
                .andExpect(jsonPath("$.phone", is("3023111660")))
                .andExpect(jsonPath("$.adress", is("Cll 111 # 03 - 04")));
    }

    @Test
    @DisplayName("Deberia lanzar excepcion por id no existente")
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
    @DisplayName("Deberia listar cliente con el nit dado")
    void listarPorNit() throws Exception {
        // arrange
        // act - assert
        mockMvc.perform(get("/clientes/find?nit=1029384")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.companyName", is("Empresa S.A.S.")))
                .andExpect(jsonPath("$.representantName", is("Cliente Prueba")))
                .andExpect(jsonPath("$.phone", is("3023013065")))
                .andExpect(jsonPath("$.adress", is("Cll 222 # 13 - 24")));
    }
}