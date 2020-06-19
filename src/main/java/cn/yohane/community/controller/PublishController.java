package cn.yohane.community.controller;

import cn.yohane.community.dto.QuestionDTO;
import cn.yohane.community.mapper.QuestionMapper;
import cn.yohane.community.model.Question;
import cn.yohane.community.model.User;
import cn.yohane.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SuwaKanan on 2020/06/08
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;


    // 修改
    // 注意这里如果通过链接直接传值可以被非法修改
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model) {

        QuestionDTO question = questionService.getById(id);

        // 留住已经填充的内容
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        return "publish";
    }


    @GetMapping("/publish")// 如果是get请求就渲染页面
    public String publish(HttpServletRequest request, Model model) {
        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "redirect:/";
        }
        return "publish";
    }

    @PostMapping("/publish")// 如果是post请求则执行
    public String doPublish(@RequestParam(value = "title", required = false) String title,
                            @RequestParam(value = "description", required = false) String description,
                            @RequestParam(value = "tag", required = false) String tag,
                            @RequestParam(value = "id", required = false) Long id,
                            HttpServletRequest request,
                            Model model) {


        // 留住已经填充的内容
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        // 判断是否为空（建议前端也要判断，但前端或许可以绕过）
        if (title == null || title == ""){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }
        if (description == null || description == ""){
            model.addAttribute("error", "内容不能为空");
            return "publish";
        }
        if (tag == null || tag == ""){
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        // 这里也放过去QuestionService了
//        question.setGmtCreate(System.currentTimeMillis());
//        question.setGmtModified(question.getGmtCreate());
        question.setId(id);
        questionService.createOrUpdate(question);
//        questionMapper.create(question);
        return "redirect:/";
    }
}
