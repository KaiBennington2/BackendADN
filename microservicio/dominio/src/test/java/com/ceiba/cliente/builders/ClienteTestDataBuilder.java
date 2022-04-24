package com.ceiba.cliente.builders;


import com.ceiba.cliente.modelo.entidad.Cliente;

public class ClienteTestDataBuilder {

    private Long id;
    private String nitCliente;
    private String razonSocial;
    private String nombreRepresentante;
    private String telefono;
    private String direccion;

    public ClienteTestDataBuilder() {
        nitCliente = "0000001";
        razonSocial = "Empresa Prueba S.A.S.";
        nombreRepresentante = "Usuario Prueba";
        telefono = "1234567890";
        direccion = "Cll 000 # 00 - 00";
    }

    public ClienteTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ClienteTestDataBuilder conNit(String nit) {
        this.nitCliente = nit;
        return this;
    }

    public ClienteTestDataBuilder conRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
        return this;
    }

    public ClienteTestDataBuilder conNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
        return this;
    }

    public ClienteTestDataBuilder conTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public ClienteTestDataBuilder conDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public Cliente build() {
        return new Cliente(id, nitCliente, razonSocial, nombreRepresentante, telefono, direccion);
    }
}
