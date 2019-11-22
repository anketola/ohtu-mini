package ohtuminiprojekti;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.File;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.Assert.assertTrue;

public class Stepdefs {

  WebDriver driver;
  String baseUrl = "http://localhost:8080";

  public Stepdefs() {
    File file;
    if (System.getProperty("os.name").matches("Mac OS X")) {
      file = new File("lib/macgeckodriver");
    } else {
      file = new File("lib/geckodriver");
    }
    String absolutePath = file.getAbsolutePath();
    System.setProperty("webdriver.gecko.driver", absolutePath);

    this.driver = new HtmlUnitDriver();

  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Given("^user is at the main page$")
  public void user_is_at_the_main_page() throws Throwable {
    driver.get("http://localhost:" + 8080 + "/");
    Thread.sleep(1000);
  }

  @When("nothing is done")
  public void nothing_is_done() {
    // do nothing - temp to for a dummy test
  }

  @When("^a link is clicked$")
  public void link_is_clicked() throws Throwable {
    Thread.sleep(1000);
    clickLinkWithText("linkki");
    Thread.sleep(1000);
  }

  @Then("^\"([^\"]*)\" is shown$")
  public void is_shown(String arg1) throws Throwable {
    assertTrue(driver.findElement(By.tagName("body"))
        .getText().contains(arg1));
  }

  // alla oleva koodi on kommentoitu koska kirjoitushetkellä se tallentaa "varsinaiseen" tietokantaan
  // ei siis testikantaan

@Given("^command save book is selected$")
  public void command_save_book_is_selected() throws Throwable {
    driver.get(baseUrl);
    clickLinkWithText("Lisää kirja");
  }

  @When("^title \"([^\"]*)\" and author \"([^\"]*)\" are entered$")
  public void title_and_author_are_entered(String title, String author) throws Throwable {
    enterBookInformation(title, author);
  }

  @Then("^book is saved$")
  public void book_is_saved() throws Throwable {
    pageHasContent("Kirjalista");
    pageHasContent("Spaghetti Code");
  }


  private void clickLinkWithText(String text) {
    int trials = 0;
    while (trials++ < 5) {
      try {
        WebElement element = driver.findElement(By.linkText(text));
        element.click();
        break;
      } catch (Exception e) {
        System.out.println(e.getStackTrace());
      }
    }
  }

  private void enterBookInformation(String title, String author) {
    WebElement element = driver.findElement(By.name("title"));
    element.sendKeys(title);
    element = driver.findElement(By.name("author"));
    element.sendKeys(author);
    element = driver.findElement(By.name("bookSubmit"));
    element.submit();
  }

  private void pageHasContent(String content) {
    assertTrue(driver.getPageSource().contains(content));
  }

}
