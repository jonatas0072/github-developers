package br.com.developers.config;

import java.util.Arrays;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import br.com.developers.domain.model.Role;
import br.com.developers.domain.model.RoleName;
import br.com.developers.domain.repository.RoleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class ApplicationConfig {

  private RoleRepository roleRepository;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }


  @Bean
  public void configVelocity() {
    Velocity.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    Velocity.setProperty("classpath.resource.loader.class",
        ClasspathResourceLoader.class.getName());
  }

  @Bean
  public void createRoleWhenInitializing() {
    Arrays.asList(RoleName.values()).forEach(roleName -> {
      if (!this.roleRepository.findByName(roleName).isPresent()) {
        this.roleRepository.saveAndFlush(Role.newBuilder().name(roleName).build());
      }
    });
  }

}
