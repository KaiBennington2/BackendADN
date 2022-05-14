package com.ceiba.cliente.modelo.entidad;

import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarVacio;

@Getter
public class Cliente {

    private static final Integer NIT_MIN_LENGHT = 5;
    private static final Integer NIT_MAX_LENGHT = 15;
    private static final String NIT_LIMITE_LONGITUD = "El NIT no debe tener mas de %s dígitos.";
    private static final String NIT_REQUIRED = "El nit de cliente es un campo requerido.";
    private static final String COMPANY_REQUIRED = "El nombre de la compañía no debe ir vacío.";
    private static final String REPRESENTANT_REQUIRED = "El nombre del representante es un campo requerido.";
    private static final String PHONE_REQUIRED = "El teléfono es un campo requerido.";
    private static final String ADRESS_REQUIRED = "La dirección de residencia es requerida.";

    private Long id;
    private String nit;
    private String razonSocial;
    private String nombreRepresentante;
    private String telefono;
    private String direccion;

    public Cliente(Long id, String nit, String razonSocial, String nombreRepresentante, String telefono, String direccion) {

        validarObligatorio(nit, NIT_REQUIRED);
        validarVacio(nit, NIT_REQUIRED);

        validarObligatorio(razonSocial, COMPANY_REQUIRED);
        validarVacio(razonSocial, COMPANY_REQUIRED);

        validarObligatorio(nombreRepresentante, REPRESENTANT_REQUIRED);
        validarVacio(nombreRepresentante, REPRESENTANT_REQUIRED);

        validarObligatorio(telefono, PHONE_REQUIRED);
        validarVacio(telefono, PHONE_REQUIRED);

        validarObligatorio(direccion, ADRESS_REQUIRED);
        validarVacio(direccion, ADRESS_REQUIRED);

        this.id = id;
        this.nit = nit;
        this.razonSocial = razonSocial;
        this.nombreRepresentante = nombreRepresentante;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Cliente:" + id + ": {" +
                " Nit :" + nit + "," +
                " Razón social :" + razonSocial + "," +
                " Representante legal :" + nombreRepresentante + "," +
                " Teléfono :" + telefono + "," +
                " Dirección :" + direccion + " }";
    }
}
