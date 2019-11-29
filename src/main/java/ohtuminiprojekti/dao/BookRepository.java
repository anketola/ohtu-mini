package ohtuminiprojekti.dao;

import java.util.List;
import ohtuminiprojekti.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
  Book findByTitle(String title);
  
  List<Book> findByisRead(int status);

}
