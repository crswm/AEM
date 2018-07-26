package com.crs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller//该注解用来识别控制器
@RequestMapping("/wel") //该注解用来控制url书写时，控制器的选择
public class welcomeController {

    @RequestMapping(value = "/welcome.do",method=RequestMethod.GET)
    public String welcome(){
        return "/welcome";
    }
}
