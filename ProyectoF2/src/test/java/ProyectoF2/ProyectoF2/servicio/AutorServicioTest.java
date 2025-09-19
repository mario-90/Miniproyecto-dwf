package ProyectoF2.servicio;

import ProyectoF2.modelo.Autor;
import ProyectoF2.repositorio.AutorRepositorio;
import ProyectoF2.servicio.impl.AutorServicioImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AutorServicioTest {

    @Mock
    private AutorRepositorio autorRepositorio;

    @InjectMocks
    private AutorServicioImpl autorServicio;

    public AutorServicioTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarAutor() {
        Autor autor = new Autor();
        autor.setNombre("Beatriz");
        autor.setCorreo("beatriz@mail.com");

        when(autorRepositorio.save(autor)).thenReturn(autor);

        Autor guardado = autorServicio.guardarAutor(autor);
        assertThat(guardado.getNombre()).isEqualTo("Beatriz");
        verify(autorRepositorio, times(1)).save(autor);
    }

    @Test
    void testBuscarPorId() {
        Autor autor = new Autor();
        autor.setNombre("Fatima");
        autor.setCorreo("fatima@mail.com");

        when(autorRepositorio.findById(1L)).thenReturn(Optional.of(autor));

        Optional<Autor> encontrado = autorServicio.buscarPorId(1L);
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo("Fatima");
    }
}
