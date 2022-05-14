package com.ceiba.contrato.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.contrato.puerto.repositorio.RepositorioContrato;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ServicioEliminarContratoTest {

    private String NOT_EXISTS = "el contrato no existe en el sistema.";

    @Test
    @DisplayName("Debería eliminar el contrato correctamente")
    void deberiaEliminarElContratoCorrectamente() {
        // arrange
        RepositorioContrato repositorioContrato = Mockito.mock(RepositorioContrato.class);
        ServicioEliminarContrato servicioEliminarContrato = new ServicioEliminarContrato(repositorioContrato);
        when(repositorioContrato.existsById(anyLong())).thenReturn(true);
        // act
        servicioEliminarContrato.ejecutar(1l);
        // assert
        Mockito.verify(repositorioContrato, Mockito.times(1)).delete(1l);
    }

    @Test
    @DisplayName("Debería lanzar excepción cuando el contrato no exista")
    void deberiaLanzarExcepcionNoExistenciaDeContrato() {
        // arrange
        RepositorioContrato repositorioContrato = Mockito.mock(RepositorioContrato.class);
        ServicioEliminarContrato servicioEliminarContrato = new ServicioEliminarContrato(repositorioContrato);
        when(repositorioContrato.existsById(anyLong())).thenReturn(false);
        // act - assert
        BasePrueba.assertThrows(() -> servicioEliminarContrato.ejecutar(1L),
                ExcepcionSinDatos.class, NOT_EXISTS);
    }

}