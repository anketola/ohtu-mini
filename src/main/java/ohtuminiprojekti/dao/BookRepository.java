package ohtuminiprojekti.dao;

import ohtuminiprojekti.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
  Book findByTitleAndAuthor(String title, String author);
}
