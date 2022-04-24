package com.ceiba.cliente.servicio;

import com.ceiba.cliente.puerto.dao.DaoCliente;

public class ServicioConsultaCliente {

    private final DaoCliente daoCliente;

    public ServicioConsultaCliente(DaoCliente daoCliente) {
        this.daoCliente = daoCliente;
    }

    public Boolean existsByNit(String nit) {
        return daoCliente.existsByNit(nit);
    }
}
