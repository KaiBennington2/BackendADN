package com.ceiba.cliente.comando.manejador;

import com.ceiba.cliente.servicio.ServicioEliminarCliente;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarClientes implements ManejadorComando<Long> {

    private final ServicioEliminarCliente servicioEliminarCliente;

    public ManejadorEliminarClientes(ServicioEliminarCliente servicioEliminarCliente) {
        this.servicioEliminarCliente = servicioEliminarCliente;
    }

    @Override
    public void ejecutar(Long id) {
        servicioEliminarCliente.ejecutar(id);
    }
}
