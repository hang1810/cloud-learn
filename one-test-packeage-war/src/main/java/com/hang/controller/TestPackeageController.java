package com.hang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hang
 * @date 2020-09-11 18:37
 */
@RestController
public class TestPackeageController {

    @RequestMapping("/testPackageController")
    public String  testPackageController(){
        return "Hello Hang , Congratulations! Released Successfully ";
    }
}
