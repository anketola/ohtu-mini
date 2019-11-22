package ohtuminiprojekti;

import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class ModifiedHtmlUnitDriver extends HtmlUnitDriver {
    
    @Override
    protected WebClient modifyWebClient(WebClient initialWebClient) {
      WebClient webClient = super.modifyWebClient(initialWebClient);
      webClient.getOptions().setThrowExceptionOnScriptError(false);
      webClient.setCssErrorHandler(new SilentCssErrorHandler());
      return webClient;
    }
}
