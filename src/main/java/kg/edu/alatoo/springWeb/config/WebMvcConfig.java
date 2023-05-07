package kg.edu.alatoo.springWeb.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                        "/css/**",
                        "/resources/**",
                        "/js/**",
                        "/images/**",
                        "/api/**",
                        "/font-awesome/**"
                )
                .addResourceLocations(
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/images/",
                        "classpath:/static/api/",
                        "classpath:/resources/",
                        "classpath:/static/font-awesome/");
    }
}
