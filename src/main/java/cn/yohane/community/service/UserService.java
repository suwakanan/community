package cn.yohane.community.service;

import cn.yohane.community.mapper.UserMapper;
import cn.yohane.community.model.User;
import cn.yohane.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by SuwaKanan on 2020/06/10
 */
@Service
public class UserService {
    
    @Autowired
    private UserMapper userMapper;


    
    public void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            // 插入数据库
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
        } else {
            // 更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setGmtModified(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria().andAccountIdEqualTo(dbUser.getAccountId());
            userMapper.updateByExampleSelective(updateUser, example);
        }
    }
}
