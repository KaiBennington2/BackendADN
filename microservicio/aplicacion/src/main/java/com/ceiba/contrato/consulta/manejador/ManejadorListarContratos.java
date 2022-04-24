package com.ceiba.contrato.consulta.manejador;

import com.ceiba.contrato.modelo.dto.DtoContrato;
import com.ceiba.contrato.puerto.dao.DaoContrato;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarContratos {

    private DaoContrato daoContrato;

    public ManejadorListarContratos(DaoContrato daoContrato) {
        this.daoContrato = daoContrato;
    }

    public List<DtoContrato> ejecutar() {
        return daoContrato.findAll();
    }

    public DtoContrato ejecutar(Long id) {
        return daoContrato.findById(id);
    }

    public DtoContrato ejecutar(String nit) {
        return daoContrato.findByNit(nit);
    }

}
