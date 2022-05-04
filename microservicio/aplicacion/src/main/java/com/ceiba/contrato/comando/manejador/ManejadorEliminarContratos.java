package com.ceiba.contrato.comando.manejador;

import com.ceiba.contrato.servicio.ServicioEliminarContrato;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarContratos implements ManejadorComando<Long> {

    private final ServicioEliminarContrato servicioEliminarContrato;

    public ManejadorEliminarContratos(ServicioEliminarContrato servicioEliminarContrato) {
        this.servicioEliminarContrato = servicioEliminarContrato;
    }

    @Override
    public void ejecutar(Long id) { this.servicioEliminarContrato.ejecutar(id); }
}
