package cn.yohane.community.interceptor;

import cn.yohane.community.mapper.UserMapper;
import cn.yohane.community.model.User;
import cn.yohane.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by SuwaKanan on 2020/06/09
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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

                    // 这里是2020-06-17的新增内容
                    UserExample userExample = new UserExample();
                    userExample.createCriteria().andTokenEqualTo(token);
                    List<User> users = userMapper.selectByExample(userExample);

//                    User user = userMapper.findByToken(token);
                    if (users.size() != 0) {
                        request.getSession().setAttribute("user", users.get(0));
                    }
                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
