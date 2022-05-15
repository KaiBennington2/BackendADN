package com.ceiba.cliente.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.builders.ClienteTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {

    private static final String NIT_OBLIGATORIO = "El nit de cliente es un campo requerido.";
    private static final String RAZON_SOCIAL_OBLIGATORIO = "La razón Social es un campo requerido.";
    private static final String NOMBRE_REPREST_OBLIGATORIO = "El nombre del representante es un campo requerido.";
    private static final String TELEFONO_OBLIGATORIO = "El teléfono es un campo requerido.";
    private static final String DIRECCION_OBLIGATORIO = "La dirección de residencia es requerida.";

    @Test
    @DisplayName("Deberia crear correctamente el cliente")
    void deberiaCrearCorrectamenteElCliente() {
        // arrange
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //act
        Cliente cliente = new ClienteTestDataBuilder().conId(1L).build();
        //assert
        assertEquals(1, cliente.getId());
        assertEquals("0000001", cliente.getNit());
        assertEquals("Empresa Prueba S.A.S.", cliente.getRazonSocial());
        assertEquals("Usuario Prueba", cliente.getNombreRepresentante());
        assertEquals("1234567890", cliente.getTelefono());
        assertEquals("Cll 000 # 00 - 00", cliente.getDireccion());
    }

    @Test
    @DisplayName("Deberia lanzar exception por falta de campo obligatorio")
    void deberiaFallarSinCampoObligatorio() {
        // arrange - act - assert
        ClienteTestDataBuilder clienteTest = new ClienteTestDataBuilder().conNit(null).conId(1L);

        BasePrueba.assertThrows(clienteTest::build, ExcepcionValorObligatorio.class, NIT_OBLIGATORIO);

        clienteTest = new ClienteTestDataBuilder().conRazonSocial(null).conId(1L);
        BasePrueba.assertThrows(clienteTest::build, ExcepcionValorObligatorio.class, RAZON_SOCIAL_OBLIGATORIO);

        clienteTest = new ClienteTestDataBuilder().conNombreRepresentante(null).conId(1L);
        BasePrueba.assertThrows(clienteTest::build, ExcepcionValorObligatorio.class, NOMBRE_REPREST_OBLIGATORIO);

        clienteTest = new ClienteTestDataBuilder().conTelefono(null).conId(1L);
        BasePrueba.assertThrows(clienteTest::build, ExcepcionValorObligatorio.class, TELEFONO_OBLIGATORIO);

        clienteTest = new ClienteTestDataBuilder().conDireccion(null).conId(1L);
        BasePrueba.assertThrows(clienteTest::build, ExcepcionValorObligatorio.class, DIRECCION_OBLIGATORIO);
    }

}