package cn.yohane.community.controller;

import cn.yohane.community.dto.CommentCreateDTO;
import cn.yohane.community.dto.CommentDTO;
import cn.yohane.community.dto.ResultDTO;
import cn.yohane.community.enums.CommentTypeEnum;
import cn.yohane.community.exception.CustomizeErrorCode;
import cn.yohane.community.model.Comment;
import cn.yohane.community.model.User;
import cn.yohane.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {
        // 取出Session
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        // 判断输入的内容是否为空
//        if (commentCreateDTO == null || commentCreateDTO.getContent() == null || commentCreateDTO.getContent() == "") {
        // 这里用了一个comment lang的工具包，详见pom.xml
        if (commentCreateDTO == null || StringUtils.isBlank(commentCreateDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        comment.setCommentCount(0);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }

    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentDTO>> comments(@PathVariable(name = "id") Long id) {
        List<CommentDTO> commentDTOs = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        return ResultDTO.okOf(commentDTOs);
    }
}
