package com.ceiba.contrato.modelo.enums;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaquetesContratosTest {

    private static final String PAQUETE_CONTRATO_INVALIDO = "El paquete de contrato es inválido.";

    @Test
    @DisplayName("Deberia devolver un paquete de contrato valido")
    void deberiaDevolverUnPaqueteDeContratoValido() {
        // arrange
        String nombrePaqueteContrato = "COMPACT";
        // act - assert
        assertEquals(PaquetesContratos.COMPACT, PaquetesContratos.getByName(nombrePaqueteContrato));
    }

    @Test
    @DisplayName("Deberia lanzar excepcion por paquete de contrato no valido")
    void deberiaLanzarExcepcionPorPaqueteNoValido() {
        // arrange
        String nombrePaqueteContrato = "CUALQUIERA";
        // act - assert
        BasePrueba.assertThrows(() -> PaquetesContratos.getByName(nombrePaqueteContrato),
                ExcepcionValorInvalido.class,
                PAQUETE_CONTRATO_INVALIDO);
    }
}