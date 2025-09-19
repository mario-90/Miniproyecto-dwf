package ProyectoF2.controlador;

import ProyectoF2.modelo.Diseno;
import ProyectoF2.servicio.DisenoServicio;
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

@WebMvcTest(DisenoController.class)
class DisenoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DisenoServicio disenoServicio;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarDisenos() throws Exception {
        Diseno diseno = new Diseno();
        diseno.setNombre("Circuito 1");
        diseno.setDescripcion("Diseño sencillo");

        when(disenoServicio.listarDisenos()).thenReturn(Collections.singletonList(diseno));

        mockMvc.perform(get("/api/disenos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Circuito 1"));
    }

    @Test
    void testCrearDiseno() throws Exception {
        Diseno diseno = new Diseno();
        diseno.setNombre("Circuito 2");
        diseno.setDescripcion("Diseño avanzado");

        when(disenoServicio.guardarDiseno(diseno)).thenReturn(diseno);

        mockMvc.perform(post("/api/disenos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(diseno)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Circuito 2"));
    }

    @Test
    void testObtenerDisenoPorId() throws Exception {
        Diseno diseno = new Diseno();
        diseno.setNombre("Circuito 3");
        diseno.setDescripcion("Diseño complejo");

        when(disenoServicio.buscarPorId(1L)).thenReturn(Optional.of(diseno));

        mockMvc.perform(get("/api/disenos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Circuito 3"));
    }
}