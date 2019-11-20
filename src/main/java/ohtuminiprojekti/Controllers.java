package ohtuminiprojekti;

import ohtuminiprojekti.dao.BookRepository;
import ohtuminiprojekti.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controllers {

    @Autowired
    BookService bookService;
    
    @PostMapping("/books/create")
    public String createBook(@RequestParam String title, @RequestParam String author) {
        bookService.newBook(title, author);
    return "redirect:/";
    }
    
    @GetMapping("/books/list")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.allBooks());
    return "listbooks";
    }
    
    @GetMapping("/")
    public String indexRoot() {
        return "index";
    }
}