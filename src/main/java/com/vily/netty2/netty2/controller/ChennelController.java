package com.vily.netty2.netty2.controller;

import com.vily.netty2.netty2.service.ChennelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  * description : 
 *  * Author : Vily
 *  * Date : 2020-01-23
 *  
 **/
@CrossOrigin
@RestController
@RequestMapping("/home")
public class ChennelController {

    @Autowired
    ChennelService mService;

    @GetMapping("/test")
    public void test(){

        System.out.println("-----");

        mService.test();
    }


}
