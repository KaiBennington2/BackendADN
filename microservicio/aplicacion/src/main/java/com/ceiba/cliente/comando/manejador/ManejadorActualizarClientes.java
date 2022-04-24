package com.ceiba.cliente.comando.manejador;

import com.ceiba.cliente.comando.dto.ComandoCliente;
import com.ceiba.cliente.comando.fabrica.FabricaCliente;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.servicio.ServicioActualizarCliente;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarClientes implements ManejadorComando<ComandoCliente> {

    private final FabricaCliente fabricaCliente;
    private final ServicioActualizarCliente servicioActualizarCliente;

    public ManejadorActualizarClientes(FabricaCliente fabricaCliente, ServicioActualizarCliente servicioActualizarCliente) {
        this.fabricaCliente = fabricaCliente;
        this.servicioActualizarCliente = servicioActualizarCliente;
    }

    @Override
    public void ejecutar(ComandoCliente comando) {
        Cliente cliente = fabricaCliente.create(comando);
        this.servicioActualizarCliente.ejecutar(cliente);
    }
}
