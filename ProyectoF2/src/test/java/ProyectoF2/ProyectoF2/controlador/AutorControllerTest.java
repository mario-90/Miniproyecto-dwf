package ProyectoF2.controlador;

import ProyectoF2.modelo.Autor;
import ProyectoF2.servicio.AutorServicio;
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

@WebMvcTest(AutorController.class)
class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AutorServicio autorServicio;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarAutores() throws Exception {
        Autor autor = new Autor();
        autor.setNombre("Admin");
        autor.setCorreo("admin@mail.com");
        when(autorServicio.listarAutores()).thenReturn(Collections.singletonList(autor));

        mockMvc.perform(get("/api/autores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Admin"));
    }

    @Test
    void testCrearAutor() throws Exception {
        Autor autor = new Autor();
        autor.setNombre("Nuevo Autor");
        autor.setCorreo("nuevo@mail.com");

        when(autorServicio.guardarAutor(autor)).thenReturn(autor);

        mockMvc.perform(post("/api/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(autor)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Nuevo Autor"));
    }

    @Test
    void testObtenerAutorPorId() throws Exception {
        Autor autor = new Autor();
        autor.setNombre("Beatriz");
        autor.setCorreo("beatriz@mail.com");

        when(autorServicio.buscarPorId(1L)).thenReturn(Optional.of(autor));

        mockMvc.perform(get("/api/autores/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Beatriz"));
    }
}