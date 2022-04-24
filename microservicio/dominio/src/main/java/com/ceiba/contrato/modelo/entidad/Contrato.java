package com.ceiba.contrato.modelo.entidad;

import com.ceiba.contrato.modelo.enums.PaquetesContratos;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Contrato {

    private static final Integer LOGITUD_MAXIMA_NIT = 15;
    private static final String PATTERN_FECHA = "(19|20)[0-9]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";
    private static final String NIT_OBLIGATORIO = "El NIT es un requisito obligatorio.";
    private static final String NIT_LIMITE_LONGITUD = "El NIT no debe tener mas de %s dígitos.";
    private static final String COIN_TYPE_OBLIGATORIO = "El tipo de moneda es requerido.";
    private static final String TRM_OBLIGATORIO = "La tasa de cambio es requerida.";
    private static final String TRM_INVALIDA = "El valor de la tasa de cambio es invalido.";
    private static final String PACKET_OBLIGATORIO = "No se encontró ningún paquete de contrato.";
    private static final String INSTALLDATE_OBLIGATORIO = "La FECHA INSTALACIÓN es un requisito obligatorio.";
    private static final String INSTALLDATE_NO_CUMPLE_PATRON = "la FECHA INSTALACIÓN es incorrecta, recuerde el formato [yyyy-MM-dd].";

    private Long id;
    private String nitCustomer;
    private Integer contractTime;
    private String coinType;
    private BigDecimal trmApplied;
    private String packet;
    private LocalDate installDate;
    private LocalDate cutOffDate;
    private LocalDate cutOffBeforeDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public Contrato(Long id, String nitCustomer, String coinType, BigDecimal trmApplied, String packet, LocalDate installDate) {

        validarObligatorio(nitCustomer, NIT_OBLIGATORIO);
        validarLongitudMaxima(nitCustomer, LOGITUD_MAXIMA_NIT, String.format(NIT_LIMITE_LONGITUD, LOGITUD_MAXIMA_NIT));

        validarObligatorio(coinType, COIN_TYPE_OBLIGATORIO);

        validarObligatorio(trmApplied, TRM_OBLIGATORIO);
        validarPositivo(trmApplied.doubleValue(), TRM_INVALIDA);

        validarObligatorio(packet, PACKET_OBLIGATORIO);
        validarVacio(packet, PACKET_OBLIGATORIO);
        PaquetesContratos.getByName(packet);

        validarObligatorio(installDate, INSTALLDATE_OBLIGATORIO);
        validarRegex(installDate.toString(), PATTERN_FECHA, INSTALLDATE_NO_CUMPLE_PATRON);

        this.id = id;
        this.nitCustomer = nitCustomer;
        this.coinType = coinType;
        this.trmApplied = trmApplied;
        this.packet = packet;
        this.installDate = installDate;
    }

    protected Contrato(Long id, String nitCustomer, Integer contractTime, String coinType, BigDecimal trmApplied, String packet, LocalDate installDate, LocalDate cutOffDate, LocalDate cutOffBeforeDate) {
        this.id = id;
        this.nitCustomer = nitCustomer;
        this.contractTime = contractTime;
        this.coinType = coinType;
        this.trmApplied = trmApplied;
        this.packet = packet;
        this.installDate = installDate;
        this.cutOffDate = cutOffDate;
        this.cutOffBeforeDate = cutOffBeforeDate;
    }

    @Override
    public String toString() {

        return "Contrato:" + id + ": ->{" +
                " Nit cliente :" + nitCustomer + "," +
                " Duración contrato en meses :" + contractTime + "," +
                " Tipo de moneda :" + coinType + "," +
                " Trm Aplicada :" + trmApplied + "," +
                " Paquete :" + packet + "," +
                " Fecha instalación :" + installDate + "," +
                " Fecha de corte :" + cutOffDate + "," +
                " Fecha de corte anterior :" + cutOffBeforeDate + " }";

    }
}
