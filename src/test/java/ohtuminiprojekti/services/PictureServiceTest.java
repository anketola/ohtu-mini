
package ohtuminiprojekti.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ohtuminiprojekti.dao.BookRepository;
import ohtuminiprojekti.dao.PictureRepository;
import ohtuminiprojekti.domain.Book;
import ohtuminiprojekti.domain.Picture;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class PictureServiceTest {
  @InjectMocks
  private PictureService service;

  @Mock
  PictureRepository pictureRepo;

  @Mock
  BookRepository bookRepo;
  

  @Test
  public void newPictureIsSaved() throws IOException {
    MultipartFile file = mock(MultipartFile.class);
    Book book = mock(Book.class);
    when(bookRepo.getOne(1L)).thenReturn(book);
    when(book.getPicture()).thenReturn(null);
    when(file.getContentType()).thenReturn("image/jpeg");
    when(file.getBytes()).thenReturn(new byte[10]);
    service.newPicture(file, "book", 1L);
    verify(book, times(1)).setPicture(any(Picture.class));
    verify(book, times(1)).getPicture();
  }
  
  @Test
  public void newPictureIsNotSavedWhenFileNotJPG() throws IOException {
    MultipartFile file = mock(MultipartFile.class);
    Book book = mock(Book.class);
    when(bookRepo.getOne(1L)).thenReturn(book);
    when(book.getPicture()).thenReturn(null);
    when(file.getContentType()).thenReturn("image/jp");
    when(file.getBytes()).thenReturn(new byte[10]);
    service.newPicture(file, "book", 1L);
    verify(book, times(0)).setPicture(any(Picture.class));
  }
  
  @Test
  public void newPictureIsNotSavedWhenTypeNotBook() throws IOException {
    MultipartFile file = mock(MultipartFile.class);
    Book book = mock(Book.class);
    when(bookRepo.getOne(1L)).thenReturn(book);
    when(book.getPicture()).thenReturn(null);
    when(file.getContentType()).thenReturn("image/jpeg");
    when(file.getBytes()).thenReturn(new byte[10]);
    service.newPicture(file, "link", 1L);
    verify(book, times(0)).setPicture(any(Picture.class));
  }
  
  @Test
  public void pictureContentIsSwitchedIfBookHasOne() throws IOException {
    MultipartFile file = mock(MultipartFile.class);
    Book book = mock(Book.class);
    Picture picture = mock(Picture.class);
    when(bookRepo.getOne(1L)).thenReturn(book);
    when(book.getPicture()).thenReturn(picture);
    when(file.getContentType()).thenReturn("image/jpeg");
    when(file.getBytes()).thenReturn(new byte[10]);
    service.newPicture(file, "book", 1L);
    verify(book, times(2)).getPicture();
    verify(picture, times(1)).setContent(any(byte[].class));
  }
  
  @Test
  public void pictureContentCanBeRetrieved() throws IOException {
    
    Picture picture = mock(Picture.class);
    when(pictureRepo.getOne(1L)).thenReturn(picture);
    when(picture.getContent()).thenReturn(new byte[10]);
    byte[] content = service.getPicture(1L);    
    verify(pictureRepo, times(1)).getOne(1L);
    verify(picture, times(1)).getContent();
  }
}
