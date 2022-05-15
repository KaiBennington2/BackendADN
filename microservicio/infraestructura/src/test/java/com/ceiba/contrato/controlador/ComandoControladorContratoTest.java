package com.ceiba.contrato.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.contrato.builders.ComandoContratoTestDataBuilder;
import com.ceiba.contrato.comando.dto.ComandoContrato;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorContrato.class)
@ContextConfiguration(classes = ApplicationMock.class)
class ComandoControladorContratoTest {

    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @BeforeEach
    private void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    }

    @Test
    @DisplayName("Debería integrar: crear contratos de manera correcta")
    void crear() throws Exception {
        // arrange
        ComandoContrato contrato = new ComandoContratoTestDataBuilder().build();
        // act - assert
        mocMvc.perform(post("/contratos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contrato)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 4}"));
    }

    @Test
    @DisplayName("Deberia dar excepcion al intentar crear sin campo fecha instalacion obligatorio.")
    void intentarCrearSinFechaInstalacionDaException() throws Exception {
        // arrange
        ComandoContrato contrato = new ComandoContratoTestDataBuilder().conFechaInstalacion(null).build();
        // - act - assert
        mocMvc.perform(post("/contratos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contrato)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje")
                        .value("La Fecha Instalación es un requisito obligatorio."));
    }

    @Test
    @DisplayName("Deberia integrar: eliminar un contrato de manera correcta")
    void eliminar() throws Exception {
        // arrange
        // act - assert
        mocMvc.perform(delete("/contratos/4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(get("/contratos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }
}