package com.ceiba.contrato.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.builders.ClienteTestDataBuilder;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.contrato.builders.ContratoTestDataBuilder;
import com.ceiba.contrato.modelo.enums.PaquetesContratos;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContratoTest {


    private static final String CLIENTE_OBLIGATORIO = "El Cliente para generar un contrato es requisito obligatorio.";
    private static final String TIPO_MONEDA_OBLIGATORIO = "El tipo de moneda es requerido.";
    private static final String TRM_OBLIGATORIO = "La tasa de cambio es requerida.";
    private static final String FECHA_INSTALACION_OBLIGATORIO = "La Fecha Instalación es un requisito obligatorio.";

    @Test
    @DisplayName("Debería crear contrato de manera correcta")
    void crearContratoCorrectamente() {
        // arrange
        Cliente cliente = new ClienteTestDataBuilder().conNit("1234567").conId(1L).build();
        // act
        Contrato contrato = new ContratoTestDataBuilder()
                .conId(1L)
                .conCliente(cliente)
                .build();
        // assert
        assertEquals(1, contrato.getId());
        assertEquals(cliente, contrato.getCliente());
        assertEquals("1234567", contrato.getCliente().getNit());
        assertEquals("COP", contrato.getTipoMoneda());
        assertEquals(new BigDecimal("3702.86"), contrato.getTrmAplicada());
        assertEquals(PaquetesContratos.BASICO, contrato.getPaqueteContrato());
        assertEquals(now(), contrato.getFechaInstalacion());
    }

    @Test
    @DisplayName("Debería arrojar fecha de corte esperada según paquete Compacto")
    void calcularFechaCorteContratoPaqueteCompacto() {
        // arrange
        LocalDate fechaInstalacion = LocalDate.of(2022,4,15);
        LocalDate fechaCorteEsperada = LocalDate.of(2022, 6,24);
        // act
        Contrato contrato = new ContratoTestDataBuilder()
                .conPaquete("Compacto")
                .conFechaInstalacion(fechaInstalacion)
                .build();
        // assert
        assertEquals(fechaCorteEsperada, contrato.getFechaCorte());
    }

    @Test
    @DisplayName("Debería arrojar fecha de corte esperada según paquete Premium")
    void calcularFechaCorteContratoPaquetePremium() {
        // arrange
        LocalDate fechaInstalacion = LocalDate.of(2022,4,15);
        LocalDate fechaCorteEsperada = LocalDate.of(2022, 8,19);
        // act
        Contrato contrato = new ContratoTestDataBuilder()
                .conPaquete("Premium")
                .conFechaInstalacion(fechaInstalacion)
                .build();
        // assert
        assertEquals(fechaCorteEsperada, contrato.getFechaCorte());
    }

    @Test
    @DisplayName("Debería arrojar tiempo contrato en meses según paquete Basico")
    void calcularTiempoContratoMesesPaqueteBasico() {
        // arrange - act
        Contrato contrato = new ContratoTestDataBuilder()
                .conPaquete("Basico")
                .build();
        // assert
        assertEquals(48, contrato.getTiempoContratoMeses());
    }

    @Test
    @DisplayName("Debería lanzar exception por falta de campo obligatorio")
    void deberiaFallarSinCampoObligatorio() {
        // arrange - act - assert
        ContratoTestDataBuilder contratoTest = new ContratoTestDataBuilder().conCliente(null);

        BasePrueba.assertThrows(contratoTest::build, ExcepcionValorObligatorio.class, CLIENTE_OBLIGATORIO);

        contratoTest = new ContratoTestDataBuilder().conTipoMoneda(null);
        BasePrueba.assertThrows(contratoTest::build, ExcepcionValorObligatorio.class, TIPO_MONEDA_OBLIGATORIO);

        contratoTest = new ContratoTestDataBuilder().conTrmAplicada(null);
        BasePrueba.assertThrows(contratoTest::build, ExcepcionValorObligatorio.class, TRM_OBLIGATORIO);

        contratoTest = new ContratoTestDataBuilder().conFechaInstalacion(null);
        BasePrueba.assertThrows(contratoTest::build, ExcepcionValorObligatorio.class, FECHA_INSTALACION_OBLIGATORIO);

    }

}