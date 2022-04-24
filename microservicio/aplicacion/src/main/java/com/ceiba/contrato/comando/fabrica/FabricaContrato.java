package com.ceiba.contrato.comando.fabrica;

import com.ceiba.contrato.comando.dto.ComandoContrato;
import com.ceiba.contrato.modelo.entidad.Contrato;
import org.springframework.stereotype.Component;

@Component
public class FabricaContrato {

    public Contrato crear(ComandoContrato comandoContrato) {

        return new Contrato(
                comandoContrato.getId(),
                comandoContrato.getNitCliente(),
                comandoContrato.getTipoMoneda(),
                comandoContrato.getTrmAplicada(),
                comandoContrato.getPaquete(),
                comandoContrato.getFechaInstalacion()
        );
    }

}
