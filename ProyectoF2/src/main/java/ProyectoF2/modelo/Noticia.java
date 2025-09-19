package ProyectoF2.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El titulo no puede estar vacio")
    @Size(min = 5, max = 150, message = "El titulo debe tener entre 5 y 150 caracteres")
    private String titulo;

    @NotBlank(message = "El cuerpo de la noticia no puede estar vacio")
    @Column(columnDefinition = "TEXT")
    private String cuerpo;

    private LocalDateTime fechaPublicacion = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;
}
