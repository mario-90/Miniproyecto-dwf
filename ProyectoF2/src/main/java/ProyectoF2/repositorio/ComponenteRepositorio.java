package ProyectoF2.repositorio;

import ProyectoF2.modelo.Componente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComponenteRepositorio extends JpaRepository<Componente, Long> {
    List<Componente> findByNombreContainingIgnoreCase(String nombre);
}
