package cn.yohane.community.mapper;

import cn.yohane.community.model.Question;
import cn.yohane.community.model.QuestionExample;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by SuwaKanan on 2020/06/08
 */
public interface QuestionExtMapper {

    int incView(Question record);
    int incCommentCount(Question record);
}
