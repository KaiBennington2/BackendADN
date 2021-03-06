package com.ceiba.contrato.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.contrato.builders.DtoContratoBuilder;
import com.ceiba.contrato.modelo.dto.DtoContrato;
import com.ceiba.contrato.modelo.dto.DtoContratoDetalle;
import com.ceiba.contrato.puerto.dao.DaoContrato;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ServicioDetalleContratoTest {

    private static final String NO_EXISTE_EL_CONTRATO = "El contrato no existe en el sistema.";

    private ServicioDetalleContrato servicioDetalleContrato;

    @Test
    @DisplayName("Debería mostrar el valor del detalle contrato de manera correcta")
    void deberiaMostrarElDetalleDeContratoConValorCorrecto() {
        // arrange
        LocalDate fechaInstalacionBase = LocalDate.of(2022, 4, 22);
        DtoContrato dtoContrato = new DtoContratoBuilder().conId(1L).build();
        DaoContrato daoContrato = Mockito.mock(DaoContrato.class);
        when(daoContrato.existsById(anyLong())).thenReturn(true);
        when(daoContrato.findById(anyLong())).thenReturn(dtoContrato);
        servicioDetalleContrato = new ServicioDetalleContrato(daoContrato);
        // act
        DtoContratoDetalle dtoContratoDetalle = servicioDetalleContrato.ejecutar(dtoContrato.getId());
        // assert
        assertEquals(new BigDecimal(338), dtoContratoDetalle.getValorContrato());
    }

    @Test
    @DisplayName("Deberia lanzar excepcion por existencia de contrato")
    void deberiaLanzarExcepcionPorExistenciaPreviaDeContrato() {
        // arrange
        DaoContrato daoContrato = Mockito.mock(DaoContrato.class);
        when(daoContrato.existsById(anyLong())).thenReturn(false);
        servicioDetalleContrato = new ServicioDetalleContrato(daoContrato);
        // act - assert
        BasePrueba.assertThrows(() -> servicioDetalleContrato.ejecutar(1L),
                ExcepcionSinDatos.class,
                NO_EXISTE_EL_CONTRATO);
    }

}