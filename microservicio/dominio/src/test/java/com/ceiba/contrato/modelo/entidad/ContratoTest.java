package com.ceiba.contrato.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.contrato.builders.ContratoTestDataBuilder;
import com.ceiba.contrato.modelo.enums.PaquetesContratos;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ContratoTest {

    private static final String NIT_OBLIGATORIO = "El NIT es un requisito obligatorio.";
    private static final String COIN_TYPE_OBLIGATORIO = "El tipo de moneda es requerido.";
    private static final String TRM_OBLIGATORIO = "La tasa de cambio es requerida.";
    private static final String INSTALLDATE_OBLIGATORIO = "La FECHA INSTALACIÓN es un requisito obligatorio.";

    private Contrato contrato;

    @Test
    @DisplayName("Deberia crear contrato de manera correcta")
    void crearContratoCorrectamente() {
        // arrange
        BigDecimal trmAplicada = new BigDecimal("3702.86");
        contrato = new ContratoTestDataBuilder().conId(1L).build();
        // arrange
        String toString = "Contrato:" + contrato.getId() + ": ->{" +
                " Nit cliente :" + contrato.getNitCustomer() + "," +
                " Duración contrato en meses :" + contrato.getContractTime() + "," +
                " Tipo de moneda :" + contrato.getCoinType() + "," +
                " Trm Aplicada :" + contrato.getTrmApplied() + "," +
                " Paquete :" + contrato.getPacket() + "," +
                " Fecha instalación :" + contrato.getInstallDate() + "," +
                " Fecha de corte :" + contrato.getCutOffDate() + "," +
                " Fecha de corte anterior :" + contrato.getCutOffBeforeDate() + " }";

        //act - assert
        assertEquals(1, contrato.getId());
        assertEquals("1234567", contrato.getNitCustomer());
        assertEquals("COP", contrato.getCoinType());
        assertEquals(trmAplicada, contrato.getTrmApplied());
        assertEquals(PaquetesContratos.BASIC.getName(), contrato.getPacket());
        assertEquals(now(), contrato.getInstallDate());
        assertEquals(toString, contrato.toString());
    }

    @Test
    @DisplayName("Deberia lanzar exception por falta de campo obligatorio")
    void deberiaFallarSinCampoObligatorio() {
        // arrange - act - assert
        ContratoTestDataBuilder contratoTest = new ContratoTestDataBuilder().conNit(null);

        BasePrueba.assertThrows(contratoTest::build, ExcepcionValorObligatorio.class, NIT_OBLIGATORIO);

        contratoTest = new ContratoTestDataBuilder().conTipoMoneda(null);
        BasePrueba.assertThrows(contratoTest::build, ExcepcionValorObligatorio.class, COIN_TYPE_OBLIGATORIO);

        contratoTest = new ContratoTestDataBuilder().conTrmAplicada(null);
        BasePrueba.assertThrows(contratoTest::build, ExcepcionValorObligatorio.class, TRM_OBLIGATORIO);

        contratoTest = new ContratoTestDataBuilder().conFechaInstalacion(null);
        BasePrueba.assertThrows(contratoTest::build, ExcepcionValorObligatorio.class, INSTALLDATE_OBLIGATORIO);

    }

}