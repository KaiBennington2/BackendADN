package com.ceiba.cliente.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.builders.ClienteTestDataBuilder;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import com.ceiba.cliente.puerto.repositories.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class ServicioCrearClienteTest {

    private final static String EXISTS = "el cliente ya existe en el sistema.";
    private final static String NIT_REQUIRED = "El nit de cliente es un campo requerido.";

    private DaoCliente customerDao;
    private RepositorioCliente customerRepository;
    private ServicioCrearCliente servicioCrearCliente;

    @Test
    @DisplayName("Deberia lanzar una exepecion por nit vacio en el modelo cliente")
    void deberiaLanzarExceptionPorNitVacio() {
        // arrange
        ClienteTestDataBuilder customerTest = new ClienteTestDataBuilder().conNit(null);
        // act - assert
        BasePrueba.assertThrows(customerTest::build,
                ExcepcionValorObligatorio.class,
                NIT_REQUIRED);
    }

    @Test
    @DisplayName("Deberia Crear un cliente de manera correcta")
    void deberiaCrearClienteDeManeraCorrecta() {
        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        customerRepository = Mockito.mock(RepositorioCliente.class);
        when(customerRepository.create(any(Cliente.class))).thenReturn(1L);
        servicioCrearCliente = new ServicioCrearCliente(customerRepository);
        // act
        Long idExpected = servicioCrearCliente.execute(cliente);
        // assert
        assertEquals(1L, idExpected);
    }

    @Test
    @DisplayName("Deberia lanzar una exepecion por existencia del cliente en el sistema")
    void deberiaLanzarExceptionPorExistenciaDelCliente() {
        // arrange
        Cliente cliente = new ClienteTestDataBuilder().build();
        customerRepository = Mockito.mock(RepositorioCliente.class);
        when(customerRepository.existsByNit(anyString())).thenReturn(true);

        servicioCrearCliente = new ServicioCrearCliente(customerRepository);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearCliente.execute(cliente),
                ExcepcionDuplicidad.class,
                EXISTS);
    }
}