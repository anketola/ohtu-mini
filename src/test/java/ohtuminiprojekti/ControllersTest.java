package ohtuminiprojekti;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import ohtuminiprojekti.dao.BookRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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
  public void getIndexRedirectsToListingPage() throws Exception {
    
    mockMvc.perform(get("/"))
        .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/bookmarks/list")).andReturn();

  }
}