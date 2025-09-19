package ProyectoF2.controlador;

import ProyectoF2.modelo.Componente;
import ProyectoF2.servicio.ComponenteServicio;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ComponenteController.class)
class ComponenteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComponenteServicio componenteServicio;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarComponentes() throws Exception {
        Componente componente = new Componente();
        componente.setNombre("Diodo");
        componente.setDescripcion("Componente semiconductor");
        componente.setUsos("Rectificación");

        when(componenteServicio.listarComponentes()).thenReturn(Collections.singletonList(componente));

        mockMvc.perform(get("/api/componentes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Diodo"));
    }

    @Test
    void testCrearComponente() throws Exception {
        Componente componente = new Componente();
        componente.setNombre("Transistor");
        componente.setDescripcion("Amplificador de señal");
        componente.setUsos("Conmutación");

        when(componenteServicio.guardarComponente(componente)).thenReturn(componente);

        mockMvc.perform(post("/api/componentes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(componente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Transistor"));
    }

    @Test
    void testObtenerComponentePorId() throws Exception {
        Componente componente = new Componente();
        componente.setNombre("Inductor");
        componente.setDescripcion("Almacena energía en campo magnético");
        componente.setUsos("Filtros y osciladores");

        when(componenteServicio.buscarPorId(1L)).thenReturn(Optional.of(componente));

        mockMvc.perform(get("/api/componentes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Inductor"));
    }
}
