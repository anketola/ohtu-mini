package ohtuminiprojekti.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import ohtuminiprojekti.dao.BookRepository;
import ohtuminiprojekti.domain.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {

  @InjectMocks
  private BookService service;

  @Mock
  BookRepository repo;

  @Test
  public void newBookIsSaved() {
    Book book = mock(Book.class);
    String title = "Test Book";
    String author = "Dest Tester";
    book.setTitle(title);
    book.setTitle(author);
    service.newBook(title, author);
    verify(repo, times(1)).save(any(Book.class));
  }

  @Test
  public void listOfAllBooksIsReturned() {
    Book b1 = new Book();
    b1.setTitle("b1title");
    b1.setAuthor("b1author");
    Book b2 = new Book();
    b2.setTitle("b2title");
    b2.setAuthor("b2author");

    List<Book> bl = new ArrayList<>();
    bl.add(b1);
    bl.add(b2);

    when(service.allBooks()).thenReturn(bl);

    List<Book> result = service.allBooks();
    verify(repo, times(1)).findAll();
    assertEquals(2, service.allBooks().size());
  }

}
