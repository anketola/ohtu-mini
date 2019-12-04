package ohtuminiprojekti.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
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
    link.setLink(url);
    link.setComment(comment);
    link.setHasBeenRead(false);
    linkRepository.save(link);
    return link;
  }

  public Link getById(long id) {
    return linkRepository.getOne(id);
  }
  
  public boolean isURL(String url) {
    try {
     (new java.net.URL(url)).openStream().close();
     return true;
    } catch (Exception ex) { }
    return false;
  }

  public boolean existingLinkByUrl(String url) {
    return linkRepository.findByLink(url) != null;
  }

  public void edit(long id, String name, String url, String comment) {
    Link link = linkRepository.getOne(id);
    link.setLink(url);
    link.setName(name);
    link.setComment(comment);
    linkRepository.save(link);
  }

  public String getTitleOfUrl(String url) {
    String title = "";
    InputStream response = null; 
    try {
      response = new URL(url).openStream();
      Scanner scanner = new Scanner(response);
      String responseBody = scanner.useDelimiter("\\A").next();
      title = responseBody.substring(responseBody.indexOf("<title>") + 7, responseBody.indexOf("</title>"));
      response.close();
    } catch (IOException ex) {
    } 
    return title;
  }
}
