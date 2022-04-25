package com.ceiba.contrato.servicio;

import com.ceiba.contrato.puerto.dao.DaoContrato;

public class ServicioConsultaContrato {

    private final DaoContrato daoContrato;

    public ServicioConsultaContrato(DaoContrato daoContrato) {
        this.daoContrato = daoContrato;
    }

    public Boolean existsByNit(String nit) {
        return daoContrato.existsByNit(nit);
    }
}
