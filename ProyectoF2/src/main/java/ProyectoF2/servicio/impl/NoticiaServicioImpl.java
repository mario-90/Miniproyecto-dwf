package ProyectoF2.servicio.impl;

import ProyectoF2.modelo.Noticia;
import ProyectoF2.repositorio.NoticiaRepositorio;
import ProyectoF2.servicio.NoticiaServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticiaServicioImpl implements NoticiaServicio {

    private final NoticiaRepositorio noticiaRepositorio;

    public NoticiaServicioImpl(NoticiaRepositorio noticiaRepositorio) {
        this.noticiaRepositorio = noticiaRepositorio;
    }

    @Override
    public Noticia guardarNoticia(Noticia noticia) {
        return noticiaRepositorio.save(noticia);
    }

    @Override
    public List<Noticia> listarNoticias() {
        return noticiaRepositorio.findAll();
    }

    @Override
    public Optional<Noticia> buscarPorId(Long id) {
        return noticiaRepositorio.findById(id);
    }

    @Override
    public List<Noticia> buscarPorAutor(Long autorId) {
        return noticiaRepositorio.findByAutorId(autorId);
    }

    @Override
    public void eliminarNoticia(Long id) {
        noticiaRepositorio.deleteById(id);
    }
}

