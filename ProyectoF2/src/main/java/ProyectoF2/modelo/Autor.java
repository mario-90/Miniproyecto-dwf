package ProyectoF2.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @Email(message = "Debe ser un correo válido")
    @NotBlank(message = "El correo no puede estar vacio")
    private String correo;
}
