package com.ceiba.cliente.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.cliente.builders.ComandoClienteTestDataBuilder;
import com.ceiba.cliente.comando.dto.ComandoCliente;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorCliente.class)
@ContextConfiguration(classes = ApplicationMock.class)
class ComandoControladorClienteTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private ComandoCliente comandoCliente;

    @Test
    @DisplayName("Deberia integrar: crear cliente de manera correcta")
    void create() throws Exception {
        // arrange
        comandoCliente = new ComandoClienteTestDataBuilder().build();
        // act - assert
        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCliente)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 6}"));
    }

    @Test
    @DisplayName("Deberia integrar: actualizar cliente de manera correcta")
    void update() throws Exception {
        // arrange
        comandoCliente = new ComandoClienteTestDataBuilder().conId(1L).conNit("1234567").build();
        // act - assert
        mockMvc.perform(put("/clientes/{id}",comandoCliente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(comandoCliente)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia integrar: eliminar un cliente de manera correcta")
    void deletee() throws Exception {
        // arrange
        comandoCliente = new ComandoClienteTestDataBuilder().conId(5L).conNit("123465").build();
        // act - assert
        mockMvc.perform(delete("/clientes/{id}", comandoCliente.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

}