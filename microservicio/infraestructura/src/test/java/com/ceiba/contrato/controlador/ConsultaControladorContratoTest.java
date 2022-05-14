package com.ceiba.contrato.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.contrato.modelo.enums.PaquetesContratos;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorContrato.class)
@ContextConfiguration(classes = ApplicationMock.class)
class ConsultaControladorContratoTest {

    @Autowired
    private MockMvc mockMvc;

    private LocalDate fechaInstalacion;

    @BeforeEach
    void setUp() {
        fechaInstalacion = LocalDate.now();
    }

    @Test
    @DisplayName("Deberia integrar: listar todos los contratos de manera correcta")
    void listarTodos() throws Exception {
        // arrange
        LocalDate fechaCorte = LocalDate.of(2022, 5, 12);
        // act - assert
        mockMvc.perform(get("/contratos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nitCliente", is("1234567")))
                .andExpect(jsonPath("$[0].tiempoContratoMeses", is(24)))
                .andExpect(jsonPath("$[0].tipoMoneda", is("USD")))
                .andExpect(jsonPath("$[0].trmAplicada", is(3702.86)))
                .andExpect(jsonPath("$[0].paqueteContrato", is(PaquetesContratos.PREMIUM.getNombre())))
                .andExpect(jsonPath("$[0].fechaInstalacion", is(fechaInstalacion.format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$[0].fechaCorte", is(fechaCorte.format(DateTimeFormatter.ISO_DATE))));
    }

    @Test
    @DisplayName("Deberia integrar: listr contrato por id de manera correcta")
    void listarPorId() throws Exception {
        // arrange
        LocalDate fechaCorte = LocalDate.of(2022, 5, 5);
        // act - assert
        mockMvc.perform(get("/contratos/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nitCliente", is("0987654")))
                .andExpect(jsonPath("$.tiempoContratoMeses", is(12)))
                .andExpect(jsonPath("$.tipoMoneda", is("COP")))
                .andExpect(jsonPath("$.trmAplicada", is(4020.52)))
                .andExpect(jsonPath("$.paqueteContrato", is(PaquetesContratos.COMPACTO.getNombre())))
                .andExpect(jsonPath("$.fechaInstalacion", is(fechaInstalacion.format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$.fechaCorte", is(fechaCorte.format(DateTimeFormatter.ISO_DATE))));
    }

    @Test
    @DisplayName("Deberia integrar: listar contrato por nit [RequestParam] de manera correcta")
    void listarPorNitRequestParam() throws Exception {
        // arrange
        LocalDate fechaCorte = LocalDate.of(2022, 6, 9);
        // act - assert
        mockMvc.perform(get("/contratos/op").param("nit", "1029384")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.tiempoContratoMeses", is(12)))
                .andExpect(jsonPath("$.tipoMoneda", is("USD")))
                .andExpect(jsonPath("$.trmAplicada", is(3065.43)))
                .andExpect(jsonPath("$.paqueteContrato", is(PaquetesContratos.BASICO.getNombre())))
                .andExpect(jsonPath("$.fechaInstalacion", is(fechaInstalacion.format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$.fechaCorte", is(fechaCorte.format(DateTimeFormatter.ISO_DATE))));
    }

    @Test
    @DisplayName("Deberia integrar: obtener el detalle del contrato de manera correcta")
    void obtenerDetallContratoExistenteRequestParam() throws Exception {
        // arrange
        LocalDate fechaCorte = LocalDate.of(2022, 5, 12);
        // act - assert
        mockMvc.perform(get("/contratos/op").param("detail", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nitCliente", is("1234567")))
                .andExpect(jsonPath("$.tiempoContratoMeses", is(24)))
                .andExpect(jsonPath("$.tipoMoneda", is("USD")))
                .andExpect(jsonPath("$.trmAplicada", is(3702.86)))
                .andExpect(jsonPath("$.paqueteContrato", is(PaquetesContratos.PREMIUM.getNombre())))
                .andExpect(jsonPath("$.fechaInstalacion", is(fechaInstalacion.format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$.fechaCorte", is(fechaCorte.format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$.valorContrato", is(338)));

    }
}