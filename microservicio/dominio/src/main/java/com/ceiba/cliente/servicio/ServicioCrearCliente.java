package com.ceiba.cliente.servicio;

import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositories.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearCliente {

    private static final String EXISTS = "el cliente ya existe en el sistema.";

    private final RepositorioCliente repositorioCliente;

    public ServicioCrearCliente(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }

    public Long execute(Cliente cliente) {
        validarExistenciaPrevia(cliente);
        return repositorioCliente.create(cliente);
    }

    private void validarExistenciaPrevia(Cliente cliente) {
        if (repositorioCliente.existsByNit(cliente.getNit())) {
            throw new ExcepcionDuplicidad(EXISTS);
        }
    }
}
