package ohtuminiprojekti.services;

import java.io.IOException;
import javax.transaction.Transactional;
import ohtuminiprojekti.dao.BookRepository;
import ohtuminiprojekti.dao.PictureRepository;
import ohtuminiprojekti.domain.Book;
import ohtuminiprojekti.domain.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureService {

  @Autowired
  private PictureRepository pictureRepository;

  @Autowired
  BookRepository bookRepository;

  @Transactional
  public void newPicture(MultipartFile file, String type, Long id) throws IOException {
    if (file.getContentType().equals("image/jpeg")) {
      if (type.equals("book")) {
        Book book = bookRepository.getOne(id);
        if (book.getPicture() == null) {
          Picture newPicture = new Picture();
          newPicture.setContent(file.getBytes());
          book.setPicture(newPicture);
          newPicture.setBook(book);
        } else {
          book.getPicture().setContent(file.getBytes());
        }
      }
    }
  }

  @Transactional
  public byte[] getPicture(Long id) {
    return pictureRepository.getOne(id).getContent();
  }

}
