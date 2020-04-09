package com.nowcoder.service;

import com.nowcoder.dao.QuestionDAO;
import com.nowcoder.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther：侯赛音
 * @Date：2020/4/9 0009
 * @Description:
 * @Version:1.0
 */
@Service
public class QuestionService {
   @Autowired(required=false)
   QuestionDAO questionDAO;
   public List<Question> getLatestQuestions(int userId, int offset, int limit){

       return questionDAO.selectLatestQuestions(userId,offset,limit);
   }

}
