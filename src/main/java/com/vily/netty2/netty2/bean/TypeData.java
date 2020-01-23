package com.vily.netty2.netty2.bean;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2019-11-13
 *  
 **/

public interface TypeData {


    //模式
    byte PING = 1;

    byte PONG = 2;

    byte CUSTOME = 3;

    Byte OFFLINE = 4;

    Byte ONLINE = 5;

    //*******************************


    byte CONNECT_SERVER = 100;

    byte MESSAGE = 101;
    byte PLAN = 102;
    Byte CONNECT_SUCCESS = 103;

    Byte EDUCATION = 104;
    Byte SYSTEM = 105;
}
