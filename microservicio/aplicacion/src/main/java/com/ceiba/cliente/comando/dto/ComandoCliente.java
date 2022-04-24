package com.ceiba.cliente.comando.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCliente {

    private Long id;
    private String nitCliente;
    private String razonSocial;
    private String nombreRepresentante;
    private String telefono;
    private String direccion;
}
