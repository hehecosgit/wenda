package com.nowcoder.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


/**
 * @Auther：侯赛音
 * @Date：2020/4/9 0009
 * @Description:
 * @Version:1.0
 */
@Controller
public class HomeController {

    private static final Logger logger= LoggerFactory.getLogger(HomeController.class);
    @RequestMapping(path={"/"},method = {RequestMethod.GET})
    public String index(HttpSession httpSession){
        return "index";
    }
}
