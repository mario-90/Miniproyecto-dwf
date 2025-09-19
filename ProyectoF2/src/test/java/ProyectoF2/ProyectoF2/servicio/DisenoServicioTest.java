package ProyectoF2.servicio;

import ProyectoF2.modelo.Diseno;
import ProyectoF2.repositorio.DisenoRepositorio;
import ProyectoF2.servicio.impl.DisenoServicioImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class DisenoServicioTest {

    @Mock
    private DisenoRepositorio disenoRepositorio;

    @InjectMocks
    private DisenoServicioImpl disenoServicio;

    public DisenoServicioTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarDiseno() {
        Diseno diseno = new Diseno();
        diseno.setNombre("Circuito básico");
        diseno.setDescripcion("Ejemplo de diseño sencillo");

        when(disenoRepositorio.save(diseno)).thenReturn(diseno);

        Diseno guardado = disenoServicio.guardarDiseno(diseno);
        assertThat(guardado.getNombre()).isEqualTo("Circuito básico");
        verify(disenoRepositorio, times(1)).save(diseno);
    }

    @Test
    void testBuscarPorId() {
        Diseno diseno = new Diseno();
        diseno.setNombre("Circuito avanzado");
        diseno.setDescripcion("Diseño complejo");

        when(disenoRepositorio.findById(1L)).thenReturn(Optional.of(diseno));

        Optional<Diseno> encontrado = disenoServicio.buscarPorId(1L);
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo("Circuito avanzado");
    }
}