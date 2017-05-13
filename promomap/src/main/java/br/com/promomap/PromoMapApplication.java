package br.com.promomap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author <a href="mailto:leandro.lucas_@hotmail.com">Leandro Lucas Santos</a>
 */

@SpringBootApplication
public class PromoMapApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PromoMapApplication.class, args);
	}
	
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://192.168.25.15:4200")
                .allowedHeaders("token", "content-type")
//                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Headers")
                .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
	
}