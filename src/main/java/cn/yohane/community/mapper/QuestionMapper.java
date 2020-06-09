package cn.yohane.community.mapper;

import cn.yohane.community.model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by SuwaKanan on 2020/06/08
 */
@Mapper
public interface QuestionMapper {
    @Insert("INSERT INTO question (title, description, gmt_create, gmt_modified, creator, tag) VALUES (#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    void create(Question question);

    // 备选方法（和驼峰命名有关）
    // @Results(@Result(property = "modifierTime", column = "modifier_time"))
    @Select("SELECT * FROM question limit #{offset}, #{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM question")
    Integer count();

    @Select("SELECT * FROM question WHERE creator = #{userId} LIMIT #{offset}, #{size}")
    List<Question> listByUserId(@Param("userId") Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    @Select("SELECT COUNT(1) FROM question WHERE creator = #{userId}")
    Integer countByUserId(Integer userId);
}
