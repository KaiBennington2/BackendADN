package com.ceiba.cliente.puerto.dao;

import com.ceiba.cliente.modelo.dto.DtoCliente;

import java.util.List;

public interface DaoCliente {

    Boolean existsById(Long id);

    Boolean existsByNit(String nit);

    List<DtoCliente> findAll();

    DtoCliente findById(Long id);

    DtoCliente findByNit(String nit);

}
