package ohtuminiprojekti.services;

import ohtuminiprojekti.dao.LinkRepository;
import ohtuminiprojekti.domain.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

  @Autowired
  private LinkRepository linkRepository;

  public Link newLink(String name, String url, String comment) {
    Link link = new Link();
    link.setName(name);
    link.setUrl(url);
    link.setComment(comment);
    link.setHasBeenRead(false);
    linkRepository.save(link);
    return link;
  }

  public Link getById(long id) {
    return linkRepository.getOne(id);
  }

  public boolean existingLinkByUrl(String url) {
    return linkRepository.findByUrl(url) != null;
  }

  public void edit(long id, String name, String url, String comment) {
    Link link = linkRepository.getOne(id);
    link.setUrl(url);
    link.setName(name);
    link.setComment(comment);
    linkRepository.save(link);
  }
}
