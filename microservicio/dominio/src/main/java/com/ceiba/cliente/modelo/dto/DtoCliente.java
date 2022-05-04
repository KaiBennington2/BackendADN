package com.ceiba.cliente.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCliente {

    private Long id;
    private String nitCliente;
    private String razonSocial;
    private String nombreRepresentante;
    private String telefono;
    private String direccion;
}
