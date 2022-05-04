package com.ceiba.cliente.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cliente.comando.dto.ComandoCliente;
import com.ceiba.cliente.comando.manejador.ManejadorActualizarClientes;
import com.ceiba.cliente.comando.manejador.ManejadorCrearClientes;
import com.ceiba.cliente.comando.manejador.ManejadorEliminarClientes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/clientes")
@Api(tags = {"Controlador de comando clientes"})
public class ComandoControladorCliente {

    private final ManejadorCrearClientes manejadorCrearClientes;
    private final ManejadorActualizarClientes manejadorActualizarClientes;
    private final ManejadorEliminarClientes manejadorEliminarClientes;

    @Autowired
    public ComandoControladorCliente(ManejadorCrearClientes manejadorCrearClientes,
                                     ManejadorActualizarClientes manejadorActualizarClientes,
                                     ManejadorEliminarClientes manejadorEliminarClientes) {
        this.manejadorCrearClientes = manejadorCrearClientes;
        this.manejadorActualizarClientes = manejadorActualizarClientes;
        this.manejadorEliminarClientes = manejadorEliminarClientes;
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

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Eliminar Cliente")
    public void delete(@PathVariable("id") Long id) {
        manejadorEliminarClientes.ejecutar(id);
    }
}
