package ProyectoF2.controlador;

import ProyectoF2.modelo.Diseno;
import ProyectoF2.servicio.DisenoServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disenos")
public class DisenoController {

    private final DisenoServicio disenoServicio;

    public DisenoController(DisenoServicio disenoServicio) {
        this.disenoServicio = disenoServicio;
    }

    @GetMapping
    public ResponseEntity<List<Diseno>> listarDisenos() {
        return ResponseEntity.ok(disenoServicio.listarDisenos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diseno> obtenerDiseno(@PathVariable Long id) {
        return disenoServicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Diseno> crearDiseno(@Valid @RequestBody Diseno diseno) {
        return ResponseEntity.status(201).body(disenoServicio.guardarDiseno(diseno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diseno> actualizarDiseno(@PathVariable Long id, @RequestBody Diseno diseno) {
        return disenoServicio.buscarPorId(id)
                .map(d -> {
                    diseno.setId(id);
                    return ResponseEntity.ok(disenoServicio.guardarDiseno(diseno));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDiseno(@PathVariable Long id) {
        if (disenoServicio.buscarPorId(id).isPresent()) {
            disenoServicio.eliminarDiseno(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}