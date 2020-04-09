package com.nowcoder.service;

import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
