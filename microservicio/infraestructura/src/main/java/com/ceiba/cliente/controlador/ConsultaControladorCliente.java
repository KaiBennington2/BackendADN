package com.ceiba.cliente.controlador;

import com.ceiba.cliente.consulta.ManejadorListarClientes;
import com.ceiba.cliente.modelo.dto.DtoCliente;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/clientes")
@Api(tags = {"controlador de consulta clientes"})
public class ConsultaControladorCliente {

    private final ManejadorListarClientes manejadorListarClientes;

    @Autowired
    public ConsultaControladorCliente(ManejadorListarClientes manejadorListarClientes) {
        this.manejadorListarClientes = manejadorListarClientes;
    }

    @GetMapping
    @ApiOperation("listar todos los cliente")
    public List<DtoCliente> listarTodos() {
        return manejadorListarClientes.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("listar cliente por id")
    public DtoCliente listarPorId(@PathVariable("id") Long id) {
        return manejadorListarClientes.findById(id);
    }

    @GetMapping(value = "/find", params = {"nit"})
    @ApiOperation("listar cliente por nit")
    public DtoCliente listarPorNit(@RequestParam(value = "nit", required = false) String nit) {
        return manejadorListarClientes.findByNit(nit);
    }
}
