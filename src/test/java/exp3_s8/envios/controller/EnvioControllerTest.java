package exp3_s8.envios.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import exp3_s8.envios.model.Envio;
import exp3_s8.envios.service.EnvioService;

@ExtendWith(MockitoExtension.class)
class EnvioControllerTest {

    @Mock
    private EnvioService envioService;

    @InjectMocks
    private EnvioController envioController;

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

        when(envioService.createEnvio(any(Envio.class))).thenReturn(envio);
        // Act
        ResponseEntity<EntityModel<Envio>> response = envioController.createEnvio(envio);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        EntityModel<Envio> savedEnvioModel = response.getBody();
        assertNotNull(savedEnvioModel);
        assertEquals(envio.getIdEnvio(), savedEnvioModel.getContent().getIdEnvio());
        assertTrue(savedEnvioModel.hasLink("self"));
        verify(envioService).createEnvio(any(Envio.class));
    }

    @Test
    void getEnvioById() {
        // Arrange
        when(envioService.getEnvioById(1L)).thenReturn(Optional.of(envio));

        // Act
        ResponseEntity<EntityModel<Envio>> response = envioController.getEnvioById(1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        EntityModel<Envio> foundEnvioModel = response.getBody();
        assertNotNull(foundEnvioModel);
        assertEquals(envio.getIdEnvio(), foundEnvioModel.getContent().getIdEnvio());
        assertTrue(foundEnvioModel.hasLink("self"));
        verify(envioService).getEnvioById(1L);
    }

}
