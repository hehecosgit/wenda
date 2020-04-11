package com.nowcoder.service;

import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.User;
import com.nowcoder.util.WendaUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

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

    public User getUser(int id){
        return userDAO.selectById(id);
    }
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
        return map;
    }
}