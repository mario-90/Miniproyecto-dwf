package ProyectoF2.servicio.impl;

import ProyectoF2.modelo.Componente;
import ProyectoF2.repositorio.ComponenteRepositorio;
import ProyectoF2.servicio.ComponenteServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComponenteServicioImpl implements ComponenteServicio {

    private final ComponenteRepositorio componenteRepositorio;

    public ComponenteServicioImpl(ComponenteRepositorio componenteRepositorio) {
        this.componenteRepositorio = componenteRepositorio;
    }

    @Override
    public Componente guardarComponente(Componente componente) {
        return componenteRepositorio.save(componente);
    }

    @Override
    public List<Componente> listarComponentes() {
        return componenteRepositorio.findAll();
    }

    @Override
    public Optional<Componente> buscarPorId(Long id) {
        return componenteRepositorio.findById(id);
    }

    @Override
    public void eliminarComponente(Long id) {
        componenteRepositorio.deleteById(id);
    }
}
