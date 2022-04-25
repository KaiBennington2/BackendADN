package com.ceiba.cliente.adaptador.repositorio;

import com.ceiba.cliente.adaptador.mapper.MapeoCliente;
import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositories.RepositorioCliente;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioClienteMysql implements RepositorioCliente {

    private static final String SQL_NAMESPACE = "cliente";
    private static final String ID = "id";
    private static final String NIT = "nit";

    @SqlStatement(namespace = SQL_NAMESPACE, value = "create")
    private static String sqlCreate;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "update")
    private static String sqlUpdate;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "delete")
    private static String sqlDelete;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "existsById")
    private static String sqlExistsById;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "existsByNit")
    private static String sqlExistsByNit;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "findById")
    private static String sqlFindById;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioClienteMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long create(Cliente cliente) {
        return customNamedParameterJdbcTemplate.crear(cliente, sqlCreate);
    }

    @Override
    public void update(Cliente cliente) {
        this.customNamedParameterJdbcTemplate.actualizar(cliente, sqlUpdate);
    }

    @Override
    public void delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlDelete,params);
    }

    @Override
    public boolean existsById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistsById, params, Boolean.class);
    }

    @Override
    public DtoCliente findById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlFindById, params, new MapeoCliente());
    }

    @Override
    public boolean existsByNit(String nit) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(NIT, nit);
        return Boolean.TRUE.equals(customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistsByNit, params, Boolean.class));
    }


}
