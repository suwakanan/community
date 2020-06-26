package cn.yohane.community.controller;

import cn.yohane.community.dto.PaginationDTO;
import cn.yohane.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by SuwaKanan on 2020/06/08
 */

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    // 这边代码可能存在问题

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        // 这是2020.06.09新增内容
        /*if (request.getCookies() == null) {
            return "index";
        }*/
        // 这里如果第一次访问不是首页的话，就没有登录态
        // 查询数据库中的文章
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);

        return "index";
    }
}
