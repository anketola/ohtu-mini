package ohtuminiprojekti.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import ohtuminiprojekti.dao.BookmarkRepository;
import ohtuminiprojekti.domain.Bookmark;
import ohtuminiprojekti.services.BookmarkService;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookmarkControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  BookmarkRepository bookmarkRepository;
  @Autowired
  BookmarkService bookmarkService;

  Bookmark bm1, bm2;

  @Before
  public void setUp() {
    bm1 = new Bookmark();
    bm1.setType("book");
    bm1.setName("bm1");
    bm1.setComment("comcom");
    bm1.setHasBeenRead(false);
    bm2 = new Bookmark();
    bm2.setType("link");
    bm2.setName("bm2");
    bm2.setComment(null);
    bm2.setHasBeenRead(true);
    bookmarkRepository.save(bm1);
    bookmarkRepository.save(bm2);
  }

  @Test
  public void unreadBookmarksReturnsBookmarksmodel() throws Exception {
    mockMvc.perform(
        get("/bookmarks/list/unread"))
        .andExpect(model()
            .attributeExists("bookmarks"));
  }

  @Test
  public void readBooksReturnsBookmarksModel() throws Exception {
    mockMvc.perform(
        get("/bookmarks/list/read"))
        .andExpect(model()
            .attributeExists("bookmarks"));
  }

  @Test
  public void bookmarkCanBeMarkAsRead() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/bookmarks/mark")
            .param("id", Long.toString(bm1.getId()))
            .param("url", "unread")
    ).andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/bookmarks/list/unread")).andReturn();

    Assert.assertEquals(true, bookmarkRepository.getOne(bm1.getId()).hasBeenRead());
  }

  @Test
  public void bookmarkCanBeMarkAsUnread() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/bookmarks/unmark")
            .param("id", Long.toString(bm2.getId()))
            .param("url", "read")
    ).andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/bookmarks/list/read")).andReturn();

    Assert.assertEquals(false, bookmarkRepository.getOne(bm2.getId()).hasBeenRead());
  }

  @Test
  public void bookmarkCanBeDeleted() throws Exception {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/bookmarks/delete")
            .param("id", Long.toString(bm2.getId()))
            .param("url", "read")
    ).andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/bookmarks/list/read")).andReturn();

    Assert.assertEquals(false, bookmarkRepository.existsById(bm2.getId()));
  }
}