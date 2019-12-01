package ohtuminiprojekti.services;

import java.util.ArrayList;
import java.util.List;
import ohtuminiprojekti.dao.BookRepository;
import ohtuminiprojekti.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
  Book b3;

  @Before
  public void setUp() {
    bookList = new ArrayList<>();
    b1 = Mockito.mock(Book.class);
    b1.setTitle("b1title");
    b1.setAuthor("b1author");
    b2 = Mockito.mock(Book.class);
    b2.setTitle("b2title");
    b2.setAuthor("b2author");

    bookList.add(b1);
    bookList.add(b2);
  }

  @Test
  public void newBookIsSaved() {
    String title = "Test Book";
    String author = "Dest Tester";
    service.newBook(title, author, null);
    Mockito.verify(repo, Mockito.times(1)).save(Mockito.any(Book.class));
  }

  @Test
  public void bookCanBeRetrieved() {
    service.getById(1L);
    Mockito.verify(repo, Mockito.times(1)).getOne(1L);
  }

  @Test
  public void existingBookIsFound() {
    service.existingBook("b1title", "b1author");
    Mockito.verify(repo, Mockito.times(1)).findByTitleAndAuthor("b1title", "b1author");
  }

  @Test
  public void existingBookWithSameTitleSearchesDatabase() {
    service.existingBook("b2title", "b2author");
    Mockito.verify(repo, Mockito.times(1)).findByTitleAndAuthor("b2title", "b2author");
  }

}
