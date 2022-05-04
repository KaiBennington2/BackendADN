package com.ceiba.contrato.adaptador.repositorio;

import com.ceiba.contrato.modelo.entidad.Contrato;
import com.ceiba.contrato.puerto.repositorio.RepositorioContrato;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioContratoMysql implements RepositorioContrato {

    private static final String SQL_NAMESPACE = "contrato";
    private static final String ID = "id";
    private static final String NIT = "nit";


    @SqlStatement(namespace = SQL_NAMESPACE, value = "create")
    private static String sqlCreate;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "delete")
    private static String sqlDelete;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "existsById")
    private static String sqlExistsById;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "existsByNit")
    private static String sqlExistsByNit;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioContratoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Contrato contrato) {
        return customNamedParameterJdbcTemplate.crear(contrato, sqlCreate);
    }

    @Override
    public void delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(ID, id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlDelete,params);
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
