package com.ceiba.cliente.modelo.entidad;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Cliente {

    private static final Integer NIT_MIN_LENGHT = 5;
    private static final Integer NIT_MAX_LENGHT = 15;
    private static final String NIT_LIMITE_LONGITUD = String.format("El NIT no debe tener %s min y %s max de dígitos.",NIT_MIN_LENGHT,NIT_MAX_LENGHT);
    private static final String NIT_OBLIGATORIO = "El nit de cliente es un campo requerido.";
    private static final String RAZON_SOCIAL_OBLIGATORIO = "La razón Social es un campo requerido.";
    private static final String NOMBRE_REPREST_OBLIGATORIO = "El nombre del representante es un campo requerido.";
    private static final String TELEFONO_OBLIGATORIO = "El teléfono es un campo requerido.";
    private static final String DIRECCION_OBLIGATORIO = "La dirección de residencia es requerida.";

    private Long id;
    private String nit;
    private String razonSocial;
    private String nombreRepresentante;
    private String telefono;
    private String direccion;

    public Cliente(Long id, String nit, String razonSocial, String nombreRepresentante, String telefono, String direccion) {

        validarObligatorio(nit, NIT_OBLIGATORIO);
        validarLongitudMinima(nit, NIT_MIN_LENGHT,NIT_LIMITE_LONGITUD);
        validarLongitudMaxima(nit, NIT_MAX_LENGHT,NIT_LIMITE_LONGITUD);
        validarObligatorio(razonSocial, RAZON_SOCIAL_OBLIGATORIO);
        validarObligatorio(nombreRepresentante, NOMBRE_REPREST_OBLIGATORIO);
        validarObligatorio(telefono, TELEFONO_OBLIGATORIO);
        validarObligatorio(direccion, DIRECCION_OBLIGATORIO);

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
