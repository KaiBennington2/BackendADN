package com.ceiba.cliente.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.builders.ClienteTestDataBuilder;
import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositories.RepositorioCliente;
import com.ceiba.contrato.servicio.ServicioConsultaContrato;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ServicioEliminarClienteTest {

    private static final String NOT_EXISTS = "el cliente no existe en el sistema.";
    private static final String TIENE_CONTRATOS_RELACIONADOS = "el cliente no puede ser eliminado por que tiene contratos relacionados.";

    private Cliente cliente;
    private DtoCliente dtoCliente;
    private RepositorioCliente repositorioCliente;
    private ServicioConsultaContrato servicioConsultaContrato;

    @Test
    @DisplayName("Deberia eliminar el cliente de manera correcta")
    void deberiaEliminarElClienteDeManeraCorrecta() {
        cliente = new ClienteTestDataBuilder().conId(4L).conNit("1234").build();
        dtoCliente = mapperClienteToDtoCliente(cliente);
        repositorioCliente = Mockito.mock(RepositorioCliente.class);
        servicioConsultaContrato = Mockito.mock(ServicioConsultaContrato.class);
        when(repositorioCliente.existsById(anyLong())).thenReturn(Boolean.TRUE);
        when(repositorioCliente.findById(anyLong())).thenReturn(dtoCliente);
        when(servicioConsultaContrato.existsByNit(anyString())).thenReturn(Boolean.FALSE);

        ServicioEliminarCliente servicioEliminarCliente = new ServicioEliminarCliente(repositorioCliente, servicioConsultaContrato);
        servicioEliminarCliente.ejecutar(4L);

        Mockito.verify(repositorioCliente, Mockito.times(1)).delete(4L);
    }

    @Test
    @DisplayName("Deberia lanzar excepcion por tener contratos relacionados")
    void deberiaLanzarExcepcionPorTenerContratosRelacionados() {
        cliente = new ClienteTestDataBuilder().conId(1L).conNit("1234567").build();
        dtoCliente = mapperClienteToDtoCliente(cliente);
        repositorioCliente = Mockito.mock(RepositorioCliente.class);
        servicioConsultaContrato = Mockito.mock(ServicioConsultaContrato.class);
        when(repositorioCliente.existsById(anyLong())).thenReturn(Boolean.TRUE);
        when(repositorioCliente.findById(anyLong())).thenReturn(dtoCliente);
        when(servicioConsultaContrato.existsByNit(anyString())).thenReturn(Boolean.TRUE);

        ServicioEliminarCliente servicioEliminarCliente = new ServicioEliminarCliente(repositorioCliente, servicioConsultaContrato);
        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarCliente.ejecutar(1L),
                ExcepcionDuplicidad.class,
                TIENE_CONTRATOS_RELACIONADOS);
    }

    @Test
    @DisplayName("Deberia lanzar excepcion por no existencia del cliente")
    void deberiaLanzarExcepcionPorNoExistenciaCliente() {
        repositorioCliente = Mockito.mock(RepositorioCliente.class);
        servicioConsultaContrato = Mockito.mock(ServicioConsultaContrato.class);
        when(repositorioCliente.existsById(anyLong())).thenReturn(Boolean.FALSE);

        ServicioEliminarCliente servicioEliminarCliente = new ServicioEliminarCliente(repositorioCliente, servicioConsultaContrato);
        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarCliente.ejecutar(1L),
                ExcepcionSinDatos.class,
                NOT_EXISTS);
    }

    DtoCliente mapperClienteToDtoCliente(Cliente cliente) {
        return new DtoCliente(
                cliente.getId(),
                cliente.getNitCustomer(),
                cliente.getCompanyName(),
                cliente.getRepresentantName(),
                cliente.getPhone(),
                cliente.getAdress());
    }

}