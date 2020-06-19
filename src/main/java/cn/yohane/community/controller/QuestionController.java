package cn.yohane.community.controller;

import cn.yohane.community.dto.QuestionDTO;
import cn.yohane.community.mapper.QuestionMapper;
import cn.yohane.community.model.Question;
import cn.yohane.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by SuwaKanan on 2020/06/10
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        // 累加阅读数
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
