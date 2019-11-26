package ohtuminiprojekti;

import io.cucumber.junit.CucumberOptions;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;

@RunWith(SpringCucumberRunner.class)
@CucumberOptions(plugin = {"pretty"})
@ActiveProfiles("test")
public class RunCukesTest {
  @ClassRule
  public static ServerRule server = new ServerRule(8080);
}
