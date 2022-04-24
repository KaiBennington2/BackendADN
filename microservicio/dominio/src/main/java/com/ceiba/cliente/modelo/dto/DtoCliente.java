package com.ceiba.cliente.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCliente {

    private Long id;
    private String nitCustomer;
    private String companyName;
    private String representantName;
    private String phone;
    private String adress;
}
