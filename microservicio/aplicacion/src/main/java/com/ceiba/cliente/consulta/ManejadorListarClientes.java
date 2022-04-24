package com.ceiba.cliente.consulta;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarClientes {

    private final DaoCliente customerDao;

    public ManejadorListarClientes(DaoCliente customerDao) {
        this.customerDao = customerDao;
    }

    public List<DtoCliente> findAll() {
        return this.customerDao.findAll();
    }

    public DtoCliente findById(Long id) {
        return this.customerDao.findById(id);
    }

    public DtoCliente findByNit(String nit) {
        return this.customerDao.findByNit(nit);
    }
}
