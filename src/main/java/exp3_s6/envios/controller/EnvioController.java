package exp3_s6.envios.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exp3_s6.envios.model.Envio;
import exp3_s6.envios.service.EnvioService;

@RestController
@RequestMapping("/envios")
public class EnvioController {

    private static final Logger log = LoggerFactory.getLogger(EnvioController.class);

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public List<Envio> getAllEnvios() {
        log.info("GET /envios");
        log.info("Retornando todos los Envios");
        return envioService.getAllEnvios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEnvioById(@PathVariable Long id) {
        Optional<Envio> envio = envioService.getEnvioById(id);
        if (envio.isEmpty()) {
            log.error("No se encontró el envio con ID {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("No se encontró el envio con ID " + id));
        }
        return ResponseEntity.ok(envio);
    }

    @PostMapping
    public ResponseEntity<Object> createEnvio(@Validated @RequestBody Envio envio) {
        Envio createdEnvio = envioService.createEnvio(envio);
        if (createdEnvio == null) {
            log.error("Error al crear el envio {}", envio);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear el envio"));
        }
        return ResponseEntity.ok(createdEnvio);

    }

    @PutMapping("/{id}")
    public Envio updateEnvio(@PathVariable Long id, @RequestBody Envio envio) {
        return envioService.updateEnvio(id, envio);

    }

    @DeleteMapping("/{id}")
    public void deleteEnvio(@PathVariable Long id) {
        envioService.deleteEnvio(id);
    }

    static class ErrorResponse {
        private final String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

}
