package ohtuminiprojekti;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controllers {

    @GetMapping("/")
    @ResponseBody
    public String indexRoot() {
        return "yay";
    }
}