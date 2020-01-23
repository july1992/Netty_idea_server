package com.vily.netty2.netty2.bean;

import java.io.Serializable;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2019-11-13
 *  
 **/

public class ChennelMsg implements Serializable {

    private Byte type;//消息类型

    private int id;

    private String state;

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ChennelMsg{" +
                "type=" + type +
                ", id=" + id +
                ", state='" + state + '\'' +
                '}';
    }
}
