package ohtuminiprojekti.dao;

import ohtuminiprojekti.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
  Video findByLink(String url);
}
