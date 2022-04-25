package com.ceiba.cliente.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.builders.ClienteTestDataBuilder;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositories.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ServicioActualizarClienteTest {

    private static final String NOT_EXISTS = "el cliente no existe en el sistema.";

    private Cliente cliente;
    private ServicioActualizarCliente servicioActualizarCliente;
    private RepositorioCliente repositorioCliente;

    @Test
    @DisplayName("Deberia actualizar un cliente de manera correcta")
    void deberiaActualizarUnCliente(){
        // arrange
        cliente = new ClienteTestDataBuilder().conId(1L).conNit("1234567").build();
        repositorioCliente = Mockito.mock(RepositorioCliente.class);
        when(repositorioCliente.existsById(anyLong())).thenReturn(true);
        servicioActualizarCliente = new ServicioActualizarCliente(repositorioCliente);
        // act
        servicioActualizarCliente.ejecutar(cliente);
        //assert
        Mockito.verify(repositorioCliente, Mockito.times(1)).update(cliente);
    }

    @Test
    @DisplayName("Deberia lanzar excepcion por no existencia del cliente.")
    void deberiaLanzarExcepcionPorNoExistenciaDelCliente(){
        // arrange
        cliente = new ClienteTestDataBuilder().conId(1L).conNit("1234567").build();
        repositorioCliente = Mockito.mock(RepositorioCliente.class);
        when(repositorioCliente.existsById(anyLong())).thenReturn(false);
        servicioActualizarCliente = new ServicioActualizarCliente(repositorioCliente);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarCliente.ejecutar(cliente),
                ExcepcionSinDatos.class,
                NOT_EXISTS);

    }

}