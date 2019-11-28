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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
      
    mockMvc.perform(
            MockMvcRequestBuilders.post("/mark")
                    .param("id", "1")
                    .param("url", "unread")
    ).andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/books/list/unread")).andReturn();
  
    Book modifiedBook = bookRepository.getOne(1L);
    
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

}