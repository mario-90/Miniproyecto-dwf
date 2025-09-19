package ProyectoF2.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI proyectoF2OpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Proyecto F2")
                        .description("Documentación de la API")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio GitHub")
                        .url("https://github.com/mario-90/Miniproyecto-dwf"));
    }
}
