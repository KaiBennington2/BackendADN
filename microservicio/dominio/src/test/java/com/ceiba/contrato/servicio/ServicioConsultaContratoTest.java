package com.ceiba.contrato.servicio;

import com.ceiba.contrato.builders.ContratoTestDataBuilder;
import com.ceiba.contrato.modelo.entidad.Contrato;
import com.ceiba.contrato.puerto.dao.DaoContrato;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ServicioConsultaContratoTest {

    private Contrato contrato;
    private ServicioConsultaContrato servicioConsultaContrato;
    private DaoContrato daoContrato;

    @Test
    @DisplayName("Deberia devolver TRUE por existencia de cliente")
    void existsByNit() {
        // arrange
        contrato = new ContratoTestDataBuilder().build();
        daoContrato = Mockito.mock(DaoContrato.class);
        servicioConsultaContrato = new ServicioConsultaContrato(daoContrato);
        when(daoContrato.existsByNit(contrato.getCliente().getNit())).thenReturn(Boolean.TRUE);
        // act
        Boolean expected = servicioConsultaContrato.existsByNit(contrato.getCliente().getNit());
        // assert
        assertEquals(Boolean.TRUE, expected);
    }
}