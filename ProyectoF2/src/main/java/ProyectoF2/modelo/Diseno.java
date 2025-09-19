package ProyectoF2.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Diseno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del diseño no puede estar vacio")
    private String nombre;

    private String descripcion;

    @ManyToMany
    @JoinTable(
            name = "diseno_componentes",
            joinColumns = @JoinColumn(name = "diseno_id"),
            inverseJoinColumns = @JoinColumn(name = "componente_id")
    )
    @com.fasterxml.jackson.annotation.JsonIgnoreProperties("disenos")
    private Set<Componente> componentes = new HashSet<>();
}
