package apap.singidol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
    @RequestMapping("/")
    public String Home(
        Model ctx
    ){
        return "/home/index";

    }
}
