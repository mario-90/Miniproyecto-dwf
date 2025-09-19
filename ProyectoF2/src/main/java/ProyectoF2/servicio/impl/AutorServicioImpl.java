package ProyectoF2.servicio.impl;

import ProyectoF2.modelo.Autor;
import ProyectoF2.repositorio.AutorRepositorio;
import ProyectoF2.servicio.AutorServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServicioImpl implements AutorServicio {

    private final AutorRepositorio autorRepositorio;

    public AutorServicioImpl(AutorRepositorio autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
    }

    @Override
    public Autor guardarAutor(Autor autor) {
        return autorRepositorio.save(autor);
    }

    @Override
    public List<Autor> listarAutores() {
        return autorRepositorio.findAll();
    }

    @Override
    public Optional<Autor> buscarPorId(Long id) {
        return autorRepositorio.findById(id);
    }

    @Override
    public void eliminarAutor(Long id) {
        autorRepositorio.deleteById(id);
    }
}