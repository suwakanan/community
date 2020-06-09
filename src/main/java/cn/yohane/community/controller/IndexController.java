package cn.yohane.community.controller;

import cn.yohane.community.dto.PaginationDTO;
import cn.yohane.community.dto.QuestionDTO;
import cn.yohane.community.mapper.QuestionMapper;
import cn.yohane.community.mapper.UserMapper;
import cn.yohane.community.model.Question;
import cn.yohane.community.model.User;
import cn.yohane.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by SuwaKanan on 2020/06/08
 */

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    // 这边代码可能存在问题

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        // 这是2020.06.09新增内容
        /*if (request.getCookies() == null) {
            return "index";
        }*/
        // 这里如果第一次访问不是首页的话，就没有登录态
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                /**
                 * 引号token是key，name也是key
                 * name是判断键的名字是不是“token”，value获得键名为“token”的值
                 * 因此判断如果name等于“token”字符串，也就是名字跟数据库中token名字相同，那么它的值就是token的value
                 * cookie是以键值对保存的，getName是获取到cookie的键，getValue是获取到对应的值
                 */
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        // 查询数据库中的文章
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);

        return "index";
    }
}
