package ProyectoF2.servicio;

import ProyectoF2.modelo.Noticia;
import java.util.List;
import java.util.Optional;

public interface NoticiaServicio {
    Noticia guardarNoticia(Noticia noticia);
    List<Noticia> listarNoticias();
    Optional<Noticia> buscarPorId(Long id);
    List<Noticia> buscarPorAutor(Long autorId);
    void eliminarNoticia(Long id);
}

