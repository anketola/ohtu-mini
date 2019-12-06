package ohtuminiprojekti;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import ohtuminiprojekti.dao.BookRepository;
import ohtuminiprojekti.domain.Book;
import org.junit.Assert;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  BookRepository bookRepository;

  @Test
  public void newBookIsCreatedAndAddedOnTheList() throws Exception {
    String title = "Coding Book";
    String author = "Writer McAuthor";

    mockMvc.perform(
        MockMvcRequestBuilders.post("/book/create")
            .param("title", title)
            .param("author", author)
            .param("comment", "")
    ).andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/bookmarks/list")).andReturn();

    MvcResult res = mockMvc.perform(get("/bookmarks/list")).andReturn();
    String content = res.getResponse().getContentAsString();
    Assert.assertTrue(content.contains(title));
    Assert.assertTrue(content.contains("book"));
  }

  @Test
  public void bookCanBeEdited() throws Exception {
    Book book = new Book();
    book.setTitle("aybabtu");
    book.setAuthor("123");
    bookRepository.save(book);

    String newTitle = "lol";
    String newAuthor = "asd";
    String newComment = "zzz";
    mockMvc.perform(
        MockMvcRequestBuilders.post("/book/edit")
            .param("id", String.valueOf(book.getId()))
            .param("title", newTitle)
            .param("author", newAuthor)
            .param("comment", newComment)
            .param("url", "/bookmarks/list")
    ).andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/bookmarks/list")).andReturn();

    MvcResult res = mockMvc.perform(get("/bookmarks/list")).andReturn();
    String content = res.getResponse().getContentAsString();
    Assert.assertTrue(content.contains(newTitle));
    Assert.assertTrue(content.contains("book"));
  }
  
  @Test
  public void getMethodbookQueryReturnsCorrectView() throws Exception {
    mockMvc.perform(get("/book/query"))
        .andExpect(status().isOk())
        .andExpect(view().name("bookquery"))
        .andReturn();
  }
  
  @Test
  public void postMethodAskBookValidISBNRedirectsToCreateWithISBN() throws Exception {
    String isbn = "0262033844";
    mockMvc.perform(
        MockMvcRequestBuilders.post("/book/query")
            .param("url", "/bookmarks/list")
            .param("isbn", isbn)
    ).andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/book/create/" + isbn))
            .andReturn();
  }
  
  @Test
  public void postMethodAskBookNonValidISBNRedirectsBack() throws Exception {
    String isbn = "Non Valid";
    mockMvc.perform(
        MockMvcRequestBuilders.post("/book/query")
            .param("url", "/bookmarks/list")
            .param("isbn", isbn)
    ).andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/book/query?error"))
            .andReturn();
  }
}