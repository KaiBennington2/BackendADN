package com.ceiba.cliente.servicio;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.puerto.repositories.RepositorioCliente;
import com.ceiba.contrato.servicio.ServicioConsultaContrato;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioEliminarCliente {

    private static final String NOT_EXISTS = "el cliente no existe en el sistema.";
    private static final String TIENE_CONTRATOS_RELACIONADOS = "el cliente no puede ser eliminado por que tiene contratos relacionados.";

    private final RepositorioCliente repositorioCliente;
    private final ServicioConsultaContrato servicioConsultaContrato;

    public ServicioEliminarCliente(RepositorioCliente repositorioCliente,
                                   ServicioConsultaContrato servicioConsultaContrato) {
        this.repositorioCliente = repositorioCliente;
        this.servicioConsultaContrato = servicioConsultaContrato;
    }

    public void ejecutar(Long id) {
        if (!repositorioCliente.existsById(id)) throw new ExcepcionSinDatos(NOT_EXISTS);
        DtoCliente dtoCliente = this.repositorioCliente.findById(id);
        validarContratosRelacionados(dtoCliente);
        repositorioCliente.delete(id);
    }

    private void validarContratosRelacionados(DtoCliente dtoCliente) {
        boolean existe = this.servicioConsultaContrato.existsByNit(dtoCliente.getNitCliente());
        if (existe) throw new ExcepcionDuplicidad(TIENE_CONTRATOS_RELACIONADOS);
    }
}
