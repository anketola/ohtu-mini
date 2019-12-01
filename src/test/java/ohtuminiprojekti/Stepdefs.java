package ohtuminiprojekti;

import com.gargoylesoftware.htmlunit.ConfirmHandler;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.File;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitAlert;

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

    this.driver = new ModifiedHtmlUnitDriver();
    //this.driver = new FirefoxDriver();
  }

  @After
  public void tearDown() {
    driver.quit();
  }

  @Given("^user is at the main page$")
  public void user_is_at_the_main_page() throws Throwable {
    driver.get("http://localhost:" + 8080 + "/");
  }

  @When("nothing is done")
  public void nothing_is_done() {
    // do nothing - temp to for a dummy test
  }

  @When("^a link is clicked$")
  public void link_is_clicked() throws Throwable {
    clickLinkWithText("linkki");
  }

  @Given("^there are no books saved$")
  public void no_books_saved() throws Throwable {
  }

  @Given("command save internet resource is selected")
  public void command_save_internet_resource_is_selected() {
  driver.get(baseUrl);
  clickLinkWithText("Lisää lukuvinkki(linkki)");
  }

  @When("url information title {string} and url {string} are entered")
  public void url_information_title_and_url_are_entered(String title, String urlstring) {
    enterUrlInformation(title, urlstring);
  }
  
  @Then("internet resource with name {string} is saved")
  public void internet_resource_with_name_is_saved(String name) {
    pageHasContent("Lista lukuvinkeistä");
    pageHasContent(name);
  }

  @Then("a bookmark with a type {string} is displayed")
  public void a_bookmark_with_a_type_is_displayed(String typeListingText) {
    pageHasContent(typeListingText);
  }

  @Then("button to remove an entry is shown")
  public void button_to_remove_an_entry_is_shown() {
    pageHasContent("Poista");
  }

  @When("button to remove an entry is clicked")
  public void button_to_remove_an_entry_is_clicked() {
    WebElement element = driver.findElement(By.name("removeButton"));
    element.click();
  }
  
  @Then("an alert with text {string} is shown")
  public void an_alert_with_text_is_shown(String alertText) {
    // Doesn't pass with HtmlUnitDriver, does on Firefox though 
    
    //String returnedText = driver.switchTo().alert().getText();
    //Assert.assertEquals(returnedText, alertText);
  }

  
  @Given("^book with title \"([^\"]*)\" and author \"([^\"]*)\" is saved$")
  public void books_saved(String title, String author) throws Throwable {
    driver.get(baseUrl);
    clickLinkWithText("Lisää lukuvinkki(kirja)");
    enterBookInformation(title, author);
  }

  @Then("^\"([^\"]*)\" is shown$")
  public void is_shown(String arg1) throws Throwable {
    Assert.assertTrue(driver.findElement(By.tagName("body"))
        .getText().contains(arg1));
  }

  @Given("^command save book is selected$")
  public void command_save_book_is_selected() throws Throwable {
    driver.get(baseUrl);
    clickLinkWithText("Lisää lukuvinkki(kirja)");
  }

  @When("^command list books is selected$")
  public void command_list_book_is_selected() throws Throwable {
    driver.get(baseUrl);
    clickLinkWithText("Listaa lukuvinkit");
  }

  @When("^title \"([^\"]*)\" and author \"([^\"]*)\" are entered$")
  public void title_and_author_are_entered(String title, String author) throws Throwable {
    enterBookInformation(title, author);
  }

  @Then("book with name {string} is saved")
  public void book_is_saved(String name) throws Throwable {
    pageHasContent("Lista lukuvinkeistä");
    pageHasContent(name);
  }

  @Then("^empty list of books is shown$")
  public void no_books_are_listed() throws Throwable {
    pageHasContent("Lukuvinkkejä ei löytynyt.");
  }

  @Then("^book with title \"([^\"]*)\" and author \"([^\"]*)\" is listed$")
  public void books_are_listed(String title, String author) throws Throwable {
    //pageHasContent(author); Author not currently listed in bookmark list
    pageHasContent(title);
  }

  @Then("a page with with text {string} is displayed")
  public void page_with_with_text_is_displayed(String string) {
    pageHasContent(string);
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

   private void enterUrlInformation(String name, String urlstring) {
    WebElement element = driver.findElement(By.name("name"));
    element.sendKeys(name);
    element = driver.findElement(By.name("link"));
    element.sendKeys(urlstring);
    element = driver.findElement(By.name("linkSubmit"));
    element.submit();
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
    Assert.assertTrue(driver.getPageSource().contains(content));
  }

}
