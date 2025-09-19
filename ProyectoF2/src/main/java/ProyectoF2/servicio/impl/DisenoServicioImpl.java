package ProyectoF2.servicio.impl;

import ProyectoF2.modelo.Diseno;
import ProyectoF2.repositorio.DisenoRepositorio;
import ProyectoF2.servicio.DisenoServicio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisenoServicioImpl implements DisenoServicio {

    private final DisenoRepositorio disenoRepositorio;

    public DisenoServicioImpl(DisenoRepositorio disenoRepositorio) {
        this.disenoRepositorio = disenoRepositorio;
    }

    @Override
    public Diseno guardarDiseno(Diseno diseno) {
        return disenoRepositorio.save(diseno);
    }

    @Override
    public List<Diseno> listarDisenos() {
        return disenoRepositorio.findAll();
    }

    @Override
    public Optional<Diseno> buscarPorId(Long id) {
        return disenoRepositorio.findById(id);
    }

    @Override
    public void eliminarDiseno(Long id) {
        disenoRepositorio.deleteById(id);
    }
}
