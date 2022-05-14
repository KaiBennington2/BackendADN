package com.ceiba.contrato.comando.fabrica;

import com.ceiba.cliente.comando.fabrica.FabricaCliente;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import com.ceiba.contrato.comando.dto.ComandoContrato;
import com.ceiba.contrato.modelo.entidad.Contrato;
import com.ceiba.contrato.modelo.enums.PaquetesContratos;
import org.springframework.stereotype.Component;

@Component
public class FabricaContrato {

    private final DaoCliente daoCliente;
    private final FabricaCliente fabricaCliente;

    public FabricaContrato(DaoCliente daoCliente, FabricaCliente fabricaCliente) {
        this.daoCliente = daoCliente;
        this.fabricaCliente = fabricaCliente;
    }

    public Contrato crear(ComandoContrato comandoContrato) {
        return new Contrato(
                comandoContrato.getId(),
                fabricaCliente.create(daoCliente.findByNit(comandoContrato.getNitCliente())),
                comandoContrato.getTipoMoneda(),
                comandoContrato.getTrmAplicada(),
                PaquetesContratos.getByName(comandoContrato.getPaqueteContrato()),
                comandoContrato.getFechaInstalacion()
        );
    }

}
