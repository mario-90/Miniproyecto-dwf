package ProyectoF2.servicio;

import ProyectoF2.modelo.Autor;
import ProyectoF2.modelo.Noticia;
import ProyectoF2.repositorio.NoticiaRepositorio;
import ProyectoF2.servicio.impl.NoticiaServicioImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class NoticiaServicioTest {

    @Mock
    private NoticiaRepositorio noticiaRepositorio;

    @InjectMocks
    private NoticiaServicioImpl noticiaServicio;

    public NoticiaServicioTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarNoticia() {
        Autor autor = new Autor();
        autor.setId(1L);
        autor.setNombre("Admin");
        autor.setCorreo("Admin@correo.com");

        Noticia noticia = new Noticia();
        noticia.setTitulo("Nueva Ley de Semiconductores");
        noticia.setCuerpo("Detalles de la ley...");
        noticia.setAutor(autor);

        when(noticiaRepositorio.save(noticia)).thenReturn(noticia);

        Noticia guardada = noticiaServicio.guardarNoticia(noticia);
        assertThat(guardada.getTitulo()).isEqualTo("Nueva Ley de Semiconductores");
        verify(noticiaRepositorio, times(1)).save(noticia);
    }

    @Test
    void testBuscarPorId() {
        Autor autor = new Autor();
        autor.setId(2L);
        autor.setNombre("Beatriz");
        autor.setCorreo("beatriz@correo.com");

        Noticia noticia = new Noticia();
        noticia.setTitulo("Noticias Microelectrónica");
        noticia.setCuerpo("Contenido interesante...");
        noticia.setAutor(autor);

        when(noticiaRepositorio.findById(1L)).thenReturn(Optional.of(noticia));

        Optional<Noticia> encontrada = noticiaServicio.buscarPorId(1L);
        assertThat(encontrada).isPresent();
        assertThat(encontrada.get().getTitulo()).isEqualTo("Noticias Microelectrónica");
    }
}
