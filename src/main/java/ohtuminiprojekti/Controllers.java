package ohtuminiprojekti;

import ohtuminiprojekti.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Controllers {

  @Autowired
  BookService bookService;

  @PostMapping("/books/create")
  public String createBook(@RequestParam String title, @RequestParam String author) {
    this.bookService.newBook(title, author);
    return "redirect:/books/list";
  }

  @GetMapping("/books/list")
  public String listBooks(Model model) {
    model.addAttribute("books", this.bookService.allBooks());
    return "listbooks";
  }

  @GetMapping("/books/create")
  public String newBook() {
    return "newbook";
  }

  @GetMapping("/")
  public String indexRoot() {
    return "index";
  }
}
