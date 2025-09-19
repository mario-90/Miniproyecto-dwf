package ProyectoF2.repositorio;

import ProyectoF2.modelo.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticiaRepositorio extends JpaRepository<Noticia, Long> {
    List<Noticia> findByTituloContaining(String palabraClave);
    List<Noticia> findByAutorId(Long autorId);
}
