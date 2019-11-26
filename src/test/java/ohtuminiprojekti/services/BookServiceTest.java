package ohtuminiprojekti.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import ohtuminiprojekti.dao.BookRepository;
import ohtuminiprojekti.domain.Book;
import org.junit.Before;
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

  List<Book> bookList;
  Book b1;
  Book b2;

  @Before
  public void setUp() {
    bookList = new ArrayList<>();
    b1 = mock(Book.class);
    b1.setTitle("b1title");
    b1.setAuthor("b1author");
    b2 = mock(Book.class);
    b2.setTitle("b2title");
    b2.setAuthor("b2author");

    bookList.add(b1);
    bookList.add(b2);
  }

  @Test
  public void newBookIsSaved() {
    String title = "Test Book";
    String author = "Dest Tester";
    service.newBook(title, author);
    verify(repo, times(1)).save(any(Book.class));
  }

  @Test
  public void listOfAllBooksIsReturned() {
    when(service.allBooks()).thenReturn(bookList);
    List<Book> result = service.allBooks();
    verify(repo, times(1)).findAll();
    assertEquals(2, service.allBooks().size());
  }

  @Test
  public void bookCanBeDeleted() {
    when(b1.getId()).thenReturn((long) 1);
    long idToDelete = b1.getId();
    service.deleteBook(idToDelete);
    verify(repo, times(1)).deleteById(eq(1L));
  }

  @Test
  public void existingBookIsFound() {
    service.existingBook("b1title");
    verify(repo, times(1)).findByTitle("b1title");
  }

}
