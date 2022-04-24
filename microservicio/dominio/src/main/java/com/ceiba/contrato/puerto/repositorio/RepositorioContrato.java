package com.ceiba.contrato.puerto.repositorio;

import com.ceiba.contrato.modelo.entidad.Contrato;

public interface RepositorioContrato {

    Long crear(Contrato contrato);

    Boolean existsByNit(String nit);


}
