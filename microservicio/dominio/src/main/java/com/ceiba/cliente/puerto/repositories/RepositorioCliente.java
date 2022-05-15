package com.ceiba.cliente.puerto.repositories;

import com.ceiba.cliente.modelo.entidad.Cliente;

public interface RepositorioCliente {

    Long create(Cliente cliente);

    void update(Cliente cliente);

    void delete(Long id);

    Boolean existsByNit(String nit);

    Boolean existsById(Long id);

}
