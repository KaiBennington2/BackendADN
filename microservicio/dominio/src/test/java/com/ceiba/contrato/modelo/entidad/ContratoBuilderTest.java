package com.ceiba.contrato.modelo.entidad;

import com.ceiba.contrato.builders.ContratoTestDataBuilder;
import com.ceiba.contrato.modelo.enums.PaquetesContratos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContratoBuilderTest {

    private Contrato contratoBase;
    private Contrato contratoBuilder;

    @Test
    @DisplayName("Deberia crear contrato builder de manera correcta")
    void crearContratoBuilderCorrectamente() {
        // arrange
        contratoBase = new ContratoTestDataBuilder().conId(1L).build();
        contratoBuilder = new ContratoBuilder(contratoBase).setCreatedDate(LocalDateTime.now()).build();
        BigDecimal trmAplicada = new BigDecimal("3702.86");

        // arrange
        String toString = "Contrato:" + contratoBuilder.getId() + ": ->{" +
                " Nit cliente :" + contratoBuilder.getNitCustomer() + "," +
                " Duración contrato en meses :" + contratoBuilder.getContractTime() + "," +
                " Tipo de moneda :" + contratoBuilder.getCoinType() + "," +
                " Trm Aplicada :" + contratoBuilder.getTrmApplied() + "," +
                " Paquete :" + contratoBuilder.getPacket() + "," +
                " Fecha instalación :" + contratoBuilder.getInstallDate() + "," +
                " Fecha de corte :" + contratoBuilder.getCutOffDate() + "," +
                " Fecha de corte anterior :" + contratoBuilder.getCutOffBeforeDate() + " }";

        //act - assert
        assertEquals(1, contratoBuilder.getId());
        assertEquals("1234567", contratoBuilder.getNitCustomer());
        assertEquals("COP", contratoBuilder.getCoinType());
        assertEquals(trmAplicada, contratoBuilder.getTrmApplied());
        assertEquals(PaquetesContratos.BASIC.getName(), contratoBuilder.getPacket());
        assertEquals(now(), contratoBuilder.getInstallDate());
        assertEquals(toString, contratoBuilder.toString());
    }

}