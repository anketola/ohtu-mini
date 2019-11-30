package ohtuminiprojekti.services;

import ohtuminiprojekti.dao.BookRepository;
import ohtuminiprojekti.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  public Book newBook(String titleString, String authorString, String comment) {
    Book book = new Book();
    book.setName(titleString);
    book.setTitle(titleString);
    book.setAuthor(authorString);
    book.setComment(comment);
    book.setHasBeenRead(false);
    bookRepository.save(book);
    return book;
  }

  public Book getById(long id) {
    return bookRepository.getOne(id);
  }

  public boolean existingBook(String title) {
    Book existingBook = bookRepository.findByTitle(title);
    return existingBook != null;
  }

  public void edit(long id, String title, String author, String comment) {
    Book book = bookRepository.getOne(id);
    book.setName(title);
    book.setTitle(title);
    book.setAuthor(author);
    book.setComment(comment);
    bookRepository.save(book);
  }
}
