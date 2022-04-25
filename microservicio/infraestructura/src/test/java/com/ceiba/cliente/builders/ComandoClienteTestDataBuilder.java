package com.ceiba.cliente.builders;

import com.ceiba.cliente.comando.dto.ComandoCliente;

public class ComandoClienteTestDataBuilder {

    private Long id;
    private String nitCliente;
    private String razonSocial;
    private String nombreRepresentante;
    private String telefono;
    private String direccion;

    public ComandoClienteTestDataBuilder() {
        nitCliente = "0000001";
        razonSocial = "Empresa Prueba S.A.S.";
        nombreRepresentante = "Usuario Prueba";
        telefono = "1234567890";
        direccion = "Cll 000 # 00 - 00";
    }

    public ComandoClienteTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoClienteTestDataBuilder conNit(String nit) {
        this.nitCliente = nit;
        return this;
    }

    public ComandoClienteTestDataBuilder conRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
        return this;
    }

    public ComandoClienteTestDataBuilder conNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
        return this;
    }

    public ComandoClienteTestDataBuilder conTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public ComandoClienteTestDataBuilder conDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public ComandoCliente build() {
        return new ComandoCliente(id, nitCliente, razonSocial, nombreRepresentante, telefono, direccion);
    }
}
