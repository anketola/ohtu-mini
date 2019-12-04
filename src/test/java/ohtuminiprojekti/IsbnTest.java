package ohtuminiprojekti;

import org.junit.Assert;
import org.junit.Test;

public class IsbnTest {

  @Test
  public void isbn10Test() {
    Isbn isbn = new Isbn("0262033844");
    Assert.assertEquals(true, isbn.isValid());
    Assert.assertEquals("0262033844 Introduction to Algorithms Thomas H. Cormen http://books.google.com/books/content?id=i-bUBQAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api", isbn.toString());
  }

  @Test
  public void isbn13Test() {
    Isbn isbn = new Isbn("9780201896831");
    Assert.assertEquals(true, isbn.isValid());
    Assert.assertEquals("9780201896831 The Art of Computer Programming: Fundamental algorithms Donald Ervin Knuth http://books.google.com/books/content?id=5oJQAAAAMAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api", isbn.toString());
  }

  @Test
  public void noThumbnailTest() {
    Assert.assertEquals("8385568034 Koniec i początek Wisława Szymborska null", new Isbn("8385568034").toString());
  }

  @Test
  public void multipleAuthorsTest() {
    Assert.assertEquals("9784047136816 NHKにようこそ!(1) 滝本竜彦, 大岩ケンヂ http://books.google.com/books/content?id=f_y9M3sy44sC&printsec=frontcover&img=1&zoom=1&source=gbs_api", new Isbn("9784047136816").toString());
  }

  @Test
  public void invalidIsbnsTest() {
    Assert.assertEquals(false, new Isbn("9780840700553").isValid());
    Assert.assertEquals(false, new Isbn("9780840700552").isValid());
    Assert.assertEquals(false, new Isbn("0840700557").isValid());
    Assert.assertEquals(false, new Isbn("0840700553").isValid());
    Assert.assertEquals(false, new Isbn("asd").isValid());
    Assert.assertEquals(false, new Isbn("1-2-3-4-5-6-7-8-9-10").isValid());
  }
}
