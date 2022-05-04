package com.ceiba.contrato.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.contrato.comando.dto.ComandoContrato;
import com.ceiba.contrato.comando.manejador.ManejadorCrearContrato;
import com.ceiba.contrato.comando.manejador.ManejadorEliminarContratos;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/contratos")
@Api(tags = {"Controlador comando contratos"})
public class ComandoControladorContrato {

    private final ManejadorCrearContrato manejadorCrearContrato;
    private final ManejadorEliminarContratos manejadorEliminarContratos;

    @Autowired
    public ComandoControladorContrato(ManejadorCrearContrato manejadorCrearContrato,
                                      ManejadorEliminarContratos manejadorEliminarContratos) {
        this.manejadorCrearContrato = manejadorCrearContrato;
        this.manejadorEliminarContratos = manejadorEliminarContratos;
    }

    @PostMapping
    @ApiOperation("Crear Contrato")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoContrato comandoContrato) {
        return manejadorCrearContrato.ejecutar(comandoContrato);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Eliminar Contrato")
    public void delete(@PathVariable("id") Long id) {
        manejadorEliminarContratos.ejecutar(id);
    }
}
