package ohtuminiprojekti.domain;


import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video extends Bookmark {

  private String link;
  private String type = "video";

}
