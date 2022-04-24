package com.ceiba.contrato.consulta.manejador;

import com.ceiba.contrato.modelo.dto.DtoContratoDetalle;
import com.ceiba.contrato.servicio.ServicioDetalleContrato;
import org.springframework.stereotype.Component;

@Component
public class ManejadorDetalleContrato {

    private final ServicioDetalleContrato servicioDetalleContrato;

    public ManejadorDetalleContrato(ServicioDetalleContrato servicioDetalleContrato) {
        this.servicioDetalleContrato = servicioDetalleContrato;
    }

    public DtoContratoDetalle ejecutarDetalle(Long id) {
        return servicioDetalleContrato.ejecutar(id);
    }
}
