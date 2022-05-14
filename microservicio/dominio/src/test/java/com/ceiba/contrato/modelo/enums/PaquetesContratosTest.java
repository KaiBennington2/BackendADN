package com.ceiba.contrato.modelo.enums;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaquetesContratosTest {

    private static final String PAQUETE_CONTRATO_INVALIDO = "El paquete de contrato es inválido.";

    @Test
    @DisplayName("Debería devolver un paquete de contrato valido")
    void deberiaDevolverUnPaqueteDeContratoValido() {
        // arrange
        String nombrePaqueteContrato = "Compacto";
        // act - assert
        assertEquals(PaquetesContratos.COMPACTO, PaquetesContratos.getByName(nombrePaqueteContrato));
    }

    @Test
    @DisplayName("Debería devolver el porcentaje aplicado al paquete seleccionado")
    void deberiaDevolverPorcentajeAplicadoAlPaquete() {
        // arrange
        String nombrePaqueteContrato = "Premium";
        // act - assert
        assertEquals(new BigDecimal(25), PaquetesContratos.getByName(nombrePaqueteContrato).getPorcentajeBaseAplicado());
    }

    @Test
    @DisplayName("Debería devolver el nombre del paquete seleccionado")
    void deberiaDevolverNombrePaqueteSeleccionado() {
        // arrange
        String nombrePaqueteContrato = "Basico";
        String nombreEsperado = "BASIC";
        // act - assert
        assertEquals(nombreEsperado, PaquetesContratos.getByName(nombrePaqueteContrato).getNombre());
    }

    @Test
    @DisplayName("Debería lanzar excepcion por paquete de contrato no valido")
    void deberiaLanzarExcepcionPorPaqueteNoValido() {
        // arrange
        String nombrePaqueteContrato = "CUALQUIERA";
        // act - assert
        BasePrueba.assertThrows(() -> PaquetesContratos.getByName(nombrePaqueteContrato),
                ExcepcionValorInvalido.class,
                PAQUETE_CONTRATO_INVALIDO);
    }
}