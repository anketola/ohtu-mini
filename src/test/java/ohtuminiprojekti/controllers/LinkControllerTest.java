package ohtuminiprojekti;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import ohtuminiprojekti.dao.LinkRepository;
import ohtuminiprojekti.domain.Link;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LinkControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  LinkRepository linkRepository;

  String correctTestingUrl;
  
  @Before
  public void setUp() {
    correctTestingUrl = "https://www.helsinki.fi";    
  }
  
  
  @Test
  public void newLinkIsCreatedAndAddedOnTheList() throws Exception {
    String name = "Hel";
    String link = "https://www.helsinki.fi/";

    mockMvc.perform(
        MockMvcRequestBuilders.post("/link/create")
            .param("name", name)
            .param("link", link)
            .param("comment", "")
    ).andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/bookmarks/list")).andReturn();

    MvcResult res = mockMvc.perform(get("/bookmarks/list")).andReturn();
    String content = res.getResponse().getContentAsString();
    Assert.assertTrue(content.contains(name));
    Assert.assertTrue(content.contains("link"));
  }

  @Test
  public void linkCanBeEdited() throws Exception {
    Link link = new Link();
    link.setName("Hel");
    link.setLink("https://www.helsinki.fi/");
    linkRepository.save(link);

    String newName = "lol";
    String newLink = "www.asd.com";
    String newComment = "zzz";
    mockMvc.perform(
        MockMvcRequestBuilders.post("/link/edit")
            .param("id", String.valueOf(link.getId()))
            .param("name", newName)
            .param("link", newLink)
            .param("comment", newComment)
            .param("url", "/bookmarks/list")
    ).andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/bookmarks/list")).andReturn();

    MvcResult res = mockMvc.perform(get("/bookmarks/list")).andReturn();
    String content = res.getResponse().getContentAsString();
    Assert.assertTrue(content.contains(newName));
    Assert.assertTrue(content.contains("link"));
  }
  
  @Test
  public void postMethodAskLinkValidUrlRedirectsToCreateWithUrl() throws Exception {
    String encodedParams = URLEncoder.encode(correctTestingUrl, StandardCharsets.UTF_8.toString());
    mockMvc.perform(
        MockMvcRequestBuilders.post("/link/query")
            .param("url", "/bookmarks/list")
            .param("link", correctTestingUrl)
    ).andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/link/create?url=" + encodedParams))
            .andReturn();
  }
  
  @Test
  public void getMethodlinkQueryReturnsCorrectView() throws Exception {
    mockMvc.perform(get("/link/query"))
        .andExpect(status().isOk())
        .andExpect(view().name("linkquery"))
        .andReturn();
  }
  
  @Test
  public void getMethodNewLinkReturnsCorrectView() throws Exception {
    mockMvc.perform(get("/link/create")
        .param("url", correctTestingUrl)
    ).andExpect(status().isOk())
        .andExpect(view().name("newlink"))
        .andReturn();
  }
  
  @Test
  public void getMethodEditLinkReturnsCorrectView() throws Exception {
    Link link = new Link();
    link.setName("Hel");
    link.setLink("https://www.helsinki.fi/");
    linkRepository.save(link);
      
    mockMvc.perform(get("/link/edit")
        .param("id", String.valueOf(link.getId()))
        .param("url", "/bookmarks/list")
    ).andExpect(status().isOk())
        .andExpect(view().name("editlink"))
        .andReturn();
  }


  
}