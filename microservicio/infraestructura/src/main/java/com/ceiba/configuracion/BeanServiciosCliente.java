package com.ceiba.configuracion;

import com.ceiba.cliente.puerto.dao.DaoCliente;
import com.ceiba.cliente.puerto.repositories.RepositorioCliente;
import com.ceiba.cliente.servicio.ServicioActualizarCliente;
import com.ceiba.cliente.servicio.ServicioConsultaCliente;
import com.ceiba.cliente.servicio.ServicioCrearCliente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServiciosCliente {

    @Bean
    public ServicioCrearCliente customerCreateService(RepositorioCliente repositorioCliente) {
        return new ServicioCrearCliente(repositorioCliente);
    }

    @Bean
    public ServicioActualizarCliente customerUpdateService(RepositorioCliente repositorioCliente) {
        return new ServicioActualizarCliente(repositorioCliente);
    }

    @Bean
    public ServicioConsultaCliente customerConsultService(DaoCliente daoCliente) {
        return new ServicioConsultaCliente(daoCliente);
    }
}
