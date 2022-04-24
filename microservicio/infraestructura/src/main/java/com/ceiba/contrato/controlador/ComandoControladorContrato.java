package com.ceiba.contrato.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.contrato.comando.dto.ComandoContrato;
import com.ceiba.contrato.comando.manejador.ManejadorCrearContrato;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contratos")
@Api(tags = {"Controlador comando contratos"})
public class ComandoControladorContrato {

    private final ManejadorCrearContrato manejadorCrearContrato;

    @Autowired
    public ComandoControladorContrato(ManejadorCrearContrato manejadorCrearContrato) {
        this.manejadorCrearContrato = manejadorCrearContrato;
    }

    @PostMapping
    @ApiOperation("Crear Contrato")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoContrato comandoContrato) {
        return manejadorCrearContrato.ejecutar(comandoContrato);
    }
}
