package exp3_s8.envios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exp3_s8.envios.model.Envio;
import exp3_s8.envios.repository.EnvioRepository;

@Service
public class EnvioServiceImpl implements EnvioService {
    @Autowired
    private EnvioRepository envioRepository;

    @Override
    public List<Envio> getAllEnvios() {
        return envioRepository.findAll();
    }

    @Override
    public Optional<Envio> getEnvioById(Long id) {
        return envioRepository.findById(id);
    }

    @Override
    public Envio createEnvio(Envio envio) {
        return envioRepository.save(envio);
    }

    @Override
    public Envio updateEnvio(Long id, Envio envio) {
        if (envioRepository.existsById(id)) {
            envio.setIdEnvio(id);
            return envioRepository.save(envio);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEnvio(Long id) {
        envioRepository.deleteById(id);
    }
}