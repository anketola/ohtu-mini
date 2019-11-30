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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PictureController {

  @Autowired
  private PictureService pictureService;

  @PostMapping("/pictures/books/create")
  public String createBookImage(RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file, @RequestParam String url, @RequestParam long id) throws IOException {
    pictureService.newPicture(file, "book", id);
    redirectAttributes.addAttribute("id", id);
    redirectAttributes.addAttribute("url", url);
    return "redirect:/books/edit";
  }

  @GetMapping(path = "/pictures/{id}", produces = "image/jpeg")
  @ResponseBody
  public byte[] getPicture(@PathVariable Long id) {
    return pictureService.getPicture(id);
  }
  
}
