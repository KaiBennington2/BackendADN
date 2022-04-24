package com.ceiba.cliente.servicio;

import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositories.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioActualizarCliente {

    private static final String NOT_EXISTS = "el cliente no existe en el sistema.";

    private final RepositorioCliente repositorioCliente;

    public ServicioActualizarCliente(RepositorioCliente repositorioCliente) {
        this.repositorioCliente = repositorioCliente;
    }


    public void ejecutar(Cliente cliente) {
        validarExistenciaPrevia(cliente);
        repositorioCliente.update(cliente);

    }

    private void validarExistenciaPrevia(Cliente cliente) {
        boolean existe = this.repositorioCliente.existsById(cliente.getId());
        if (!existe) {
            throw new ExcepcionSinDatos(NOT_EXISTS);
        }
    }
}
