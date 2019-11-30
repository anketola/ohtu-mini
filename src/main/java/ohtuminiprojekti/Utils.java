package ohtuminiprojekti;

public class Utils {
  public static String redirectToSameListing(String url) {
    if (url.contains("unread")) {
      return "redirect:/bookmarks/list/unread";
    } else if (url.contains("read")) {
      return "redirect:/bookmarks/list/read";
    }
    return "redirect:/bookmarks/list";
  }
}
