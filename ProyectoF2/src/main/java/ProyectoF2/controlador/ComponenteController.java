package ProyectoF2.controlador;

import ProyectoF2.modelo.Componente;
import ProyectoF2.servicio.ComponenteServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/componentes")
public class ComponenteController {

    private final ComponenteServicio componenteServicio;

    public ComponenteController(ComponenteServicio componenteServicio) {
        this.componenteServicio = componenteServicio;
    }

    @GetMapping
    public ResponseEntity<List<Componente>> listarComponentes() {
        return ResponseEntity.ok(componenteServicio.listarComponentes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Componente> obtenerComponente(@PathVariable Long id) {
        return componenteServicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Componente> crearComponente(@Valid @RequestBody Componente componente) {
        return ResponseEntity.status(201).body(componenteServicio.guardarComponente(componente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Componente> actualizarComponente(@PathVariable Long id, @Valid @RequestBody Componente componente) {
        return componenteServicio.buscarPorId(id)
                .map(c -> {
                    componente.setId(id);
                    return ResponseEntity.ok(componenteServicio.guardarComponente(componente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComponente(@PathVariable Long id) {
        if (componenteServicio.buscarPorId(id).isPresent()) {
            componenteServicio.eliminarComponente(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}