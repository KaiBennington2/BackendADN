package com.ceiba.cliente.modelo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.builders.ClienteTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClienteTest {

    private final static String NIT_REQUIRED = "El nit de cliente es un campo requerido.";
    private final static String COMPANY_REQUIRED = "El nombre de la compañía no debe ir vacío.";
    private final static String REPRESENTANT_REQUIRED = "El nombre del representante es un campo requerido.";
    private final static String PHONE_REQUIRED = "El teléfono es un campo requerido.";
    private final static String ADRESS_REQUIRED = "La dirección de residencia es requerida.";

    @Test
    @DisplayName("Deberia crear correctamente el cliente")
    void deberiaCrearCorrectamenteElCliente() {
        // arrange
        String toString = "Cliente:1: ->{ Nit :0000001, Razón social :Empresa Prueba S.A.S.," +
                " Representante legal :Usuario Prueba, Teléfono :1234567890, Dirección :Cll 000 # 00 - 00 }";
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
        assertEquals(toString, cliente.toString());
    }

    @Test
    @DisplayName("Deberia lanzar exception por falta de campo obligatorio")
    void deberiaFallarSinCampoObligatorio() {
        // arrange - act - assert
        ClienteTestDataBuilder clienteTest = new ClienteTestDataBuilder().conNit(null).conId(1L);

        BasePrueba.assertThrows(clienteTest::build, ExcepcionValorObligatorio.class, NIT_REQUIRED);

        clienteTest = new ClienteTestDataBuilder().conRazonSocial(null).conId(1L);
        BasePrueba.assertThrows(clienteTest::build, ExcepcionValorObligatorio.class, COMPANY_REQUIRED);

        clienteTest = new ClienteTestDataBuilder().conNombreRepresentante(null).conId(1L);
        BasePrueba.assertThrows(clienteTest::build, ExcepcionValorObligatorio.class, REPRESENTANT_REQUIRED);

        clienteTest = new ClienteTestDataBuilder().conTelefono(null).conId(1L);
        BasePrueba.assertThrows(clienteTest::build, ExcepcionValorObligatorio.class, PHONE_REQUIRED);

        clienteTest = new ClienteTestDataBuilder().conDireccion(null).conId(1L);
        BasePrueba.assertThrows(clienteTest::build, ExcepcionValorObligatorio.class, ADRESS_REQUIRED);
    }

}