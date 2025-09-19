package ProyectoF2.servicio;

import ProyectoF2.modelo.Componente;
import ProyectoF2.repositorio.ComponenteRepositorio;
import ProyectoF2.servicio.impl.ComponenteServicioImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class ComponenteServicioTest {

    @Mock
    private ComponenteRepositorio componenteRepositorio;

    @InjectMocks
    private ComponenteServicioImpl componenteServicio;

    public ComponenteServicioTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarComponente() {
        Componente componente = new Componente();
        componente.setNombre("Resistor");
        componente.setDescripcion("Componente pasivo");
        componente.setUsos("Control de corriente");

        when(componenteRepositorio.save(componente)).thenReturn(componente);

        Componente guardado = componenteServicio.guardarComponente(componente);
        assertThat(guardado.getNombre()).isEqualTo("Resistor");
        verify(componenteRepositorio, times(1)).save(componente);
    }

    @Test
    void testBuscarPorId() {
        Componente componente = new Componente();
        componente.setNombre("Capacitor");
        componente.setDescripcion("Almacena carga");
        componente.setUsos("Filtrado de señales");

        when(componenteRepositorio.findById(1L)).thenReturn(Optional.of(componente));

        Optional<Componente> encontrado = componenteServicio.buscarPorId(1L);
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getNombre()).isEqualTo("Capacitor");
    }
}
