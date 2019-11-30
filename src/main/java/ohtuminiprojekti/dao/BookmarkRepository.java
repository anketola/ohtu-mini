package ohtuminiprojekti.dao;

import java.util.List;
import ohtuminiprojekti.domain.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
  Bookmark findByName(String name);

  List<Bookmark> findByHasBeenRead(boolean status);
}
