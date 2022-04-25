package com.ceiba.contrato.adaptador.dao;

import com.ceiba.contrato.adaptador.mapper.MapeoContrato;
import com.ceiba.contrato.modelo.dto.DtoContrato;
import com.ceiba.contrato.puerto.dao.DaoContrato;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoContratoMysql implements DaoContrato {

    private static final String SQL_NAMESPACE = "contrato";
    private static final String ID = "id";
    private static final String NIT = "nit";

    @SqlStatement(namespace = SQL_NAMESPACE, value = "findAll")
    private static String sqlFindAll;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "findById")
    private static String sqlFindById;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "findByNit")
    private static String sqlFindByNit;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "existsById")
    private static String sqlExistsById;

    @SqlStatement(namespace = SQL_NAMESPACE, value = "existsByNit")
    private static String sqlExistsByNit;

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoContratoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoContrato> findAll() {
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlFindAll, new MapeoContrato());
    }

    @Override
    public DtoContrato findById(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(ID, id);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlFindById, paramSource, new MapeoContrato());
    }

    @Override
    public DtoContrato findByNit(String nit) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(NIT, nit);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlFindByNit, paramSource, new MapeoContrato());

    }

    @Override
    public Boolean existsById(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(ID, id);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistsById, paramSource, Boolean.class);
    }

    @Override
    public Boolean existsByNit(String nit) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(NIT, nit);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExistsByNit, paramSource, Boolean.class);
    }

}
