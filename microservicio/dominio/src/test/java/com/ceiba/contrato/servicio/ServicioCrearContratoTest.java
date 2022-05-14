package com.ceiba.contrato.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.contrato.builders.ContratoTestDataBuilder;
import com.ceiba.contrato.modelo.entidad.Contrato;
import com.ceiba.contrato.puerto.repositorio.RepositorioContrato;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicioCrearContratoTest {

    private String EL_CONTRATO_YA_EXISTE_EN_EL_SISTEMA = "Para el cliente [%s], ya existe un contrato en el sistema.";

    //    @Mock
    private RepositorioContrato repositorioContrato;
    //    @InjectMocks
    private ServicioCrearContrato servicioCrearContrato;

    @BeforeEach
    void setUp() {
        repositorioContrato = Mockito.mock(RepositorioContrato.class);
        servicioCrearContrato = new ServicioCrearContrato(repositorioContrato);
    }

    @Test
    @DisplayName("Deberia crear un contrato con paquete Basico")
    void deberiaCrearContratoDeManeraCorrecta() {
        // arrange
        Contrato contrato = new ContratoTestDataBuilder().build();
        Mockito.when(repositorioContrato.existsByNit(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(repositorioContrato.crear(ArgumentMatchers.any(Contrato.class))).thenReturn(1L);
        // act
        Long ejecutar = servicioCrearContrato.execute(contrato);
        // Assert
        assertEquals(1L, ejecutar);
    }

    @Test
    @DisplayName("Deberia crear un contrato con paquete Premium")
    void deberiaCrearContratoDeManeraCorrectaConPaquetePremium() {
        // arrange
        Contrato contrato = new ContratoTestDataBuilder().conPaquete("PREMIUM").build();
        Mockito.when(repositorioContrato.existsByNit(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(repositorioContrato.crear(ArgumentMatchers.any(Contrato.class))).thenReturn(1L);
        // act
        Long ejecutar = servicioCrearContrato.execute(contrato);
        // Assert
        assertEquals(1L, ejecutar);
    }

    @Test
    @DisplayName("Deberia crear un contrato con paquete Compacto")
    void deberiaCrearContratoDeManeraCorrectaConPaqueteCompact() {
        // arrange
        Contrato contrato = new ContratoTestDataBuilder().conPaquete("compacto").build();
        Mockito.when(repositorioContrato.existsByNit(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(repositorioContrato.crear(ArgumentMatchers.any(Contrato.class))).thenReturn(1L);
        // act
        Long ejecutar = servicioCrearContrato.execute(contrato);
        // Assert
        assertEquals(1L, ejecutar);
    }

    @Test
    @DisplayName("Debería lanzar una excepción por existencia del contrato en el sistema")
    void deberiaLanzarExceptionPorExistenciaDelContrato() {
        // arrange
        Contrato contrato = new ContratoTestDataBuilder().build();
        when(repositorioContrato.existsByNit(anyString())).thenReturn(true);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearContrato.execute(contrato),
                ExcepcionDuplicidad.class,
                String.format(EL_CONTRATO_YA_EXISTE_EN_EL_SISTEMA,
                        contrato.getCliente().getNit()));
    }
}