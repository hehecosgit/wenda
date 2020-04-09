package com.nowcoder.controller;

import com.nowcoder.model.Question;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.QuestionService;
import com.nowcoder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * @Auther：侯赛音
 * @Date：2020/4/9 0009
 * @Description:
 * @Version:1.0
 */
@Controller
public class HomeController {
    @Autowired
    QuestionService questionService;
    @Autowired
    UserService userService;
    private static final Logger logger= LoggerFactory.getLogger(HomeController.class);
    @RequestMapping(path={"/"},method = {RequestMethod.GET})
    public String index(Model model){
        List<Question> questionList=questionService.getLatestQuestions(0,0,10);

        List<ViewObject> vos=new ArrayList<>();
        for(Question question:questionList){
            ViewObject vo=new ViewObject();
            vo.setObjs("question",question);
            vo.setObjs("user",userService.getUser(question.getUserId()));
            vos.add(vo);
        }

        model.addAttribute("vos",vos);
        return "index";
    }
}
