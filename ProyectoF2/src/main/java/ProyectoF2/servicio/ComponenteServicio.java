package ProyectoF2.servicio;

import ProyectoF2.modelo.Componente;
import java.util.List;
import java.util.Optional;

public interface ComponenteServicio {
    Componente guardarComponente(Componente componente);
    List<Componente> listarComponentes();
    Optional<Componente> buscarPorId(Long id);
    void eliminarComponente(Long id);
}
