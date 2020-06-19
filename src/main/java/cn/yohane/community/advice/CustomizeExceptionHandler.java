package cn.yohane.community.advice;

import cn.yohane.community.dto.ResultDTO;
import cn.yohane.community.exception.CustomizeErrorCode;
import cn.yohane.community.exception.CustomizeException;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SuwaKanan on 2020/06/19
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)

    ModelAndView handle(HttpServletRequest request, Throwable e, Model model, HttpServletResponse response) {
//        HttpStatus status = getStatus(request);
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            ResultDTO resultDTO = null;
            // 返回JSON
            if (e instanceof CustomizeException) {
                resultDTO =  ResultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO =  ResultDTO.errorOf(CustomizeErrorCode.SYSTEM_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {

            }
            return null;

        } else {
            // 错误页面跳转
            if (e instanceof CustomizeException) {
                model.addAttribute("message", CustomizeErrorCode.SYSTEM_ERROR.getMessage());
            } else {
                model.addAttribute("message", "奇怪的错误发生了T_T");
            }
            return new ModelAndView("error");
        }

    }

}
