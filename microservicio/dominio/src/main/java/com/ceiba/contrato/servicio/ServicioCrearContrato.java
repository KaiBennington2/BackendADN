package com.ceiba.contrato.servicio;

import com.ceiba.contrato.modelo.entidad.Contrato;
import com.ceiba.contrato.puerto.repositorio.RepositorioContrato;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearContrato {

    private static final String EL_CONTRATO_YA_EXISTE_EN_EL_SISTEMA = "Para el cliente [%s], ya existe un contrato en el sistema.";

    private final RepositorioContrato repositorioContrato;

    public ServicioCrearContrato(RepositorioContrato repositorioContrato) {
        this.repositorioContrato = repositorioContrato;
    }

    public Long execute(Contrato contrato) {
        validarExistenciaContrato(contrato);
        return this.repositorioContrato.crear(contrato);
    }

    private void validarExistenciaContrato(Contrato contrato) {
        String nitCliente = contrato.getCliente().getNit();
        boolean existe = this.repositorioContrato.existsByNit(nitCliente);
        if (existe) {
            throw new ExcepcionDuplicidad(String.format(EL_CONTRATO_YA_EXISTE_EN_EL_SISTEMA, nitCliente));
        }
    }

}
