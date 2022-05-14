package com.ceiba.contrato.builders;

import com.ceiba.cliente.builders.ClienteTestDataBuilder;
import com.ceiba.cliente.modelo.entidad.Cliente;
import com.ceiba.contrato.modelo.entidad.Contrato;
import com.ceiba.contrato.modelo.enums.PaquetesContratos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContratoTestDataBuilder {

    private Long id;
    private Cliente cliente;
    private String tipoMoneda;
    private BigDecimal trmAplicada;
    private PaquetesContratos paquetesContrato;
    private LocalDate fechaInstalacion;

    public ContratoTestDataBuilder() {
        cliente = new ClienteTestDataBuilder().conId(1L).build();
        tipoMoneda = "COP";
        trmAplicada = new BigDecimal("3702.86");
        paquetesContrato = PaquetesContratos.getByName("BASICO");
        fechaInstalacion = LocalDate.now();

    }

    public ContratoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ContratoTestDataBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public ContratoTestDataBuilder conTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
        return this;
    }

    public ContratoTestDataBuilder conTrmAplicada(BigDecimal trmAplicada) {
        this.trmAplicada = trmAplicada;
        return this;
    }

    public ContratoTestDataBuilder conPaquete(String paquetesContrato) {
        this.paquetesContrato = PaquetesContratos.getByName(paquetesContrato);
        return this;
    }

    public ContratoTestDataBuilder conFechaInstalacion(LocalDate fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
        return this;
    }

    public Contrato build() {
        return new Contrato(
                id,
                cliente,
                tipoMoneda,
                trmAplicada,
                paquetesContrato,
                fechaInstalacion);
    }

}
