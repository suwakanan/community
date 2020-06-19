package cn.yohane.community.controller;

import cn.yohane.community.dto.CommentDTO;
import cn.yohane.community.dto.ResultDTO;
import cn.yohane.community.exception.CustomizeErrorCode;
import cn.yohane.community.mapper.CommentMapper;
import cn.yohane.community.model.Comment;
import cn.yohane.community.model.User;
import cn.yohane.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SuwaKanan on 2020/06/19
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 把评论加到数据库中
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        // 取出Session
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
