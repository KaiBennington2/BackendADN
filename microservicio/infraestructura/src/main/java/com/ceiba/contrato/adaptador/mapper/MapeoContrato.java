package com.ceiba.contrato.adaptador.mapper;

import com.ceiba.contrato.modelo.dto.DtoContrato;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class MapeoContrato implements RowMapper<DtoContrato>, MapperResult {

    @Override
    public DtoContrato mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long id = rs.getLong("id");
        String nitCustomer = rs.getString("nit_cliente");
        Integer contractTime = rs.getInt("duracion_contrato_meses");
        String coinType = rs.getString("tipo_moneda");
        BigDecimal trmApplied = rs.getBigDecimal("trm_aplicada");
        String packet = rs.getString("paquete");
        LocalDate installDate = extraerLocalDate(rs, "fecha_instalacion");
        LocalDate cutOffDate = extraerLocalDate(rs, "fecha_corte");
        LocalDate cutOffBeforeDate = extraerLocalDate(rs, "fecha_corte_anterior");

        return new DtoContrato(id, nitCustomer, contractTime, coinType, trmApplied, packet, installDate, cutOffDate, cutOffBeforeDate);
    }

}
