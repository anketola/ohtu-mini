package ohtuminiprojekti;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.ClassRule;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
public class RunCukesTest {
  @ClassRule
  public static ServerRule server = new ServerRule(8080);
}