package com.ceiba.cliente.modelo.entidad;

import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarVacio;

@Getter
public class Cliente {

    private static final String NIT_REQUIRED = "El nit de cliente es un campo requerido.";
    private static final String COMPANY_REQUIRED = "El nombre de la compañía no debe ir vacío.";
    private static final String REPRESENTANT_REQUIRED = "El nombre del representante es un campo requerido.";
    private static final String PHONE_REQUIRED = "El teléfono es un campo requerido.";
    private static final String ADRESS_REQUIRED = "La dirección de residencia es requerida.";

    private Long id;
    private String nitCustomer;
    private String companyName;
    private String representantName;
    private String phone;
    private String adress;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Cliente(Long id, String nitCustomer, String companyName, String representantName, String phone, String adress) {

        validarObligatorio(nitCustomer, NIT_REQUIRED);
        validarVacio(nitCustomer, NIT_REQUIRED);

        validarObligatorio(companyName, COMPANY_REQUIRED);
        validarVacio(companyName, COMPANY_REQUIRED);

        validarObligatorio(representantName, REPRESENTANT_REQUIRED);
        validarVacio(representantName, REPRESENTANT_REQUIRED);

        validarObligatorio(phone, PHONE_REQUIRED);
        validarVacio(phone, PHONE_REQUIRED);

        validarObligatorio(adress, ADRESS_REQUIRED);
        validarVacio(adress, ADRESS_REQUIRED);

        this.id = id;
        this.nitCustomer = nitCustomer;
        this.companyName = companyName;
        this.representantName = representantName;
        this.phone = phone;
        this.adress = adress;
    }

    @Override
    public String toString() {
        return "Cliente:" + id + ": ->{" +
                " Nit :" + nitCustomer + "," +
                " Razón social :" + companyName + "," +
                " Representante legal :" + representantName + "," +
                " Teléfono :" + phone + "," +
                " Dirección :" + adress + " }";
    }
}
