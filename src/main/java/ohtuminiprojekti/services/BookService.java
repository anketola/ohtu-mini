package ohtuminiprojekti.services;

import java.util.List;
import ohtuminiprojekti.dao.BookRepository;
import ohtuminiprojekti.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void newBook(String titleString, String authorString) {
        Book newBook = new Book();
        newBook.setTitle(titleString);
        newBook.setAuthor(authorString);
        newBook.setIsRead(0);
        bookRepository.save(newBook);
    }

    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    public Book getById(long id) {
        return bookRepository.getOne(id);
    }

    public List<Book> readBooks() {
        return bookRepository.findByisRead(1);
    }

    public List<Book> unreadBooks() {
        return bookRepository.findByisRead(0);
    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    public void markBookAsRead(long id) {
        Book toBeMarked = bookRepository.getOne(id);
        toBeMarked.setIsRead(1);
        bookRepository.save(toBeMarked);
    }

    public void markBookAsUnread(long id) {
        Book toBeMarked = bookRepository.getOne(id);
        toBeMarked.setIsRead(0);
        bookRepository.save(toBeMarked);
    }

    public boolean existingBook(String title) {
        Book existingBook = bookRepository.findByTitle(title);
        if (existingBook != null) {
            return true;
        }
        return false;
    }

    public void edit(long id, String title, String author) {
        Book book = bookRepository.getOne(id);
        book.setTitle(title);
        book.setAuthor(author);
        bookRepository.save(book);
    }

    public boolean existingOtherBookWithSameTitle(long id, String title) {
        Book existingBook = bookRepository.findByTitle(title);
        if (existingBook != null) {
            if (existingBook.getId() != id) {
                return true;
            }
        }
        return false;
    }
}
