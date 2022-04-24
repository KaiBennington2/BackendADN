package com.ceiba.configuracion;

import com.ceiba.cliente.servicio.ServicioConsultaCliente;
import com.ceiba.contrato.puerto.dao.DaoContrato;
import com.ceiba.contrato.puerto.repositorio.RepositorioContrato;
import com.ceiba.contrato.servicio.ServicioCrearContrato;
import com.ceiba.contrato.servicio.ServicioDetalleContrato;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioContrato {

    @Bean
    public ServicioCrearContrato contractCreateService(RepositorioContrato repositorioContrato, ServicioConsultaCliente servicioConsultaCliente) {
        return new ServicioCrearContrato(repositorioContrato, servicioConsultaCliente);
    }

    @Bean
    public ServicioDetalleContrato servicioDetalleContrato(DaoContrato daoContrato) {
        return new ServicioDetalleContrato(daoContrato);
    }
}
