package ohtuminiprojekti;

import org.junit.Assert;;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllersTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {

    }

    @Test
    public void getIndexWorks() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());

        MvcResult res = mockMvc.perform(get("/"))
                .andReturn();

        String content = res.getResponse().getContentAsString();
        Assert.assertTrue(content.contains("Tervetuloa kirjapalveluun"));
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
        ).andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/books/list")).andReturn();

        MvcResult res= mockMvc.perform(get("/books/list")).andReturn();
        String content = res.getResponse().getContentAsString();
        Assert.assertTrue(content.contains(title));
        Assert.assertTrue(content.contains(author));
    }
}