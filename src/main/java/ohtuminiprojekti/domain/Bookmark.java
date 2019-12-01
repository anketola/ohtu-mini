package ohtuminiprojekti.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(
    strategy = InheritanceType.JOINED
)
public class Bookmark extends AbstractPersistable<Long> {

  private String type;
  private String name;
  private String comment;
  private boolean hasBeenRead;

  @OneToOne(cascade = CascadeType.ALL, optional = true, orphanRemoval = true)
  private Picture picture;

  public String getType() {
    return type;
  }

  public String getComment() {
    return comment;
  }

  public boolean hasBeenRead() {
    return hasBeenRead;
  }

  public Picture getPicture() {
    return picture;
  }
}
