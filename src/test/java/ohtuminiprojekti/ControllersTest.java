package ohtuminiprojekti;

import ohtuminiprojekti.dao.BookRepository;
import ohtuminiprojekti.domain.Book;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllersTest {

  @Autowired
  private MockMvc mockMvc;
  
  @Autowired
  BookRepository bookRepository;
  
  @Before
  public void setUp() {

  }

  @Test
  public void getIndexWorks() throws Exception {
    mockMvc.perform(get("/")).andExpect(status().isOk());

    MvcResult res = mockMvc.perform(get("/"))
        .andReturn();

    String content = res.getResponse().getContentAsString();
    Assert.assertTrue(content.contains("Tervetuloa lukuvinkkisovellukseen"));
  }

  @Test
  public void modelHasAttributeBooks() throws Exception {
    mockMvc.perform(
        get("/books/list"))
        .andExpect(model()
            .attributeExists("books"));
  }

  @Test
  public void newBookIsCreatedAndAddedOnTheList() throws Exception {
    String title = "Coding Book";
    String author = "Writer McAuthor";

    mockMvc.perform(
            MockMvcRequestBuilders.post("/books/create")
                    .param("title", title)
                    .param("author", author)
                    .param("urlstring", "")
    ).andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/books/list")).andReturn();

    MvcResult res = mockMvc.perform(get("/books/list")).andReturn();
    String content = res.getResponse().getContentAsString();
    Assert.assertTrue(content.contains(title));
    Assert.assertTrue(content.contains(author));
  }
  
  @Test
  public void unreadBooksReturnsBooksModel () throws Exception {
    mockMvc.perform(
        get("/books/list/unread"))
        .andExpect(model()
            .attributeExists("books"));
  }
  
  @Test
  public void readBooksReturnsBooksModel () throws Exception {
    mockMvc.perform(
        get("/books/list/read"))
        .andExpect(model()
            .attributeExists("books"));
  }
  
  @Test
  public void bookCanBeMarkAsRead() throws Exception {
    Book book = new Book();
    bookRepository.save(book);
    Long id = book.getId();

    mockMvc.perform(
            MockMvcRequestBuilders.post("/mark")
                    .param("id", Long.toString(book.getId()))
                    .param("url", "unread")
    ).andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/books/list/unread")).andReturn();
  
    Book modifiedBook = bookRepository.getOne(id);
    Assert.assertEquals(1, modifiedBook.getIsRead());
  }
  
  @Test
  public void bookCanBeMarkAsUnread() throws Exception {
    Book book2 = new Book();
    bookRepository.save(book2);
    Long id = book2.getId();
    
    mockMvc.perform(
            MockMvcRequestBuilders.post("/unmark")
                    .param("id", Long.toString(book2.getId()))
                    .param("url", "read")
    ).andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/books/list/read")).andReturn();
  
    Book modifiedBook = bookRepository.getOne(id);
    Assert.assertEquals(0, modifiedBook.getIsRead()); 
  
  }

  @Test
  public void bookCanBeEdited() throws Exception {
    Book book = new Book();
    book.setTitle("cannotEdit");
    book.setAuthor("Editor");
    bookRepository.save(book);
    long id = book.getId();

    mockMvc.perform(MockMvcRequestBuilders.post("/books/edit")
                    .param("id", String.valueOf(id))
                    .param("url", "/books/list")
                    .param("title", "can edit")
            .param("author", "Editor")
            .param("urlstring", "")
    ).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/books/list")).andReturn();

    Book edited = bookRepository.getOne(id);
    Assert.assertEquals("can edit", edited.getTitle());
  }

  @Test
  public void bookTitleCannotBeEditedToBeExistingTitle() throws Exception {
    Book book = new Book();
    book.setTitle("existingTitle");
    book.setAuthor("Editor");
    Book differentBook = new Book();
    differentBook.setTitle("editToExistingTitle");
    differentBook.setAuthor("Editor");
    bookRepository.save(book);
    bookRepository.save((differentBook));

    long idDifferent = differentBook.getId();

    mockMvc.perform(MockMvcRequestBuilders.post("/books/edit")
            .param("id", String.valueOf(idDifferent))
            .param("url", "/books/list")
            .param("title", "existingTitle")
            .param("author", "Editor")
            .param("urlstring", "")

    ).andExpect(status().is3xxRedirection()).andReturn();

    Assert.assertEquals("editToExistingTitle", differentBook.getTitle());
  }

  @Test
  public void bookCanBeDeleted() throws Exception {
    Book deleteThis = new Book();
    deleteThis.setTitle("delete this");
    deleteThis.setAuthor("Deleter");
    bookRepository.save(deleteThis);

    mockMvc.perform(MockMvcRequestBuilders.post("/delete")
                    .param("id", String.valueOf(deleteThis.getId()))
            .param("url", "/books/list")
    ).andExpect(status().is3xxRedirection()).andReturn();

    Assert.assertTrue(bookRepository.findByTitle("delete this") == null);
  }
}