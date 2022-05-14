package com.ceiba.contrato.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.servicio.ServicioConsultaCliente;
import com.ceiba.contrato.builders.ContratoTestDataBuilder;
import com.ceiba.contrato.modelo.entidad.Contrato;
import com.ceiba.contrato.puerto.repositorio.RepositorioContrato;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
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

    private static final String CLIENTE_NO_EXISTENTE = "El cliente [%s] no fue encontrado.";
    private Contrato contrato;
    //    @Mock
    private RepositorioContrato repositorioContrato;
    private ServicioConsultaCliente servicioConsultaCliente;
    //    @InjectMocks
    private ServicioCrearContrato servicioCrearContrato;

    @BeforeEach
    void setUp() {
        repositorioContrato = Mockito.mock(RepositorioContrato.class);
        servicioConsultaCliente = Mockito.mock(ServicioConsultaCliente.class);
        servicioCrearContrato = new ServicioCrearContrato(repositorioContrato, servicioConsultaCliente);
    }

    @Test
    @DisplayName("Deberia crear un contrato con paquete Basico")
    void deberiaCrearContratoDeManeraCorrecta() {
        // arrange
        contrato = new ContratoTestDataBuilder().build();
        Mockito.when(repositorioContrato.crear(ArgumentMatchers.any(Contrato.class))).thenReturn(1L);
        Mockito.when(repositorioContrato.existsByNit(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(servicioConsultaCliente.existsByNit(ArgumentMatchers.anyString())).thenReturn(true);
        // act
        Long ejecutar = servicioCrearContrato.execute(contrato);
        // Assert
        assertEquals(1L, ejecutar);
    }

    @Test
    @DisplayName("Deberia crear un contrato con paquete Premium")
    void deberiaCrearContratoDeManeraCorrectaConPaquetePremium() {
        // arrange
        contrato = new ContratoTestDataBuilder().conPaquete("PREMIUM").build();
        Mockito.when(repositorioContrato.crear(ArgumentMatchers.any(Contrato.class))).thenReturn(1L);
        Mockito.when(repositorioContrato.existsByNit(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(servicioConsultaCliente.existsByNit(ArgumentMatchers.anyString())).thenReturn(true);
        // act
        Long ejecutar = servicioCrearContrato.execute(contrato);
        // Assert
        assertEquals(1L, ejecutar);
    }

    @Test
    @DisplayName("Deberia crear un contrato con paquete Compact")
    void deberiaCrearContratoDeManeraCorrectaConPaqueteCompact() {
        // arrange
        contrato = new ContratoTestDataBuilder().conPaquete("COMPACT").build();
        Mockito.when(repositorioContrato.crear(ArgumentMatchers.any(Contrato.class))).thenReturn(1L);
        Mockito.when(repositorioContrato.existsByNit(ArgumentMatchers.anyString())).thenReturn(false);
        Mockito.when(servicioConsultaCliente.existsByNit(ArgumentMatchers.anyString())).thenReturn(true);
        // act
        Long ejecutar = servicioCrearContrato.execute(contrato);
        // Assert
        assertEquals(1L, ejecutar);
    }

    @Test
    @DisplayName("Deberia lanzar una excepcion por existencia del contrato en el sistema")
    void deberiaLanzarExceptionPorExistenciaDelContrato() {
        // arrange
        contrato = new ContratoTestDataBuilder().build();
        when(repositorioContrato.existsByNit(anyString())).thenReturn(true);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearContrato.execute(contrato),
                ExcepcionDuplicidad.class,
                String.format("Para el cliente [%s], ya existe un contrato en el sistema.",
                        contrato.getCliente().getNit()));
    }

    @Test
    @DisplayName("Deberia lanzar una excepcion por que el nit de cliente no existe.")
    void deberiaLanzarExceptionPorNoExistenciaDelCliente() {
        // arrange
        contrato = new ContratoTestDataBuilder().build();
        when(servicioConsultaCliente.existsByNit(anyString())).thenReturn(false);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearContrato.execute(contrato),
                ExcepcionSinDatos.class,
                String.format(CLIENTE_NO_EXISTENTE, contrato.getCliente().getNit()));
    }
}