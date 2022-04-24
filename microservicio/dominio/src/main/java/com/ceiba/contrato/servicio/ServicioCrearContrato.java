package com.ceiba.contrato.servicio;

import com.ceiba.cliente.servicio.ServicioConsultaCliente;
import com.ceiba.contrato.modelo.entidad.Contrato;
import com.ceiba.contrato.modelo.entidad.ContratoBuilder;
import com.ceiba.contrato.modelo.enums.PaquetesContratos;
import com.ceiba.contrato.puerto.repositorio.RepositorioContrato;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.ceiba.contrato.modelo.enums.PaquetesContratos.COMPACT;
import static com.ceiba.contrato.modelo.enums.PaquetesContratos.PREMIUM;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

public class ServicioCrearContrato {

    private static final String EL_CONTRATO_YA_EXISTE_EN_EL_SISTEMA = "Para el cliente [%s], ya existe un contrato en el sistema.";
    private static final String CLIENTE_NO_EXISTENTE = "El cliente [%s] no fue encontrado.";

    private PaquetesContratos paqueteContrato;
    private LocalDate cutOffDate;

    private final RepositorioContrato repositorioContrato;
    private final ServicioConsultaCliente servicioConsultaCliente;

    public ServicioCrearContrato(RepositorioContrato repositorioContrato, ServicioConsultaCliente servicioConsultaCliente) {
        this.repositorioContrato = repositorioContrato;
        this.servicioConsultaCliente = servicioConsultaCliente;
    }

    public Long execute(Contrato contrato) {

        validarExistenciaContrato(contrato);
        validarExistenciaCliente(contrato);
        obtenerPaqueteContrato(contrato);
        calcularFechaCorte(contrato);

        contrato = new ContratoBuilder(contrato)
                .setContractTime(paqueteContrato.getMinPacketTime())
                .setCutOffDate(cutOffDate)
                .build();
        return this.repositorioContrato.crear(contrato);
    }

    private void validarExistenciaContrato(Contrato contrato) {
        boolean existe = this.repositorioContrato.existsByNit(contrato.getNitCustomer());
        if (existe)
            throw new ExcepcionDuplicidad(String.format(EL_CONTRATO_YA_EXISTE_EN_EL_SISTEMA, contrato.getNitCustomer()));
    }

    private void validarExistenciaCliente(Contrato contrato) {
        boolean existe = this.servicioConsultaCliente.existsByNit(contrato.getNitCustomer());
        if (!existe) throw new ExcepcionDuplicidad(String.format(CLIENTE_NO_EXISTENTE, contrato.getNitCustomer()));
    }

    private void obtenerPaqueteContrato(Contrato contrato) {
        paqueteContrato = PaquetesContratos.getByName(contrato.getPacket());
    }

    private void calcularFechaCorte(Contrato contrato) {
        ArrayList<DayOfWeek> excludedDays = excludeDays(paqueteContrato);
        getCutOffDate(contrato.getInstallDate(), excludedDays);
    }

    private ArrayList<DayOfWeek> excludeDays(PaquetesContratos paqueteContrato) {
        List<DayOfWeek> excludedDays = new ArrayList<>();
        if (paqueteContrato.equals(COMPACT)) {
            excludedDays.add(SUNDAY);
        } else if (paqueteContrato.equals(PREMIUM)) {
            excludedDays.add(SUNDAY);
            excludedDays.add(SATURDAY);
        }
        return new ArrayList<>(excludedDays);
    }

    private void getCutOffDate(LocalDate installDate, ArrayList<DayOfWeek> excludedDays) {
        cutOffDate = installDate;
        Integer dayCount = 0;
        while (dayCount < paqueteContrato.getFrequencyDayPayment()) {
            cutOffDate = cutOffDate.plusDays(1);
            boolean noExcluir = !excludedDays.contains(cutOffDate.getDayOfWeek());
            if (noExcluir) dayCount++;
        }
    }

}
