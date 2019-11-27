package ohtuminiprojekti;

import ohtuminiprojekti.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Controllers {

    @Autowired
    BookService bookService;

    @PostMapping("/books/create")
    public String createBook(@RequestParam String title, @RequestParam String author) {
        if (!this.bookService.existingBook(title)) {
            this.bookService.newBook(title, author);
            return "redirect:/books/list";
        }
        return "redirect:/books/create?error";
    }

    @GetMapping("/books/list")
    public String listBooks(Model model) {
        model.addAttribute("books", this.bookService.allBooks());
        return "listbooks";
    }

    @GetMapping("/books/edit")
    public String editBook(Model model, @RequestParam long id, @RequestParam String url) {
        model.addAttribute("book", bookService.getById(id));
        model.addAttribute("url", url);
        return "editbook";
    }

    @GetMapping("/books/list/read")
    public String listReadBooks(Model model) {
        model.addAttribute("books", this.bookService.readBooks());
        return "listbooks";
    }

    @GetMapping("/books/list/unread")
    public String listUnreadBooks(Model model) {
        model.addAttribute("books", this.bookService.unreadBooks());
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

    @PostMapping("/edit")
    public String edit(RedirectAttributes redirectAttributes, @RequestParam long id, @RequestParam String url, @RequestParam String title, @RequestParam String author) {
        if (!this.bookService.existingOtherBookWithSameTitle(id, title)) {
            this.bookService.edit(id, title, author);
            return redirectToSameListing(url);
        }
        redirectAttributes.addAttribute("id", id);
        redirectAttributes.addAttribute("url", url);
        return "redirect:/books/edit?error";
    }

    @PostMapping("/delete")
    public String deleteBook(@RequestParam long id, @RequestParam String url) {
        this.bookService.deleteBook(id);
        return redirectToSameListing(url);
    }

    @PostMapping("/mark")
    public String markBookAsRead(@RequestParam long id, @RequestParam String url) {
        bookService.markBookAsRead(id);
        return redirectToSameListing(url);
    }

    @PostMapping("/unmark")
    public String markBookAsUnread(@RequestParam long id, @RequestParam String url) {
        bookService.markBookAsUnread(id);
        return redirectToSameListing(url);
    }

    private String redirectToSameListing(String url) {
        if (url.contains("unread")) {
            return "redirect:/books/list/unread";
        } else if (url.contains("read")) {
            return "redirect:/books/list/read";
        }
        return "redirect:/books/list";
    }
}
