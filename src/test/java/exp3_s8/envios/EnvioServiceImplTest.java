package exp3_s8.envios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import exp3_s8.envios.model.Envio;
import exp3_s8.envios.repository.EnvioRepository;
import exp3_s8.envios.service.EnvioServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EnvioServiceImplTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioServiceImpl envioService;

    private Envio envio;

    @BeforeEach
    void setUp() {
        envio = new Envio();
        envio.setIdEnvio(1L);
        envio.setDestinatarioEnvio("Hugo Ramos");
        envio.setFechaEnvio("2024-09-30");
        envio.setDireccionEnvio("Calle 1, Puente Alto");
        envio.setEstadoEnvio("Pendiente");
        envio.setUbicacionEnvio("EN BODEGA");
    }

    @Test
    void createEnvio() {
        // Arrange
        when(envioRepository.save(envio)).thenReturn(envio);

        // Act
        Envio createEnvio = envioService.createEnvio(envio);

        // Assert
        assertEquals(envio, createEnvio);
        verify(envioRepository).save(envio);
    }

    @Test
    void getEnvioById() {
        // Arrange
        when(envioRepository.findById(envio.getIdEnvio())).thenReturn(Optional.of(envio));

        // Act
        Optional<Envio> foundEnvio = envioService.getEnvioById(envio.getIdEnvio());

        // Assert
        assertTrue(foundEnvio.isPresent());
        assertEquals(envio, foundEnvio.get());
        verify(envioRepository).findById(envio.getIdEnvio());
    }

    @Test
    void getAllEnvios() {
        // Arrange
        when(envioRepository.findAll()).thenReturn(List.of(envio));

        // Act
        List<Envio> foundEnvios = envioService.getAllEnvios();

        // Assert
        assertEquals(List.of(envio), foundEnvios);
        verify(envioRepository).findAll();
    }
}
