package ohtuminiprojekti;

import java.io.IOException;
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
    } catch(IllegalArgumentException err) {
      // Invalid url
    } catch(IOException err) {
      err.printStackTrace();
    }
    return "";
  }

  public static String getOgImageMetatag(String url) {
    try {
      Document doc = Jsoup.connect(url).get();
      return doc.head().getElementsByAttributeValue("property", "og:image").get(0).attr("content");
    } catch(IllegalArgumentException err) {
      // Invalid url
    } catch(Exception e) {
      e.printStackTrace();
    }
    return "";
  }

  public static String getYoutubeVideoThumbnail(String url) {
    try {
      String videoId = "";
      if(url.contains("youtu.be/")) {
        videoId = url.split("utu.be/")[1].split("\\?")[0];
      } else if(url.contains("youtube.com")) {
        videoId = url.split("\\?v=")[1].split("&")[0];
      } else {
        return "";
      }
      return "https://img.youtube.com/vi/" + videoId + "/0.jpg";
    } catch(ArrayIndexOutOfBoundsException err) {
    }
    return "";
  }
}
