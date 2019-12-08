
package ohtuminiprojekti.controllers;

import ohtuminiprojekti.Utils;
import ohtuminiprojekti.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VideoController {
  @Autowired
  private VideoService videoService;

  @PostMapping("/video/create")
  public String createVideo(RedirectAttributes redirectAttributes, @RequestParam String name, @RequestParam String link, @RequestParam String comment) {
    if (!videoService.existingVideoByUrl(link)) {
      videoService.newVideo(name, link, comment);
      return "redirect:/bookmarks/list";
    } else {
      redirectAttributes.addAttribute("url", link);
      return "redirect:/video/create?error";
    }
  }
  
  @GetMapping("/video/query")
  public String askVideo() {
    return "videoquery";
  }
  
  @PostMapping("/video/query")
  public String askVideo(RedirectAttributes redirectAttributes, @RequestParam String link) {
    if (!videoService.isURL(link)) {
      return "redirect:/video/query?error";
    }
    redirectAttributes.addAttribute("url", link);
    return "redirect:/video/create";
  }

  @GetMapping("/video/edit")
  public String editVideo(Model model, @RequestParam long id, @RequestParam String url) {
    model.addAttribute("video", videoService.getById(id));
    model.addAttribute("url", url);
    return "editvideo";
  }

  @GetMapping("/video/create")
  public String newVideo(Model model, @RequestParam String url) {
    model.addAttribute("name", Utils.getTitleOfUrl(url));
    model.addAttribute("link", url);
    return "newvideo";
  }

  @PostMapping("/video/edit")
  public String editVideo(RedirectAttributes redirectAttributes, @RequestParam long id, @RequestParam String url, @RequestParam String name, @RequestParam String link, @RequestParam String comment) {
    if (videoService.getById(id).getLink().equals(link) || !videoService.existingVideoByUrl(link)) {
      videoService.edit(id, name, link, comment);
      return Utils.redirectToSameListing(url);
    }
    redirectAttributes.addAttribute("id", id);
    redirectAttributes.addAttribute("url", url);
    return "redirect:/video/edit?error";
  }
}
