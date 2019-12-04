package ohtuminiprojekti;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class Isbn {

  private String isbn;
  private String title;
  private String authors;
  private String thumbnailLink;
  private boolean valid;

  public Isbn(String isbn) {
    this.isbn = isbn;
    if(validateISBN(isbn)) {
      valid = true;
      getISBNInformation();
    } else {
      valid = false;
    }
  }

  // Returns true if the given string is a valid ISBN
  private boolean validateISBN(String isbn) {
    int checksum = 0;
    if(isbn.length() == 10) {
      for(int i = 0; i < 9; i++) {
        char cha = isbn.charAt(i);
        if('0' > cha || cha > '9') {
          return false;
        }
        checksum += (10 - i) * (cha - '0');
      }
      checksum = (11-checksum%11)%11;
      return checksum == isbn.charAt(9) - '0';
    } else if(isbn.length() == 13) {
      for(int i = 0; i < 12; i++) {
        char cha = isbn.charAt(i);
        if('0' > cha || cha > '9') {
          return false;
        }
        int digit = cha - '0';
        if(i % 2 == 0) {
          checksum += digit;
        } else {
          checksum += digit * 3;
        }
      }
      checksum = (10 - checksum % 10) % 10;
      return isbn.charAt(12) - '0' == checksum;
    }
    return false;
  }

  private void getISBNInformation() {
    String apiLink = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + getIsbn();
    try {
      URL url = new URL(apiLink);
      URLConnection conn = url.openConnection();
      String content = new BufferedReader(new InputStreamReader(conn.getInputStream()))
          .lines().collect(Collectors.joining("\n"));

      JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();

      if(jsonObject.has("items")) {
        JsonObject item = jsonObject.get("items").getAsJsonArray().get(0).getAsJsonObject();
        if(item.has("volumeInfo")) {
          JsonObject volumeInfo = item.get("volumeInfo").getAsJsonObject();

          title = getTitle(volumeInfo);
          authors = getAuthors(volumeInfo);
          thumbnailLink = getThumbnail(volumeInfo);
        }
      }
    } catch(IOException err) {
      err.printStackTrace();
    }
  }

  private String getTitle(JsonObject volumeInfo) {
    if(volumeInfo.has("title")) {
      return volumeInfo.get("title").getAsString();
    }
    return null;
  }

  private String getAuthors(JsonObject volumeInfo) {
    if(volumeInfo.has("authors")) {
      JsonArray authorsArray = volumeInfo.get("authors").getAsJsonArray();
      StringBuilder sb = new StringBuilder();
      for(int i = 0; i < authorsArray.size(); i++) {
        if(i > 0) {
          sb.append(", ");
        }
        sb.append(authorsArray.get(i).getAsString());
      }
      return sb.toString();
    }
    return null;
  }

  private String getThumbnail(JsonObject volumeInfo) {
    if(volumeInfo.has("imageLinks")) {
      JsonObject imageLinks = volumeInfo.get("imageLinks").getAsJsonObject();
      if(imageLinks.has("thumbnail")) {
        return imageLinks.get("thumbnail").getAsString();
      }
    }
    return null;
  }

  public String toString() {
    return getIsbn() + " " + getTitle() + " " + getAuthors() + " " + getThumbnailLink();
  }

  public String getIsbn() {
    return isbn;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthors() {
    return authors;
  }

  public String getThumbnailLink() {
    return thumbnailLink;
  }

  public boolean isValid() {
    return valid;
  }
}