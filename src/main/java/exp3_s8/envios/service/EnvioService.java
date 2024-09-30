package exp3_s8.envios.service;

import java.util.List;
import java.util.Optional;

import exp3_s8.envios.model.Envio;

public interface EnvioService {
    List<Envio> getAllEnvios();

    Optional<Envio> getEnvioById(Long id);

    Envio createEnvio(Envio envio);

    Envio updateEnvio(Long id, Envio envio);

    void deleteEnvio(Long id);

}
