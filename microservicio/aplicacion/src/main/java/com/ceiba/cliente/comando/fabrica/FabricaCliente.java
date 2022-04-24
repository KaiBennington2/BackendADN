package com.ceiba.cliente.comando.fabrica;

import com.ceiba.cliente.comando.dto.ComandoCliente;
import com.ceiba.cliente.modelo.entidad.Cliente;
import org.springframework.stereotype.Component;

@Component
public class FabricaCliente {

    public Cliente create(ComandoCliente comandoCliente) {

        return new Cliente(
                comandoCliente.getId(),
                comandoCliente.getNitCliente(),
                comandoCliente.getRazonSocial(),
                comandoCliente.getNombreRepresentante(),
                comandoCliente.getTelefono(),
                comandoCliente.getDireccion()
        );
    }
}
