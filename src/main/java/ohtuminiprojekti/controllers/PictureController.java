package ohtuminiprojekti.controllers;

import java.io.IOException;
import ohtuminiprojekti.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureController {

  @Autowired
  private PictureService pictureService;

  @GetMapping("/testarea")
  public String showTestArea() {
    return "devtemplate";
  }

  @PostMapping("/pictures/books/create")
  public String createBookImage(@RequestParam("file") MultipartFile file, Long bookid) throws IOException {
    pictureService.newPicture(file, "book", bookid);
    return "redirect:/testarea";
  }

  @GetMapping(path = "/pictures/{id}", produces = "image/jpeg")
  @ResponseBody
  public byte[] getPicture(@PathVariable Long id) {
    return pictureService.getPicture(id);
  }

}
