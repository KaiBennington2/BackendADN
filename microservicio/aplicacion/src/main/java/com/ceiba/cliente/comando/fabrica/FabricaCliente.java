package com.ceiba.cliente.comando.fabrica;

import com.ceiba.cliente.comando.dto.ComandoCliente;
import com.ceiba.cliente.modelo.dto.DtoCliente;
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

    public Cliente create(DtoCliente dtoCliente) {

        return new Cliente(
                dtoCliente.getId(),
                dtoCliente.getNitCliente(),
                dtoCliente.getRazonSocial(),
                dtoCliente.getNombreRepresentante(),
                dtoCliente.getTelefono(),
                dtoCliente.getDireccion()
        );
    }
}
