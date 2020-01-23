package com.vily.netty2.netty2.chat;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2019-11-15
 *  
 **/

@Entity
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class ChatMsg {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    private String send_user_id;
    private String accept_user_id;
    private String msg;
    private int sign_flag;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_time;


    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date update_time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSend_user_id() {
        return send_user_id;
    }

    public void setSend_user_id(String send_user_id) {
        this.send_user_id = send_user_id;
    }

    public String getAccept_user_id() {
        return accept_user_id;
    }

    public void setAccept_user_id(String accept_user_id) {
        this.accept_user_id = accept_user_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getSign_flag() {
        return sign_flag;
    }

    public void setSign_flag(int sign_flag) {
        this.sign_flag = sign_flag;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
