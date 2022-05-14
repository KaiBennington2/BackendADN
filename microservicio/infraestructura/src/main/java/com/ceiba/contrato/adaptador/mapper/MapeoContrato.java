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
        String nitCliente = rs.getString("nit_cliente");
        Integer tiempoContratoMeses = rs.getInt("duracion_contrato_meses");
        String tipoMoneda = rs.getString("tipo_moneda");
        BigDecimal trmAplicada = rs.getBigDecimal("trm_aplicada");
        String paqueteContrato = rs.getString("paquete");
        LocalDate fechaInstalacion = extraerLocalDate(rs, "fecha_instalacion");
        LocalDate fechaCorte = extraerLocalDate(rs, "fecha_corte");
        LocalDate fechaCorteAnterior = extraerLocalDate(rs, "fecha_corte_anterior");

        return new DtoContrato(
                id,
                nitCliente,
                tiempoContratoMeses,
                tipoMoneda,
                trmAplicada,
                paqueteContrato,
                fechaInstalacion,
                fechaCorte,
                fechaCorteAnterior);
    }

}
