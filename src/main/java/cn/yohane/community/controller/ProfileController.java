package cn.yohane.community.controller;

import cn.yohane.community.dto.PaginationDTO;
import cn.yohane.community.mapper.UserMapper;
import cn.yohane.community.model.User;
import cn.yohane.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by SuwaKanan on 2020/06/09
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    // {action}可以动态的切换路径
    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size) {

        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName","最新回复");
        }

        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}
