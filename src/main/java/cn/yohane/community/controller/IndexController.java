package cn.yohane.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by SuwaKanan on 2020/06/08
 */

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
