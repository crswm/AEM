package com.crs.controller;

import com.crs.entity.User;
import com.crs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller//该注解用来识别控制器
//@RequestMapping("/user") //该注解用来控制url书写时，控制器的选择
public class HelloController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index.do",method=RequestMethod.GET)
    public String index(){
        return "/index";
    }

//    @RequestMapping(value = "/welcome.do",method=RequestMethod.GET)
//    public String welcome(){
//        return "/welcome";
//    }

    @ResponseBody
    @RequestMapping(value = "/login",method=RequestMethod.GET)
    public Map<String, Object> userLogin(HttpServletRequest request) throws Exception{
        Map<String, Object> resultMap=new HashMap<String, Object>();
        User user=new User();
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        User currentUser=userService.queryUser(user);
        if (currentUser!=null) {
            resultMap.put("id", currentUser.getId());
            resultMap.put("res", "yes");
        }else{
            resultMap.put("res", "no");
        }
        return resultMap;
    }
}