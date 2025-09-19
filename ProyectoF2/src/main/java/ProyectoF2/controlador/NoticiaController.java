package ProyectoF2.controlador;

import ProyectoF2.modelo.Noticia;
import ProyectoF2.repositorio.NoticiaRepositorio;
import ProyectoF2.servicio.NoticiaServicio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {

    private final NoticiaServicio noticiaServicio;

    public NoticiaController(NoticiaServicio noticiaServicio){
        this.noticiaServicio = noticiaServicio;
    }

    @GetMapping
    public ResponseEntity<List<Noticia>> obtenerNoticias(){
        return ResponseEntity.ok(noticiaServicio.listarNoticias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Noticia> obtenerNoticia(@PathVariable Long id){
        return noticiaServicio.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<List<Noticia>> listarNoticiasPorAutor(@PathVariable Long autorId) {
        return ResponseEntity.ok(noticiaServicio.buscarPorAutor(autorId));
    }

    @PostMapping
    public ResponseEntity<Noticia> crearNoticia(@Valid @RequestBody Noticia noticia) {
        return ResponseEntity.status(201).body(noticiaServicio.guardarNoticia(noticia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Noticia> actualizarNoticia(@PathVariable Long id, @Valid @RequestBody Noticia noticia) {
        return noticiaServicio.buscarPorId(id)
                .map(n -> {
                    noticia.setId(id);
                    return ResponseEntity.ok(noticiaServicio.guardarNoticia(noticia));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarNoticia(@PathVariable Long id) {
        if (noticiaServicio.buscarPorId(id).isPresent()) {
            noticiaServicio.eliminarNoticia(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
