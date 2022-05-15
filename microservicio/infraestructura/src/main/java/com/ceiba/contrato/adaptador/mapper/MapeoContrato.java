package com.ceiba.contrato.adaptador.mapper;

import com.ceiba.contrato.modelo.dto.DtoContrato;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class MapeoContrato implements RowMapper<DtoContrato>, MapperResult {

    private static final String ID = "id";
    private static final String NIT_CLIENTE = "nit_cliente";
    private static final String CONTRATO_DURACION_MESES = "duracion_contrato_meses";
    private static final String TIPO_MONEDA = "tipo_moneda";
    private static final String TRM_APLICADA = "trm_aplicada";
    private static final String PAQUETE_CONTRATO = "paquete";
    private static final String FECHA_INSTALACION = "fecha_instalacion";
    private static final String FECHA_CORTE = "fecha_corte";
    private static final String FECHA_CORTE_ANTERIOR = "fecha_corte_anterior";

    @Override
    public DtoContrato mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new DtoContrato(
                rs.getLong(ID),
                rs.getString(NIT_CLIENTE),
                rs.getInt(CONTRATO_DURACION_MESES),
                rs.getString(TIPO_MONEDA),
                rs.getBigDecimal(TRM_APLICADA),
                rs.getString(PAQUETE_CONTRATO),
                extraerLocalDate(rs, FECHA_INSTALACION),
                extraerLocalDate(rs, FECHA_CORTE),
                extraerLocalDate(rs, FECHA_CORTE_ANTERIOR));
    }

}
