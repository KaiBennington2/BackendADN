package com.ceiba.contrato.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.contrato.modelo.enums.PaquetesContratos;
import org.junit.jupiter.api.BeforeEach;
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
    void listarTodos() throws Exception {

        //arrange - act - assert
        LocalDate fechaCorte = LocalDate.of(2022, 5, 12);

        mockMvc.perform(get("/contratos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].nitCustomer", is("1234567")))
                .andExpect(jsonPath("$[0].contractTime", is(24)))
                .andExpect(jsonPath("$[0].coinType", is("USD")))
                .andExpect(jsonPath("$[0].trmApplied", is(3702.86)))
                .andExpect(jsonPath("$[0].packet", is(PaquetesContratos.PREMIUM.getName())))
                .andExpect(jsonPath("$[0].installDate", is(fechaInstalacion.format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$[0].cutOffDate", is(fechaCorte.format(DateTimeFormatter.ISO_DATE))));
    }

    @Test
    void listarPorId() throws Exception {

        // arrange
        LocalDate fechaCorte = LocalDate.of(2022, 5, 5);

        // act - assert
        mockMvc.perform(get("/contratos/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nitCustomer", is("0987654")))
                .andExpect(jsonPath("$.contractTime", is(12)))
                .andExpect(jsonPath("$.coinType", is("COP")))
                .andExpect(jsonPath("$.trmApplied", is(4020.52)))
                .andExpect(jsonPath("$.packet", is(PaquetesContratos.COMPACT.getName())))
                .andExpect(jsonPath("$.installDate", is(fechaInstalacion.format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$.cutOffDate", is(fechaCorte.format(DateTimeFormatter.ISO_DATE))));
    }

    @Test
    void listarPorNitRequestParam() throws Exception {

        LocalDate fechaCorte = LocalDate.of(2022, 6, 9);

        //arrange - act - assert
        mockMvc.perform(get("/contratos/op").param("nit", "1029384")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.contractTime", is(12)))
                .andExpect(jsonPath("$.coinType", is("USD")))
                .andExpect(jsonPath("$.trmApplied", is(3065.43)))
                .andExpect(jsonPath("$.packet", is(PaquetesContratos.BASIC.getName())))
                .andExpect(jsonPath("$.installDate", is(fechaInstalacion.format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$.cutOffDate", is(fechaCorte.format(DateTimeFormatter.ISO_DATE))));
    }

    @Test
    void obtenerDetallContratoExistenteRequestParam() throws Exception {

        // arrange
        LocalDate fechaCorte = LocalDate.of(2022, 5, 12);

        // act - assert
        mockMvc.perform(get("/contratos/op").param("detail", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())

                .andExpect(jsonPath("$.nitCustomer", is("1234567")))
                .andExpect(jsonPath("$.contractTime", is(24)))
                .andExpect(jsonPath("$.coinType", is("USD")))
                .andExpect(jsonPath("$.trmApplied", is(3702.86)))
                .andExpect(jsonPath("$.packet", is(PaquetesContratos.PREMIUM.getName())))
                .andExpect(jsonPath("$.installDate", is(fechaInstalacion.format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$.cutOffDate", is(fechaCorte.format(DateTimeFormatter.ISO_DATE))))
                .andExpect(jsonPath("$.contractValue", is(338)));

    }
}