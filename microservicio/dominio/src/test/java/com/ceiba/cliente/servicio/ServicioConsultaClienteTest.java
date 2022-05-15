package com.ceiba.cliente.servicio;

import com.ceiba.cliente.builders.ClienteTestDataBuilder;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ServicioConsultaClienteTest {

    private Cliente cliente;
    private ServicioConsultaCliente servicioConsultaCliente;
    private DaoCliente daoCliente;

    @Test
    @DisplayName("Deberia devolver TRUE por existencia de cliente")
    void existsByNit() {
        // arrange
        cliente = new ClienteTestDataBuilder().build();
        daoCliente = Mockito.mock(DaoCliente.class);
        servicioConsultaCliente = new ServicioConsultaCliente(daoCliente);
        when(daoCliente.existsByNit(cliente.getNit())).thenReturn(Boolean.TRUE);
        // act
        Boolean expected = servicioConsultaCliente.existsByNit(cliente.getNit());
        // assert
        assertEquals(Boolean.TRUE, expected);
    }

    @Test
    @DisplayName("Deberia devolver FALSE por no existencia de cliente")
    void noExistsByNit() {
        // arrange
        cliente = new ClienteTestDataBuilder().build();
        daoCliente = Mockito.mock(DaoCliente.class);
        servicioConsultaCliente = new ServicioConsultaCliente(daoCliente);
        when(daoCliente.existsByNit(cliente.getNit())).thenReturn(Boolean.FALSE);
        // act
        Boolean expected = servicioConsultaCliente.existsByNit(cliente.getNit());
        // assert
        assertEquals(Boolean.FALSE, expected);
    }
}