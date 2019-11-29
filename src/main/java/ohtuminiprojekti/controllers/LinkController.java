package ohtuminiprojekti.controllers;

import ohtuminiprojekti.Utils;
import ohtuminiprojekti.services.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LinkController {

  @Autowired
  private LinkService linkService;

  @PostMapping("/link/create")
  public String createLink(@RequestParam String name, @RequestParam String url, @RequestParam String comment) {
    if (!linkService.existingLinkByUrl(url)) {
      linkService.newLink(name, url, comment);
      return "redirect:/bookmarks/list";
    } else {
      return "redirect:/bookmarks/create?error";
    }
  }

  @GetMapping("/link/edit")
  public String editLink(Model model, @RequestParam long id, @RequestParam String url) {
    model.addAttribute("link", linkService.getById(id));
    model.addAttribute("url", url);
    return "editlink";
  }

  @GetMapping("/link/create")
  public String newLink() {
    return "newlink";
  }

  @PostMapping("/link/edit")
  public String editLink(RedirectAttributes redirectAttributes, @RequestParam long id, @RequestParam String name, @RequestParam String url, @RequestParam String comment) {
    if (!linkService.existingLinkByUrl(url)) {
      linkService.edit(id, name, url, comment);
      return Utils.redirectToSameListing(url);
    }
    redirectAttributes.addAttribute("id", id);
    redirectAttributes.addAttribute("url", url);
    return "redirect:/link/edit?error";
  }
}