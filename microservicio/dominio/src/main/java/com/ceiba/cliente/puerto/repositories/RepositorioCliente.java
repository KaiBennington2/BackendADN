package com.ceiba.cliente.puerto.repositories;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.modelo.entidad.Cliente;

public interface RepositorioCliente {

    Long create(Cliente cliente);

    void update(Cliente cliente);
    void delete(Long id);

    boolean existsByNit(String nit);

    boolean existsById(Long id);

    DtoCliente findById(Long id);

}
