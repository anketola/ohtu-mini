package ohtuminiprojekti.services;

import ohtuminiprojekti.Utils;
import ohtuminiprojekti.dao.VideoRepository;
import ohtuminiprojekti.domain.Video;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class VideoServiceTest {  
  @InjectMocks
  VideoService videoService;
  
  @Mock
  VideoRepository videoRepository;
  
  String correctTestingUrl;
  String correctUrlTitle;
  String incorrectTestingUrl;
  String incorrectUrlTitle;
  String validName;
  String validComment;
  Video testVideo;
  
  @Before
  public void setUp() {
    correctTestingUrl = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";
    incorrectTestingUrl = "https://w.helsinki.f";
    correctUrlTitle = "Rick Astley - Never Gonna Give You Up (Video) - YouTube";
    incorrectUrlTitle = "";
    validName = "Testilinkki";
    validComment = "Testikommentti";
  }
  
  @Test
  public void isUrlReturnsTrueWithRealUrl() {
    Assert.assertTrue(videoService.isURL(correctTestingUrl));
  }
  
  @Test
  public void isUrlReturnsFalseWithIncorrectUrl() {
    Assert.assertTrue(!videoService.isURL(incorrectTestingUrl));
  }
  
  //@Test
  //public void correctTitleForUrlIsReturned() {
  //  Assert.assertEquals(correctUrlTitle, videoService.getTitleOfUrl(correctTestingUrl));
  //}
  
  @Test
  public void emptyTitleForIncorrectUrlIsReturned() {
    Assert.assertEquals(incorrectUrlTitle, Utils.getTitleOfUrl(incorrectTestingUrl));
  }
  
  @Test
  public void validVideoIsSaved() {
    videoService.newVideo(validName, correctTestingUrl, validComment);
    Mockito.verify(videoRepository, Mockito.times(1)).save(Mockito.any(Video.class));
  }

  @Test
  public void existingVideoByUrlPeformsCorrectDatabaseQuery() {
    videoService.existingVideoByUrl(correctTestingUrl);
    Mockito.verify(videoRepository, Mockito.times(1)).findByLink(correctTestingUrl);
  }
  
  @Test
  public void existingVideoByUrlReturnTrueWhenVideoExists() {
    Mockito.when(videoRepository.findByLink(correctTestingUrl)).thenReturn(new Video());
    boolean exists = videoService.existingVideoByUrl(correctTestingUrl);
    assertTrue(exists);
  }
  
  @Test
  public void existingVideoByUrlReturnFalseWhenVideoDoesNotExist() {
    Mockito.when(videoRepository.findByLink(correctTestingUrl)).thenReturn(null);
    boolean exists = videoService.existingVideoByUrl(correctTestingUrl);
    assertFalse(exists);
  }
  
}
