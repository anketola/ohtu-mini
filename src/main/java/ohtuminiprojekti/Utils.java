package ohtuminiprojekti;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

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
