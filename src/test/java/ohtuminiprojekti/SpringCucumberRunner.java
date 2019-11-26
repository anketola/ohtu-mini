package ohtuminiprojekti;

import io.cucumber.junit.Cucumber;
import java.io.IOException;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.ActiveProfiles;

public class SpringCucumberRunner extends Cucumber {
        
  public SpringCucumberRunner(Class spClass) throws InitializationError, IOException {
    super(spClass);
    ActiveProfiles ap = (ActiveProfiles) spClass.getAnnotation(ActiveProfiles.class);
    if (ap != null) {
      System.setProperty("spring.profiles.active", String.join(",", ap.value()));
    }
  }
}
    

