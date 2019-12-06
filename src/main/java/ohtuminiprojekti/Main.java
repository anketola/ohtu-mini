package ohtuminiprojekti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class Main extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

  @Configuration
  public class WebConfig implements WebMvcConfigurer {      
        
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/**")
      .addResourceLocations("classpath:/static/")
        .setCachePeriod(0);
    }
  }

}
