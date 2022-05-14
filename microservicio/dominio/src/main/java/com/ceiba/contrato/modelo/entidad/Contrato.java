package com.ceiba.contrato.modelo.entidad;

import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.contrato.modelo.enums.PaquetesContratos;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.ceiba.contrato.modelo.enums.PaquetesContratos.COMPACTO;
import static com.ceiba.contrato.modelo.enums.PaquetesContratos.PREMIUM;
import static com.ceiba.dominio.ValidadorArgumento.*;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

@Getter
public class Contrato {

    private static final String PATTERN_FECHA = "(19|20)[0-9]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])";
    private static final String CLIENTE_OBLIGATORIO = "El Cliente para generar un contrato es requisito obligatorio.";
    private static final String TIPO_MONEDA_OBLIGATORIO = "El tipo de moneda es requerido.";
    private static final String TRM_OBLIGATORIO = "La tasa de cambio es requerida.";
    private static final String TRM_INVALIDA = "El valor de la tasa de cambio es invalido.";
    private static final String PAQUETE_OBLIGATORIO = "No se encontró ningún paquete de contrato.";
    private static final String FECHA_INSTALACION_OBLIGATORIO = "La Fecha Instalación es un requisito obligatorio.";
    private static final String FECHA_INSTALACION_NO_CUMPLE_PATRON = "la Fecha Instalación es incorrecta, recuerde el formato [yyyy-MM-dd].";

    private Long id;
    private Cliente cliente;
    private Integer tiempoContratoMeses;
    private String tipoMoneda;
    private BigDecimal trmAplicada;
    private PaquetesContratos paqueteContrato;
    private LocalDate fechaInstalacion;
    private LocalDate fechaCorte;
    private LocalDate fechaCorteAnterior;

    public Contrato(Long id, Cliente cliente, String tipoMoneda, BigDecimal trmAplicada, PaquetesContratos paqueteContrato, LocalDate fechaInstalacion) {

        validarObligatorio(cliente, CLIENTE_OBLIGATORIO);
        validarObligatorio(tipoMoneda, TIPO_MONEDA_OBLIGATORIO);
        validarObligatorio(trmAplicada, TRM_OBLIGATORIO);
        validarPositivo(trmAplicada.doubleValue(), TRM_INVALIDA);
        validarObligatorio(paqueteContrato, PAQUETE_OBLIGATORIO);
        validarObligatorio(fechaInstalacion, FECHA_INSTALACION_OBLIGATORIO);
        validarRegex(fechaInstalacion.toString(), PATTERN_FECHA, FECHA_INSTALACION_NO_CUMPLE_PATRON);

        this.id = id;
        this.cliente = cliente;
        this.tiempoContratoMeses = paqueteContrato.getTiempoPaqueteEnMeses();
        this.tipoMoneda = tipoMoneda;
        this.trmAplicada = trmAplicada;
        this.paqueteContrato = paqueteContrato;
        this.fechaInstalacion = fechaInstalacion;
        this.fechaCorte = calcularFechaCorte(fechaInstalacion);
    }

    private LocalDate calcularFechaCorte(LocalDate fechaInstalacion) {
        LocalDate fechaCorteRsp = fechaInstalacion;
        int contadorDias = 0;
        while (contadorDias < paqueteContrato.getFrecuenciaPagoEnDias()) {
            fechaCorteRsp = fechaCorteRsp.plusDays(1);
            boolean noExcluir = !excluirDiasPaqueteContrato().contains(fechaCorteRsp.getDayOfWeek());
            if (noExcluir) { contadorDias++; }
        }
        return fechaCorteRsp;
    }

    private ArrayList<DayOfWeek> excluirDiasPaqueteContrato() {
        List<DayOfWeek> excludedDays = new ArrayList<>();
        if (paqueteContrato.equals(COMPACTO)) {
            excludedDays.add(SUNDAY);
        } else if (paqueteContrato.equals(PREMIUM)) {
            excludedDays.add(SUNDAY);
            excludedDays.add(SATURDAY);
        }
        return new ArrayList<>(excludedDays);
    }

    @Override
    public String toString() {
        return "Contrato:" + id + ": {" +
                " Cliente :[ " + cliente + " ]," +
                " Duración contrato en meses :" + tiempoContratoMeses + "," +
                " Tipo de moneda :" + tipoMoneda + "," +
                " Trm Aplicada :" + trmAplicada + "," +
                " Paquete :" + paqueteContrato + "," +
                " Fecha instalación :" + fechaInstalacion + "," +
                " Fecha de corte :" + fechaCorte + "," +
                " Fecha de corte anterior :" + fechaCorteAnterior + " }";
    }
}
