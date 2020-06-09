package cn.yohane.community.mapper;

import cn.yohane.community.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by SuwaKanan on 2020/06/08
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user (name, account_id, token, gmt_create, gmt_modified, avatar_url) VALUES (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    @Select("SELECT * FROM user WHERE token = #{token}")
    User findByToken(String token);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User findById(@Param("id") Integer id);
}
