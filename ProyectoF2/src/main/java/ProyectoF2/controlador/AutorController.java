package ProyectoF2.controlador;

import ProyectoF2.modelo.Autor;
import ProyectoF2.servicio.AutorServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    private final AutorServicio autorServicio;

    public AutorController(AutorServicio autorServicio) {
        this.autorServicio = autorServicio;
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listarAutores() {
        return ResponseEntity.ok(autorServicio.listarAutores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerAutor(@PathVariable Long id) {
        return autorServicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Autor> crearAutor(@Valid @RequestBody Autor autor) {
        return ResponseEntity.status(201).body(autorServicio.guardarAutor(autor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizarAutor(@PathVariable Long id, @Valid @RequestBody Autor autor) {
        return autorServicio.buscarPorId(id)
                .map(a->{
                    autor.setId(id);
                    return ResponseEntity.ok(autorServicio.guardarAutor(autor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Autor> eliminarAutor(@PathVariable Long id) {
        if (autorServicio.buscarPorId(id).isPresent()) {
            autorServicio.eliminarAutor(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
