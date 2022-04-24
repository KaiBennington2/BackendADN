package com.ceiba.contrato.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.contrato.builders.ComandoContratoTestDataBuilder;
import com.ceiba.contrato.comando.dto.ComandoContrato;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorContrato.class)
@ContextConfiguration(classes = ApplicationMock.class)
class ComandoControladorContratoTest {

    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    private ComandoContrato contrato;

    @BeforeEach
    private void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    }

    @Test
    void crear() throws Exception {
        // arrange
        contrato = new ComandoContratoTestDataBuilder().build();
        // act - assert
        mocMvc.perform(post("/contratos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contrato)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 4}"));
    }

    @Test
    void crearSinFechaInstalacionDaException() throws Exception {
        // arrange
        contrato = new ComandoContratoTestDataBuilder().conFechaInstalacion(null).build();
        // - act - assert
        mocMvc.perform(post("/contratos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contrato)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje")
                        .value("La FECHA INSTALACIÓN es un requisito obligatorio."));
    }
}