package ohtuminiprojekti.services;

import ohtuminiprojekti.Utils;
import ohtuminiprojekti.dao.LinkRepository;
import ohtuminiprojekti.domain.Link;
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
public class LinkServiceTest {
  
  @InjectMocks
  LinkService linkService;
  
  @Mock
  LinkRepository linkRepository;
  
  String correctTestingUrl;
  String correctUrlTitle;
  String incorrectTestingUrl;
  String incorrectUrlTitle;
  String validName;
  String validComment;
  Link testLink;
  
  @Before
  public void setUp() {
    correctTestingUrl = "https://www.helsinki.fi";
    incorrectTestingUrl = "https://w.helsinki.f";
    correctUrlTitle = "Tutkimusta, opetusta ja yhteistyötä | Helsingin yliopisto";
    incorrectUrlTitle = "";
    validName = "Testilinkki";
    validComment = "Testikommentti";
  }
  
  @Test
  public void isUrlReturnsTrueWithRealUrl() {
    Assert.assertTrue(linkService.isURL(correctTestingUrl));
  }
  
  @Test
  public void isUrlReturnsFalseWithIncorrectUrl() {
    Assert.assertTrue(!linkService.isURL(incorrectTestingUrl));
  }
  
  @Test
  public void correctTitleForUrlIsReturned() {
    Assert.assertEquals(correctUrlTitle, Utils.getTitleOfUrl(correctTestingUrl));
  }
  
  @Test
  public void emptyTitleForIncorrectUrlIsReturned() {
    Assert.assertEquals(incorrectUrlTitle, Utils.getTitleOfUrl(incorrectTestingUrl));
  }
  
  @Test
  public void validLinkIsSaved() {
    linkService.newLink(validName, correctTestingUrl, validComment);
    Mockito.verify(linkRepository, Mockito.times(1)).save(Mockito.any(Link.class));
  }

  @Test
  public void existingLinkByUrlPeformsCorrectDatabaseQuery() {
    linkService.existingLinkByUrl(correctTestingUrl);
    Mockito.verify(linkRepository, Mockito.times(1)).findByLink(correctTestingUrl);
  }
  
  @Test
  public void existingLinkByUrlReturnTrueWhenLinkExists() {
    Mockito.when(linkRepository.findByLink(correctTestingUrl)).thenReturn(new Link());
    boolean exists = linkService.existingLinkByUrl(correctTestingUrl);
    assertTrue(exists);
  }
  
  @Test
  public void existingLinkByUrlReturnFalseWhenLinkDoesNotExist() {
    Mockito.when(linkRepository.findByLink(correctTestingUrl)).thenReturn(null);
    boolean exists = linkService.existingLinkByUrl(correctTestingUrl);
    assertFalse(exists);
  }
  
}
