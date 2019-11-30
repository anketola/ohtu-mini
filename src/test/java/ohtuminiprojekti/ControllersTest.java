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
}