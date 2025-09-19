package ProyectoF2.servicio;

import ProyectoF2.modelo.Diseno;
import java.util.List;
import java.util.Optional;

public interface DisenoServicio {
    Diseno guardarDiseno(Diseno diseno);
    List<Diseno> listarDisenos();
    Optional<Diseno> buscarPorId(Long id);
    void eliminarDiseno(Long id);
}

