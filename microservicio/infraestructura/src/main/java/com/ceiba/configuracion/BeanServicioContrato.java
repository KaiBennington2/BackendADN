package com.ceiba.configuracion;

import com.ceiba.contrato.puerto.dao.DaoContrato;
import com.ceiba.contrato.puerto.repositorio.RepositorioContrato;
import com.ceiba.contrato.servicio.ServicioConsultaContrato;
import com.ceiba.contrato.servicio.ServicioCrearContrato;
import com.ceiba.contrato.servicio.ServicioDetalleContrato;
import com.ceiba.contrato.servicio.ServicioEliminarContrato;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioContrato {

    @Bean
    public ServicioCrearContrato contractCreateService(RepositorioContrato repositorioContrato) {
        return new ServicioCrearContrato(repositorioContrato);
    }

    @Bean
    public ServicioEliminarContrato contractDeleteService(RepositorioContrato repositorioContrato) {
        return new ServicioEliminarContrato(repositorioContrato);
    }

    @Bean
    public ServicioDetalleContrato contractDetailService(DaoContrato daoContrato) {
        return new ServicioDetalleContrato(daoContrato);
    }

    @Bean
    public ServicioConsultaContrato contractConsultService(DaoContrato daoContrato) {
        return new ServicioConsultaContrato(daoContrato);
    }
}
