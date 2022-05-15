package com.ceiba.cliente.adaptador.dao;

import com.ceiba.cliente.adaptador.mapper.MapeoCliente;
import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoClienteMysqlCliente implements DaoCliente {

    private static final String CLIENTE_NO_ENCONTRADO = "El cliente no encontrado.";

    private static final String SQL_NAMESPACE = "cliente";
    private static final String ID = "id";
    private static final String NIT = "nit";

    @SqlStatement(namespace = SQL_NAMESPACE, value = "existsById")
    private static String sqlExistsById;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "existsByNit")
    private static String sqlExistsByNit;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "findAll")
    private static String sqlFindAll;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "findById")
    private static String sqlFindById;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "findByNit")
    private static String sqlFindByNit;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoClienteMysqlCliente(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Boolean existsById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistsById, params, Boolean.class);
    }

    @Override
    public Boolean existsByNit(String nit) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(NIT, nit);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistsByNit, params, Boolean.class);
    }


    @Override
    public List<DtoCliente> findAll() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlFindAll, new MapeoCliente());
    }

    @Override
    public DtoCliente findById(Long id) {
        try {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlFindById, params, new MapeoCliente());
        } catch (DataAccessException e) {
            throw new ExcepcionTecnica(CLIENTE_NO_ENCONTRADO, e);
        }
    }

    @Override
    public DtoCliente findByNit(String nit) {
        try {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(NIT, nit);
            return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlFindByNit, params, new MapeoCliente());
        }catch (DataAccessException e){
            throw new ExcepcionTecnica(CLIENTE_NO_ENCONTRADO, e);
        }
    }

}
