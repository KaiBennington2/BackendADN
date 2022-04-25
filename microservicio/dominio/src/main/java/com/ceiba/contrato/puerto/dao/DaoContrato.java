package com.ceiba.contrato.puerto.dao;

import com.ceiba.contrato.modelo.dto.DtoContrato;

import java.util.List;

public interface DaoContrato {

    Boolean existsById(Long id);

    Boolean existsByNit(String nit);

    List<DtoContrato> findAll();

    DtoContrato findById(Long id);

    DtoContrato findByNit(String nit);
}
