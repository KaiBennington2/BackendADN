package com.ceiba.cliente.adaptador.repositorio;

import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.cliente.puerto.repositories.RepositorioCliente;
import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RepositorioClienteMysql implements RepositorioCliente {

    private static final String ERROR_AL_GUARDAR = "Ocurrió un Error al tratar de guardar el cliente";
    private static final String ERROR_AL_MODIFICAR = "Ocurrió un Error al tratar de modificar el cliente";
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

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioClienteMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long create(Cliente cliente) {
        try {
            return customNamedParameterJdbcTemplate.crear(cliente, sqlCreate);
        } catch (DataAccessException e) {
            throw new ExcepcionTecnica(ERROR_AL_GUARDAR, e);
        }
    }

    @Override
    public void update(Cliente cliente) {
        try {
            Map params = new HashMap();
            params.put("fechaModificacion", LocalDateTime.now());
            this.customNamedParameterJdbcTemplate.actualizarParametrosExtras(cliente, sqlUpdate, params);
        } catch (DataAccessException e) {
            throw new ExcepcionTecnica(ERROR_AL_MODIFICAR, e);
        }
    }

    @Override
    public void delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlDelete, params);
    }

    @Override
    public Boolean existsByNit(String nit) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(NIT, nit);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistsByNit, paramSource, Boolean.class);
    }

    @Override
    public Boolean existsById(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistsById, params, Boolean.class);
    }

}
