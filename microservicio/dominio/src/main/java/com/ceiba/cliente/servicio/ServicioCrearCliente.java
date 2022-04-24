package com.ceiba.cliente.servicio;

import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositories.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearCliente {

    private static final String EXISTS = "el cliente ya existe en el sistema.";

    private final RepositorioCliente customerRepository;

    public ServicioCrearCliente(RepositorioCliente customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Long execute(Cliente cliente) {
        validarExistenciaPrevia(cliente);
        return customerRepository.create(cliente);
    }

    private void validarExistenciaPrevia(Cliente cliente) {
        if (customerRepository.existsByNit(cliente.getNitCustomer())) {
            throw new ExcepcionDuplicidad(EXISTS);
        }
    }
}
