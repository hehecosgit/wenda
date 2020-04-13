package com.nowcoder.dao;

import com.nowcoder.model.LoginTicket;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @Auther：侯赛音
 * @Date：2020/4/13 0013
 * @Description:
 * @Version:1.0
 */
@Mapper
public interface LoginTicketDAO {
    public String TABLE_NAME=" login_ticket ";
    public String INSERT_FIELDS=" user_id, ticket, expired, status ";
    public String SELECT_FIELDS=" id, "+INSERT_FIELDS;
    @Insert({"insert into ",TABLE_NAME ," (",INSERT_FIELDS,") values ( #{userId}, #{ticket}, #{expired}, #{status} )"})
    public int addLoginTicket(LoginTicket loginTicket);

    @Select({"select ",SELECT_FIELDS," from ",TABLE_NAME," where ticket=#{ticket}"})
    public LoginTicket selectLoginTicket(String ticket);

    @Update({"update ",TABLE_NAME," set status=#{status} where ticket=#{ticket}"})
    public void updateLoginTicket(String ticket);
}
