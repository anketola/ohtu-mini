
package ohtuminiprojekti.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Book extends AbstractPersistable<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String author;

  public void setId(Long id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Long getId() {
    return this.id;
  }

  public String getTitle() {
    return this.title;
  }

  public String getAuthor() {
    return this.author;
  }

}
