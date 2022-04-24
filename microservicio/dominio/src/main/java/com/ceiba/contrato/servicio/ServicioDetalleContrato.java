package com.ceiba.contrato.servicio;

import com.ceiba.contrato.modelo.dto.DtoContrato;
import com.ceiba.contrato.modelo.dto.DtoContratoDetalle;
import com.ceiba.contrato.modelo.enums.PaquetesContratos;
import com.ceiba.contrato.modelo.enums.SalariosMinimosAnnos;
import com.ceiba.contrato.puerto.dao.DaoContrato;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

import java.math.BigDecimal;

public class ServicioDetalleContrato {

    private static final String NO_EXISTE_EL_CONTRATO = "No se encontraron datos en el sistema.";

    private final DaoContrato daoContrato;

    public ServicioDetalleContrato(DaoContrato daoContrato) {
        this.daoContrato = daoContrato;
    }

    public DtoContratoDetalle ejecutar(Long id) {
        validarExistenciaPrevia(id);
        return crearDtoContratoDetalle(daoContrato.findById(id));
    }

    private void validarExistenciaPrevia(Long id) {
        boolean existe = this.daoContrato.existsById(id);
        if (!existe) throw new ExcepcionSinDatos(NO_EXISTE_EL_CONTRATO);
    }

    private DtoContratoDetalle crearDtoContratoDetalle(DtoContrato dtoContrato) {

        Integer anno = dtoContrato.getInstallDate().getYear();
        SalariosMinimosAnnos salario = SalariosMinimosAnnos.getByYear(anno);
        PaquetesContratos paquete = PaquetesContratos.getByName(dtoContrato.getPacket());
        BigDecimal valorBase = salario.getSmmlv();
        BigDecimal valorPorcentajeAplicado = valorBase.multiply(paquete.getBasePorcentage()).divide(new BigDecimal(100), BigDecimal.ROUND_HALF_EVEN);
        BigDecimal valorContrato = valorBase.add(valorPorcentajeAplicado);

        if ("USD".equalsIgnoreCase(dtoContrato.getCoinType())) {
            valorContrato = valorContrato.divide(dtoContrato.getTrmApplied(), BigDecimal.ROUND_HALF_EVEN);
        }
        return new DtoContratoDetalle(
                dtoContrato.getId(),
                dtoContrato.getNitCustomer(),
                dtoContrato.getContractTime(),
                dtoContrato.getCoinType(),
                dtoContrato.getTrmApplied(),
                dtoContrato.getPacket(),
                dtoContrato.getInstallDate(),
                dtoContrato.getCutOffDate(),
                dtoContrato.getCutOffBeforeDate(),
                valorContrato
        );
    }
}
