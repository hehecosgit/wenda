package com.nowcoder;

import com.nowcoder.dao.QuestionDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.Question;
import com.nowcoder.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WendaApplication.class)
@Sql("/init-schema.sql")
public class InitDataBaseTests {
    @Autowired(required=false)
	UserDAO userDAO;
	@Autowired(required=false)
	QuestionDAO questionDAO;
	@Test
	public void initDataBase() {
		Random ran=new Random();
		for(int i=0;i<11;i++){
			User user=new User();
			user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", ran.nextInt(1000)));
			user.setName(String.format("User%d",i));
			user.setPassword("");
			user.setSalt("");
			userDAO.addUser(user);

			Question question =new Question();
			question.setCommentCount(i);
			question.setContent("iiiiii"+i);
			Date date=new Date();
			date.setTime(date.getTime()+1000*3600*i);
			question.setCreatedDate(date);
			question.setTitle("Title"+i);
			question.setUserId(i);
			questionDAO.addQuestion(question);

		}
		System.out.print(questionDAO.selectLatestQuestions(2,0,10));

	}

}
