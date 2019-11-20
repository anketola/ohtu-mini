package ohtuminiprojekti;

import ohtuminiprojekti.dao.BookRepository;
import ohtuminiprojekti.domain.Book;
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
    private BookRepository bookRepository;
    
    @PostMapping("/books/create")
    public String createBook(@RequestParam String title, @RequestParam String author) {
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        bookRepository.save(newBook);
    return "redirect:/";
    }
    
    @GetMapping("/books/list")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
    return "listbooks";
    }
    
    @GetMapping("/")
    @ResponseBody
    public String indexRoot() {
        return "yay";
    }
}