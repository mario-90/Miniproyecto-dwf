package ProyectoF2.controlador;

import ProyectoF2.modelo.Autor;
import ProyectoF2.modelo.Noticia;
import ProyectoF2.servicio.NoticiaServicio;
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

@WebMvcTest(NoticiaController.class)
class NoticiaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoticiaServicio noticiaServicio;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarNoticias() throws Exception {
        Autor autor = new Autor();
        autor.setId(1L);
        autor.setNombre("Admin");
        autor.setCorreo("Admin@correo.com");

        Noticia noticia = new Noticia();
        noticia.setTitulo("Microchips 2025");
        noticia.setCuerpo("Avances en microchips...");
        noticia.setAutor(autor);

        when(noticiaServicio.listarNoticias()).thenReturn(Collections.singletonList(noticia));

        mockMvc.perform(get("/api/noticias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Microchips 2025"));
    }

    @Test
    void testCrearNoticia() throws Exception {
        Autor autor = new Autor();
        autor.setId(2L);
        autor.setNombre("Beatriz");
        autor.setCorreo("beatriz@correo.com");

        Noticia noticia = new Noticia();
        noticia.setTitulo("Nueva Tecnología");
        noticia.setCuerpo("Innovaciones recientes...");
        noticia.setAutor(autor);

        when(noticiaServicio.guardarNoticia(noticia)).thenReturn(noticia);

        mockMvc.perform(post("/api/noticias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noticia)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Nueva Tecnología"));
    }

    @Test
    void testObtenerNoticiaPorId() throws Exception {
        Autor autor = new Autor();
        autor.setId(1L);
        autor.setNombre("Admin");
        autor.setCorreo("admin@correo.com");

        Noticia noticia = new Noticia();
        noticia.setTitulo("Noticias Microelectrónica");
        noticia.setCuerpo("Contenido detallado...");
        noticia.setAutor(autor);

        when(noticiaServicio.buscarPorId(1L)).thenReturn(Optional.of(noticia));

        mockMvc.perform(get("/api/noticias/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Noticias Microelectrónica"));
    }
}