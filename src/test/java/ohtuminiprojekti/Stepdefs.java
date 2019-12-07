package ohtuminiprojekti;

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
    //((ModifiedHtmlUnitDriver) driver).setJavascriptEnabled(true);
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
    clickLinkWithText("uusi nettivinkki");
    WebElement element = driver.findElement(By.name("addLinkDirectly"));
    element.click();
  }

  @Given("command to automatically get data by URL is selected")
  public void command_to_automatically_get_data_by_URL_is_selected() {
    driver.get(baseUrl);
    clickLinkWithText("uusi nettivinkki");
  }

  @When("url information {string} is entered")
  public void url_information_is_entered(String urlString) throws InterruptedException {
    WebElement element = driver.findElement(By.name("link"));
    element.sendKeys(urlString);
    element = driver.findElement(By.name("linkSubmit"));
    element.submit();
    Thread.sleep(500);
  }

  @When("fetched url information is accepted")
  public void fetched_url_information_is_accepted() {
    WebElement element = driver.findElement(By.name("linkSubmit"));
    element.click();
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

  @Then("button to mark entry as read is shown")
  public void button_to_mark_entry_as_read_is_shown() {
    pageHasContent("Merkitse luetuksi");
  }
 
  @When("button to mark entry read is pressed")
  public void button_to_mark_entry_read_is_pressed() {
    WebElement element = driver.findElement(By.name("markEntryAsRead"));
    element.click();
  }
  
  @Then("bookmark has changed status to read")
   public void bookmark_has_changed_status_to_read() {
    pageHasContent("Merkitse lukemattomaksi");
  }

  @When("button to mark entry unread is pressed")
  public void button_to_mark_entry_unread_is_pressed() {
    WebElement element = driver.findElement(By.name("markEntryAsUnread"));
    element.click();
  }
  
  @Then("bookmark has changed status to unread")
  public void bookmark_has_changed_status_to_unread() {
    pageHasContent("Merkitse luetuksi");
  }

  @When("command list unread books is selected")
  public void command_list_unread_books_is_selected() {
    driver.get(baseUrl);
    clickLinkWithText("Listaa lukemattomat lukuvinkit");   
  }
  
  @Then("bookmark {string} is listed")
  public void bookmark_is_listed(String bookmarkName) {
    pageHasContent(bookmarkName);
  }

  @Then("an error message with text {string} is displayed")
  public void an_error_message_with_text_is_displayed(String errorString) {
    pageHasContent(errorString);
  }
  
  @When("button to save a book is pressed")
  public void button_to_save_a_book_is_pressed() {
    WebElement element = driver.findElement(By.name("bookSubmit"));
    element.click();
  }
  
  @Then("an error message with text {string} is not displayed")
  public void an_error_message_with_text_is_not_displayed(String errorString) {
    pageDoesNotHaveContent(errorString);
  }

  
  @When("button to remove an entry is clicked")
  public void button_to_remove_an_entry_is_clicked() {
    WebElement element = driver.findElement(By.name("removeButton"));
    element.click();
  }
  
  @Then("an alert with text {string} is shown")
  public void an_alert_with_text_is_shown(String alertText) throws Throwable {
    // Potentially problematic with HtmlUnitDriver, does work on Firefox though 
    // Works with roughly 50% of times, disabled so far
    //int trials = 0;
    //while (trials++ < 5) {
    //  try {
    //    String returnedText = driver.switchTo().alert().getText();
    //    Assert.assertEquals(returnedText, alertText);
    //    break;
    //  } catch (Exception e) {
    //    System.out.println(e.getStackTrace());
    //  }
    //}
    //String returnedText = driver.switchTo().alert().getText();
    //Assert.assertEquals(returnedText, alertText);
  }

  @Then("bookmark {string} is no longer listed")
  public void bookmark_is_no_longer_listed(String bookmarkString) {
    driver.get(baseUrl);
    clickLinkWithText("Listaa lukuvinkit");
    pageDoesNotHaveContent(bookmarkString);
  }

  
  @Given("^book with title \"([^\"]*)\" and author \"([^\"]*)\" is saved$")
  public void books_saved(String title, String author) throws Throwable {
    driver.get(baseUrl);
    clickLinkWithText("uusi kirjavinkki");
    WebElement element = driver.findElement(By.name("addWithoutISBN"));
    element.click();
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
    clickLinkWithText("uusi kirjavinkki");
    WebElement element = driver.findElement(By.name("addWithoutISBN"));
    element.click();
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

  @Then("a message {string} is not displayed")
  public void a_message_is_not_displayed(String message) {
    pageDoesNotHaveContent(message);
  }

  @Then("a link to address {string} is shown in the listing")
  public void a_link_to_address_is_shown_in_the_listing(String address) {
    pageHasLinkToAddress(address);  
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

  private void pageHasLinkToAddress(String address) {
    Assert.assertTrue(driver.getPageSource().contains("href=\"" + address + "\""));
  }
  
  private void pageDoesNotHaveContent(String content) {
    Assert.assertTrue(!driver.getPageSource().contains(content));
  }
  
}
