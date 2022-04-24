package com.ceiba.cliente.adaptador.mapper;

import com.ceiba.cliente.modelo.dto.DtoCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoCliente implements RowMapper<DtoCliente>, MapperResult {

    private static final String ID = "id";
    private static final String NIT = "nit";
    private static final String COMPANY = "razon_social";
    private static final String REPRESENTANT = "nombre_representante";
    private static final String PHONE = "telefono";
    private static final String ADRESS = "direccion";

    @Override
    public DtoCliente mapRow(ResultSet rs, int rowNum) throws SQLException {

        return new DtoCliente(
                rs.getLong(ID),
                rs.getString(NIT),
                rs.getString(COMPANY),
                rs.getString(REPRESENTANT),
                rs.getString(PHONE),
                rs.getString(ADRESS)
        );
    }
}
