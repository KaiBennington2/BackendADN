package com.ceiba.cliente.servicio;

import com.ceiba.cliente.builders.ClienteTestDataBuilder;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ServicioConsultaClienteTest {

    private Cliente cliente;
    private ServicioConsultaCliente servicioConsultaCliente;
    private DaoCliente daoCliente;

    @Test
    void existsByNit() {
        // arrange
        cliente = new ClienteTestDataBuilder().build();
        daoCliente = Mockito.mock(DaoCliente.class);
        servicioConsultaCliente = new ServicioConsultaCliente(daoCliente);
        when(daoCliente.existsByNit(cliente.getNitCustomer())).thenReturn(Boolean.TRUE);
        // act
        Boolean expected = servicioConsultaCliente.existsByNit(cliente.getNitCustomer());
        // assert
        assertEquals(Boolean.TRUE, expected);
    }
}