package com.ceiba.contrato.servicio;

import com.ceiba.contrato.puerto.repositorio.RepositorioContrato;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioEliminarContrato {

    private static final String NOT_EXISTS = "el contrato no existe en el sistema.";

    private final RepositorioContrato repositorioContrato;


    public ServicioEliminarContrato(RepositorioContrato repositorioContrato) {
        this.repositorioContrato = repositorioContrato;
    }

    public void ejecutar(Long id) {
        if (!repositorioContrato.existsById(id)) { throw new ExcepcionSinDatos(NOT_EXISTS); }
        repositorioContrato.delete(id);
    }
}
