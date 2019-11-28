package ohtuminiprojekti.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book extends AbstractPersistable<Long> {

  private String title;
  private String author;
  private String urlstring;
  private int isRead;
  @OneToOne(cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
  private Picture picture;

}
