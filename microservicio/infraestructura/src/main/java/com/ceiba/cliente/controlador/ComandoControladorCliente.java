package com.ceiba.cliente.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cliente.comando.dto.ComandoCliente;
import com.ceiba.cliente.comando.manejador.ManejadorActualizarClientes;
import com.ceiba.cliente.comando.manejador.ManejadorCrearClientes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Api(tags = {"Controlador de comando clientes"})
public class ComandoControladorCliente {

    private final ManejadorCrearClientes manejadorCrearClientes;
    private final ManejadorActualizarClientes manejadorActualizarClientes;

    @Autowired
    public ComandoControladorCliente(ManejadorCrearClientes manejadorCrearClientes,
                                     ManejadorActualizarClientes manejadorActualizarClientes) {
        this.manejadorCrearClientes = manejadorCrearClientes;
        this.manejadorActualizarClientes = manejadorActualizarClientes;
    }

    @PostMapping
    @ApiOperation("crear cliente")
    public ComandoRespuesta<Long> create(@RequestBody ComandoCliente comandoCliente) {
        return manejadorCrearClientes.ejecutar(comandoCliente);

    }

    @PutMapping("/{id}")
    @ApiOperation("modificar cliente")
    public void update(@PathVariable("id") Long id, @RequestBody ComandoCliente comandoCliente) {
        comandoCliente.setId(id);
        manejadorActualizarClientes.ejecutar(comandoCliente);
    }
}
