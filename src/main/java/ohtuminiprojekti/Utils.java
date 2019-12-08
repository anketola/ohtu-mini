package ohtuminiprojekti;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Utils {
  public static String redirectToSameListing(String url) {
    if (url.contains("unread")) {
      return "redirect:/bookmarks/list/unread";
    } else if (url.contains("read")) {
      return "redirect:/bookmarks/list/read";
    }
    return "redirect:/bookmarks/list";
  }

  public static String getTitleOfUrl(String url) {
    try {
      Document doc = Jsoup.connect(url).get();
      return doc.title();
    } catch(IOException err) {
      err.printStackTrace();;
    }
    return "";
  }

  }
}
