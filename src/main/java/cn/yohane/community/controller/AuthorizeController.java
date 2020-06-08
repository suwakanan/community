package cn.yohane.community.controller;

import cn.yohane.community.dto.AccessTokenDTO;
import cn.yohane.community.dto.GithubUser;
import cn.yohane.community.mapper.UserMapper;
import cn.yohane.community.model.User;
import cn.yohane.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by SuwaKanan on 2020/06/08
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    // 从application.properties取值
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    // 用来接收github登录返回的code
    // OkHttp：https://square.github.io/okhttp/
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));// 把int强转成string
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            // 登录后如果获取到token就将数据写入到数据库中
            userMapper.insert(user);
            // 通过response写入cookie
            response.addCookie(new Cookie("token", token));

            return "redirect:/";// 重定向
        } else {
            // 登录失败，重新登录
            return "redirect:/";
        }
        //return "index";
    }
}
