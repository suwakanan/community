package cn.yohane.community.controller;

import cn.yohane.community.mapper.UserMapper;
import cn.yohane.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by SuwaKanan on 2020/06/08
 */

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
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

        return "index";
    }
}
