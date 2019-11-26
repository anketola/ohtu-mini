package ohtuminiprojekti.dao;

import ohtuminiprojekti.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    
}
