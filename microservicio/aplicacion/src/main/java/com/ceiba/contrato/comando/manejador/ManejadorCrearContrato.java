package com.ceiba.contrato.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.contrato.comando.dto.ComandoContrato;
import com.ceiba.contrato.comando.fabrica.FabricaContrato;
import com.ceiba.contrato.modelo.entidad.Contrato;
import com.ceiba.contrato.servicio.ServicioCrearContrato;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearContrato implements ManejadorComandoRespuesta<ComandoContrato, ComandoRespuesta<Long>> {

    private final FabricaContrato fabricaContrato;
    private final ServicioCrearContrato servicioCrearContrato;

    public ManejadorCrearContrato(FabricaContrato fabricaContrato, ServicioCrearContrato servicioCrearContrato) {
        this.fabricaContrato = fabricaContrato;
        this.servicioCrearContrato = servicioCrearContrato;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoContrato comando) {
        Contrato contrato = fabricaContrato.crear(comando);
        return new ComandoRespuesta<>(servicioCrearContrato.execute(contrato));
    }
}
