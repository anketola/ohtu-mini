package ohtuminiprojekti.controllers;

import ohtuminiprojekti.Isbn;
import ohtuminiprojekti.Utils;
import ohtuminiprojekti.domain.Book;
import ohtuminiprojekti.services.BookService;
import ohtuminiprojekti.services.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {

  @Autowired
  private BookService bookService;
  @Autowired
  private BookmarkService bookmarkService;

  @PostMapping("/book/create")
  public String createBook(@RequestParam String title, @RequestParam String author, @RequestParam String comment) {
    if (!bookService.existingBook(title, author)) {
      bookService.newBook(title, author, comment);
      return "redirect:/bookmarks/list";
    } else {
      return "redirect:/book/create/none?error";
    }
  }

  @GetMapping("/book/edit")
  public String editBook(Model model, @RequestParam long id, @RequestParam String url) {
    model.addAttribute("book", bookService.getById(id));
    model.addAttribute("url", url);
    return "editbook";
  }
  
  @GetMapping("/book/create/")
  public String newBookRedirect() {
    return "redirect:/book/create/none";
  }

  @GetMapping("/book/create/{isbn}")
  public String newBook(Model model, @PathVariable String isbn) {
    Isbn isbnEntity;
    if(isbn.equals("none")) {
        isbnEntity = new Isbn();
    } else {
        isbnEntity = new Isbn(isbn);
    }
    model.addAttribute("title", isbnEntity.getTitle());
    model.addAttribute("author", isbnEntity.getAuthors());
    return "newbook";
  }
  
  @GetMapping("/book/query")
  public String askBook() {
    return "bookquery";
  }
  
  @PostMapping("/book/query")
  public String askLink(@RequestParam String isbn) {
      Isbn isbnEntity = new Isbn(isbn);
      if(!isbnEntity.isValid()) {
          return "redirect:/book/query?error";
      }
      return "redirect:/book/create/" + isbn;
  }

  @PostMapping("/book/edit")
  public String edit(RedirectAttributes redirectAttributes, @RequestParam long id, @RequestParam String url, @RequestParam String title, @RequestParam String author, @RequestParam String comment) {
    Book book = bookService.getById(id);
    if ((book.getAuthor().equals(author) && book.getTitle().equals(title)) || !bookService.existingBook(title, author)) {
      bookService.edit(id, title, author, comment);
      bookmarkService.setName(id, title);
      return Utils.redirectToSameListing(url);
    }
    redirectAttributes.addAttribute("id", id);
    redirectAttributes.addAttribute("url", url);
    return "redirect:/book/edit?error";
  }
}
