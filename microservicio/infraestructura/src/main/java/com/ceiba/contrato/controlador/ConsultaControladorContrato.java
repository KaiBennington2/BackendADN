package com.ceiba.contrato.controlador;

import com.ceiba.contrato.consulta.manejador.ManejadorDetalleContrato;
import com.ceiba.contrato.consulta.manejador.ManejadorListarContratos;
import com.ceiba.contrato.modelo.dto.DtoContrato;
import com.ceiba.contrato.modelo.dto.DtoContratoDetalle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/contratos")
@Api(tags = {"Controlador consulta contratos"})
public class ConsultaControladorContrato {

    private final ManejadorListarContratos manejadorListarContratos;
    private final ManejadorDetalleContrato manejadorDetalleContratos;

    @Autowired
    public ConsultaControladorContrato(ManejadorListarContratos manejadorListarContratos,
                                       ManejadorDetalleContrato manejadorDetalleContratos) {
        this.manejadorListarContratos = manejadorListarContratos;
        this.manejadorDetalleContratos = manejadorDetalleContratos;
    }

    @GetMapping
    @ApiOperation("listar Contratos")
    public List<DtoContrato> findAll() {
        return manejadorListarContratos.ejecutar();
    }

    @GetMapping("/{id}")
    @ApiOperation("listar contrato por id")
    public DtoContrato findById(@PathVariable("id") Long id) {
        return manejadorListarContratos.ejecutar(id);
    }

    @GetMapping(value = "/op", params = {"nit"})
    @ApiOperation("listar contrato por nit")
    public DtoContrato findByNit(@RequestParam(value = "nit", required = false) String nit) {
        return manejadorListarContratos.ejecutar(nit);
    }

    @GetMapping(value = "/op", params = {"detail"})
    @ApiOperation("obtener el detalle del contrato")
    public DtoContratoDetalle findDetailContract(@RequestParam(value = "detail") Long id) {
        return manejadorDetalleContratos.ejecutarDetalle(id);
    }

}
