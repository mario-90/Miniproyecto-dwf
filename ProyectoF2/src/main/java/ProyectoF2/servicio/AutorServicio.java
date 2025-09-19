package ProyectoF2.servicio;

import ProyectoF2.modelo.Autor;
import java.util.List;
import java.util.Optional;

public interface AutorServicio {
    Autor guardarAutor(Autor autor);
    List<Autor> listarAutores();
    Optional<Autor> buscarPorId(Long id);
    void eliminarAutor(Long id);
}
