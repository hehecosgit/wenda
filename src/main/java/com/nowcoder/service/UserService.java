package com.nowcoder.service;

import com.nowcoder.dao.LoginTicketDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.model.User;
import com.nowcoder.util.WendaUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther：侯赛音
 * @Date：2020/4/9 0009
 * @Description:
 * @Version:1.0
 */
@Service
public class UserService {
    @Autowired(required=false)
    UserDAO userDAO;

    @Autowired(required = false)
    LoginTicketDAO loginTicketDAO;

    public User getUser(int id){
        return userDAO.selectById(id);
    }
    /*
    用户注册功能模块
     */
    public Map<String,String> register(String name,String password){
        Map<String,String> map=new HashMap<>();
        if(StringUtils.isBlank(name)){
            map.put("msg","用户名不可为空");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("msg","密码不可为空");
            return map;
        }
        User finduser=userDAO.selectByName(name);
        if(finduser!=null){
            map.put("msg","用户名已存在");
            return map;
        }
        User user=new User();
        user.setName(name);
        String salt= UUID.randomUUID().toString().substring(0,5);
        user.setSalt(salt);
        Random ran=new Random();
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", ran.nextInt(1000)));
        user.setPassword(WendaUtil.MD5(password+user.getSalt()));
        userDAO.addUser(user);
        LoginTicket loginTicket=addTicket(user.getId());
        loginTicketDAO.addLoginTicket(loginTicket);
        map.put("ticket",loginTicket.getTicket());
        return map;
    }

    /*
    用户登录功能模块
     */
    public Map<String,String> login(String name,String password){
        Map<String,String> map=new HashMap<>();
        if(StringUtils.isBlank(name)){
            map.put("msg","用户名不可为空");
            return map;
        }
        if(StringUtils.isBlank(password)){
            map.put("msg","密码不可为空");
            return map;
        }
        User finduser=userDAO.selectByName(name);
        if(finduser==null){
            map.put("msg","用户名不存在");
            return map;
        }
        if(!finduser.getPassword().equals(WendaUtil.MD5(password+finduser.getSalt()))){
            map.put("msg","密码错误");
            return map;
        }
        LoginTicket loginTicket=addTicket(finduser.getId());
        loginTicketDAO.addLoginTicket(loginTicket);
        map.put("ticket",loginTicket.getTicket());
        return map;
    }

    /*
    为验证成功的用户创建一个LoginTicket对象
     */
    public LoginTicket addTicket(int userId){
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setUserId(userId);
        loginTicket.setStatus(0);
        loginTicket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        Date date=new Date();
        date.setTime(1000*3600*5+date.getTime());
        loginTicket.setExpired(date);
        return loginTicket;
    }



}