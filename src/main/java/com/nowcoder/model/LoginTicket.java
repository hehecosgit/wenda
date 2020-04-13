package com.nowcoder.model;

import java.util.Date;

/**
 * @Auther：侯赛音
 * @Date：2020/4/13 0013
 * @Description:
 * @Version:1.0
 */
public class LoginTicket {
    private int userId;
    private int status;
    private String ticket;
    private Date expired;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }
}
