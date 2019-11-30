package ohtuminiprojekti.controllers;

import ohtuminiprojekti.Utils;
import ohtuminiprojekti.services.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookmarkController {

  @Autowired
  private BookmarkService bookmarkService;

  @GetMapping("/bookmarks/list")
  public String listBookmarks(Model model) {
    model.addAttribute("bookmarks", this.bookmarkService.allBookmarks());
    return "listbookmarks";
  }

  @PostMapping("/bookmarks/delete")
  public String deleteBookmark(@RequestParam long id, @RequestParam String url) {

    this.bookmarkService.deleteBookmark(id);
    return Utils.redirectToSameListing(url);
  }

  @PostMapping("/bookmarks/mark")
  public String markBookmarkAsRead(@RequestParam long id, @RequestParam String url) {
    bookmarkService.setBookmarkRead(id, true);
    return Utils.redirectToSameListing(url);
  }

  @PostMapping("/bookmarks/unmark")
  public String markBookmarkAsUnread(@RequestParam long id, @RequestParam String url) {
    bookmarkService.setBookmarkRead(id, false);
    return Utils.redirectToSameListing(url);
  }

  @GetMapping("/bookmarks/list/read")
  public String listReadBookmarks(Model model) {
    model.addAttribute("bookmarks", this.bookmarkService.readBooks());
    return "listbookmarks";
  }

  @GetMapping("/bookmarks/list/unread")
  public String listUnreadBookmarks(Model model) {
    model.addAttribute("bookmarks", this.bookmarkService.unreadBooks());
    return "listbookmarks";
  }
}
