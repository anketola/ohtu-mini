package ohtuminiprojekti.controllers;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import ohtuminiprojekti.dao.VideoRepository;
import ohtuminiprojekti.domain.Video;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VideoControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  VideoRepository videoRepository;
  
  String correctTestingUrl;
  
  @Before
  public void setUp() {
    correctTestingUrl = "https://www.helsinki.fi";    
  }
  
  @Test
  public void newVideoIsCreatedAndAddedOnTheList() throws Exception {
    String name = "Hel";
    String link = "https://www.helsinki.fi/";

    mockMvc.perform(
        MockMvcRequestBuilders.post("/video/create")
            .param("name", name)
            .param("link", link)
            .param("comment", "")
    ).andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/bookmarks/list")).andReturn();

    MvcResult res = mockMvc.perform(get("/bookmarks/list")).andReturn();
    String content = res.getResponse().getContentAsString();
    Assert.assertTrue(content.contains(name));
    Assert.assertTrue(content.contains("video"));
  }

  @Test
  public void videoCanBeEdited() throws Exception {
    Video video = new Video();
    video.setName("Hel");
    video.setLink("https://www.helsinki.fi/");
    videoRepository.save(video);

    String newName = "lol";
    String newLink = "www.asd.com";
    String newComment = "zzz";
    mockMvc.perform(
        MockMvcRequestBuilders.post("/video/edit")
            .param("id", String.valueOf(video.getId()))
            .param("name", newName)
            .param("link", newLink)
            .param("comment", newComment)
            .param("url", "/bookmarks/list")
    ).andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/bookmarks/list")).andReturn();

    MvcResult res = mockMvc.perform(get("/bookmarks/list")).andReturn();
    String content = res.getResponse().getContentAsString();
    Assert.assertTrue(content.contains(newName));
    Assert.assertTrue(content.contains("video"));
  }
  
  @Test
  public void postMethodAskVideoValidUrlRedirectsToCreateWithUrl() throws Exception {
    String encodedParams = URLEncoder.encode(correctTestingUrl, StandardCharsets.UTF_8.toString());
    mockMvc.perform(
        MockMvcRequestBuilders.post("/video/query")
            .param("url", "/bookmarks/list")
            .param("link", correctTestingUrl)
    ).andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/video/create?url=" + encodedParams))
            .andReturn();
  }
  
  @Test
  public void postMethodAskVideoNonValidUrlRedirectsBack() throws Exception {
    String url = "Non valid";
    mockMvc.perform(
        MockMvcRequestBuilders.post("/video/query")
            .param("url", "/bookmarks/list")
            .param("link", url)
    ).andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/video/query?error"))
            .andReturn();
  }
  
  @Test
  public void getMethodVideoQueryReturnsCorrectView() throws Exception {
    mockMvc.perform(get("/video/query"))
        .andExpect(status().isOk())
        .andExpect(view().name("videoquery"))
        .andReturn();
  }

  @Test
  public void getMethodNewVideoReturnsCorrectView() throws Exception {
    mockMvc.perform(get("/video/create")
        .param("url", correctTestingUrl)
    ).andExpect(status().isOk())
        .andExpect(view().name("newvideo"))
        .andReturn();
  }
  
  @Test
  public void getMethodEditVideoReturnsCorrectView() throws Exception {
    Video video = new Video();
    video.setName("Hel");
    video.setLink("https://www.helsinki.fi/");
    videoRepository.save(video);
      
    mockMvc.perform(get("/video/edit")
        .param("id", String.valueOf(video.getId()))
        .param("url", "/bookmarks/list")
    ).andExpect(status().isOk())
        .andExpect(view().name("editvideo"))
        .andReturn();
  }
}
