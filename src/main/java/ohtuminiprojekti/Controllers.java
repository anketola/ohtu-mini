package ohtuminiprojekti;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controllers {

  @GetMapping("/")
  public String indexRoot() {
    return "redirect:/bookmarks/list";
  }
}
