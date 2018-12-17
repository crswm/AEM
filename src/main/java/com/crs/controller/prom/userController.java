package com.crs.controller.prom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crs.entity.prom.User;
import com.crs.service.prom.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//该注解用来识别控制器
@RequestMapping("/crs") //该注解用来控制url书写时，控制器的选择
public class userController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/member-list.do", method = RequestMethod.GET)
    public String userListPage() {
        return "/member-list";
    }

    @RequestMapping(value = "/member-add.do", method = RequestMethod.GET)
    public String userAddPage() {
        return "/member-add";
    }


    @RequestMapping(value = "/member-show.do", method = RequestMethod.GET)
    public String userShowPage() {
        return "/member-show";
    }



    @ResponseBody
    @RequestMapping(value = "/checkName.do", method = RequestMethod.POST)
    public JSONObject checkName(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String username = request.getParameter("username");
        User user =  userService.findUserByUserName(username);
        if (user==null){
            resultMap.put("result", "ok");
        }else {
            resultMap.put("result", "exist");
        }
        System.out.println(JSON.toJSONString(resultMap));
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));

        return jsonObject;

    }

    @ResponseBody
    @RequestMapping(value = "/addUser.do", method = RequestMethod.POST)
    public JSONObject userAdd(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:24mm:ss");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String mobile = request.getParameter("mobile");
        String email = request.getParameter("email");
//        String city = request.getParameter("city");
        String beizhu = request.getParameter("beizhu");
        if (username!=""&&username!=null&&password!=""&&password!=null){
            User user = new User();
            user.setUserName(username);
            user.setPassword(password);
            user.setCreateDate(sdf.parse(sdf.format(new Date())));
            user.setEmail(email);
            user.setTel(mobile);
            user.setValid("1");
            if (this.checkName(username)){
                userService.addUser(user);
                user.setUserNum(1000+user.getId()+"");
                userService.updateUser(user);
                resultMap.put("result", "100");
            }
        }else {
            resultMap.put("result", "101");
        }
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
        return jsonObject;

        }

    @ResponseBody
    @RequestMapping(value = "/UserList.do", method = RequestMethod.POST)
    public JSONObject UserList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<User> userList=  userService.findUserRoles();
        if (!userList.isEmpty()){
            System.out.println(JSON.toJSONString(userList));
            resultMap.put("userList",userList);
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
            return jsonObject;
        }else {
            resultMap.put("msg","获取用户列表失败!");
            JSONObject jsonObject =JSONObject.parseObject(JSON.toJSONString(resultMap));
            return jsonObject;
        }
    }
    public boolean checkName(String username){
        User user =  userService.findUserByUserName(username);
        if (user==null){
            return true;
        }
        return false;
    }
}
