package com.ceiba.contrato.puerto.repositorio;

import com.ceiba.contrato.modelo.entidad.Contrato;

public interface RepositorioContrato {

    Long crear(Contrato contrato);

    void delete(Long id);

    Boolean existsByNit(String nit);

    Boolean existsById(Long id);


}
