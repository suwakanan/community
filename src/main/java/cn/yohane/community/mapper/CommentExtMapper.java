package cn.yohane.community.mapper;

import cn.yohane.community.model.Comment;
import cn.yohane.community.model.CommentExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment record);
}