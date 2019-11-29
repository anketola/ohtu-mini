package ohtuminiprojekti.services;

import java.util.List;
import ohtuminiprojekti.dao.BookmarkRepository;
import ohtuminiprojekti.domain.Bookmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookmarkService {

  @Autowired
  private BookmarkRepository bookmarkRepository;

  public List<Bookmark> allBookmarks() {
    return bookmarkRepository.findAll();
  }

  public Bookmark getById(long id) {
    return bookmarkRepository.getOne(id);
  }

  public List<Bookmark> readBooks() {
    return bookmarkRepository.findByHasBeenRead(true);
  }

  public void setName(long id, String name) {
    bookmarkRepository.getOne(id).setName(name);
  }

  public List<Bookmark> unreadBooks() {
    return bookmarkRepository.findByHasBeenRead(false);
  }

  public void deleteBookmark(long id) {
    bookmarkRepository.deleteById(id);
  }

  public void setBookmarkRead(long id, boolean hasBeenRead) {
    Bookmark bookmark = bookmarkRepository.getOne(id);
    bookmark.setHasBeenRead(hasBeenRead);
    bookmarkRepository.save(bookmark);
  }

  public boolean bookmarkByNameExists(String title) {
    return bookmarkRepository.findByName(title) != null;
  }
}
