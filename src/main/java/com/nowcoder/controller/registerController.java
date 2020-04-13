package com.nowcoder.controller;

import com.nowcoder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther：侯赛音
 * @Date：2020/4/11 0011
 * @Description: 用于用户注册和登录操作  使用/reglogin路径
 * @Version:1.0
 */
@Controller
public class registerController {
    private final static Logger logger= LoggerFactory.getLogger(registerController.class);
    @Autowired
    UserService userService;

    /*
    注册接口
     */
    @RequestMapping(path = {"/reg/"},method = {RequestMethod.POST})
    public String register(Model model,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam(value = "rememberme", defaultValue = "false") boolean rememberme,
                           HttpServletResponse httpServletResponse){

        try{
            Map<String,String> map= userService.register(username,password);
            if(map.containsKey("ticket")){
                Cookie cookie=new Cookie("ticket",map.get("ticket"));
                cookie.setPath("/");
                httpServletResponse.addCookie(cookie);
                return "redirect:/";
            } else{
                model.addAttribute("msg",map.get("msg"));
                return "login";
            }

        }catch(Exception e){
            logger.error("注册异常"+e.getMessage());
            return "login";
        }
    }

    /*
    登录接口
     */

    @RequestMapping(path = {"/login"},method = {RequestMethod.POST})
    public String login(Model model,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam(value = "rememberme", defaultValue = "false") boolean rememberme,
                           HttpServletResponse httpServletResponse){

        try{
            Map<String,String> map= userService.login(username,password);
            if(map.containsKey("ticket")){
                Cookie cookie=new Cookie("ticket",map.get("ticket"));
                cookie.setPath("/");
                httpServletResponse.addCookie(cookie);
                return "redirect:/";
            } else{
                model.addAttribute("msg",map.get("msg"));
                return "login";
            }

        }catch(Exception e){
            logger.error("登录异常"+e.getMessage());
            return "login";
        }
    }
    @RequestMapping(path = {"/reglogin"},method = {RequestMethod.GET})
    public String registerlogin(Model model) {
        return "login";
    }
}
