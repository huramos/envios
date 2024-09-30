package exp3_s8.envios.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
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

import exp3_s8.envios.model.Envio;
import exp3_s8.envios.service.EnvioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/envios")
@RequiredArgsConstructor
public class EnvioController {

    private static final Logger log = LoggerFactory.getLogger(EnvioController.class);

    @Autowired
    private final EnvioService envioService;

    @GetMapping
    public List<Envio> getAllEnvios() {
        log.info("GET /envios");
        log.info("Retornando todos los Envios");
        return envioService.getAllEnvios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Envio>> getEnvioById(@PathVariable Long id) {
        log.info("Getting a envio by ID: {}", id);
        Optional<Envio> envio = envioService.getEnvioById(id);
        if (envio.isPresent()) {
            EntityModel<Envio> envioModel = EntityModel.of(envio.get());
            envioModel.add(linkTo(methodOn(EnvioController.class).getEnvioById(id)).withSelfRel());

            return ResponseEntity.ok(envioModel);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<EntityModel<Envio>> createEnvio(@Validated @RequestBody Envio envio) {
        log.info("Creating a new envio with request: {}", envio);
        Envio createEnvio = envioService.createEnvio(envio);
        log.info("Envio created successfully. Envio ID: {}", createEnvio.getIdEnvio());

        EntityModel<Envio> envioModel = EntityModel.of(createEnvio);
        envioModel.add(linkTo(methodOn(EnvioController.class).getEnvioById(createEnvio.getIdEnvio())).withSelfRel());

        return ResponseEntity.created(
                linkTo(methodOn(EnvioController.class).getEnvioById(createEnvio.getIdEnvio())).toUri())
                .body(envioModel);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEnvio(@PathVariable Long id, @RequestBody Envio envio) {
        log.info("Update a Envio with ID: {}", id);
        Optional<Envio> envio2 = envioService.getEnvioById(id);
        if (envio2.isPresent()) {
            envioService.updateEnvio(id, envio);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnvio(@PathVariable Long id) {
        log.info("Deleting a Envio with ID: {}", id);
        Optional<Envio> envio = envioService.getEnvioById(id);
        if (envio.isPresent()) {
            envioService.deleteEnvio(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
