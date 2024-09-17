package exp3_s6.envios.model;

import org.springframework.hateoas.RepresentationModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "envio")
public class Envio extends RepresentationModel<Envio> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEnvio")
    private Long idEnvio;
    @NotBlank(message = "No puede ingresar un destinatario vacio")
    @Column(name = "destinatarioEnvio")
    private String destinatarioEnvio;
    @Size(max=10)
    @NotBlank(message = "No puede ingresar una fecha vacia")
    @Column(name = "fechaEnvio")
    private String fechaEnvio;
    @NotBlank(message = "No puede ingresar una direccion vacia")
    @Column(name = "direccionEnvio")
    private String direccionEnvio;
    @NotBlank(message = "No puede ingresar un estado vacio")
    @Column(name = "estadoEnvio")
    private String estadoEnvio;
    @NotBlank(message = "No puede ingresar una ubicacion vacia")
    @Column(name = "ubicacionEnvio")
    private String ubicacionEnvio;

    public Long getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Long idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getDestinatarioEnvio() {
        return destinatarioEnvio;
    }

    public void setDestinatarioEnvio(String destinatarioEnvio) {
        this.destinatarioEnvio = destinatarioEnvio;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(String fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public String getUbicacionEnvio() {
        return ubicacionEnvio;
    }

    public void setUbicacionEnvio(String ubicacionEnvio) {
        this.ubicacionEnvio = ubicacionEnvio;
    }
}
