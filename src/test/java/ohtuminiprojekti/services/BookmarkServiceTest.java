package ohtuminiprojekti.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


import java.util.ArrayList;
import java.util.List;
import ohtuminiprojekti.dao.BookmarkRepository;
import ohtuminiprojekti.domain.Bookmark;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
public class BookmarkServiceTest {

  @InjectMocks
  private BookmarkService service;

  @Mock
  BookmarkRepository repo;

  List<Bookmark> bookmarkList;
  Bookmark b1, b2, b3;

  @Before
  public void setUp() {
    bookmarkList = new ArrayList<>();
    b1 = mock(Bookmark.class);
    b1.setName("b1name");
    b1.setHasBeenRead(false);
    b1.setComment("lol");
    b1.setType("book");
    b2 = mock(Bookmark.class);
    b1.setName("b2name");
    b1.setHasBeenRead(true);
    b1.setComment(null);
    b1.setType("book");
    b3 = mock(Bookmark.class);
    b1.setName("b3name");
    b1.setHasBeenRead(false);
    b1.setComment(null);
    b1.setType("link");
    bookmarkList.add(b1);
    bookmarkList.add(b2);
    bookmarkList.add(b3);
  }

  @Test
  public void listOfAllBookMarksIsReturned() {
    Mockito.when(service.allBookmarks()).thenReturn(bookmarkList);
    List<Bookmark> result = service.allBookmarks();
    verify(repo, times(1)).findAll();
    Assert.assertEquals(3, result.size());
  }

  @Test
  public void bookmarkCanBeDeleted() {
    Mockito.when(b1.getId()).thenReturn(1L);
    service.deleteBookmark(b1.getId());
    verify(repo, times(1)).deleteById(1L);
  }
  
  @Test
  public void bookmarkGetByIdGetsById() {
    Mockito.when(repo.getOne(1L)).thenReturn(b1);
    Bookmark testBookmark = service.getById(1L);
    verify(repo, times(1)).getOne(1L);
    assertEquals(b1, testBookmark);
  }
  
  @Test
  public void bookmarkByNameExistsReturnTrueWhenBookExists() {
    Mockito.when(repo.findByName("b1name")).thenReturn(b1);
    boolean exists = service.bookmarkByNameExists("b1name");
    verify(repo, times(1)).findByName("b1name");
    assertTrue(exists);
  }
  
  @Test
  public void bookmarkByNameExistsReturnFalseWhenBookDoesNotExist() {
    Mockito.when(repo.findByName("b1name")).thenReturn(null);
    boolean exists = service.bookmarkByNameExists("b1name");
    verify(repo, times(1)).findByName("b1name");
    assertFalse(exists);
  }
}
