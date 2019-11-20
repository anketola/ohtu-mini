
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
    bookRepository.save(newBook);
  }

  public List<Book> allBooks() {
    return bookRepository.findAll();
  }

}
