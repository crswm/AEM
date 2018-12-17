package com.crs.controller.prom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crs.Util.Global;
import com.crs.entity.prom.Menu;
import com.crs.entity.prom.Role;
import com.crs.entity.prom.User;
import com.crs.service.prom.MenuService;
import com.crs.service.prom.RoleService;
import com.crs.service.prom.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller//该注解用来识别控制器
//@RequestMapping("/user") //该注解用来控制url书写时，控制器的选择
public class loginController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
//        this.logout(request);
        return "/login";
    }


    @ResponseBody
    @RequestMapping(value = "/loginCheck.do", method = RequestMethod.POST)
    public JSONObject userLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        User user = new User();
        user.setUserName(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));
        User currentUser = userService.queryUser(user);
        if (currentUser != null) {
            Set<Menu> menus = getMenus(currentUser);
            List<Menu> menuList = new ArrayList<Menu>();
            menuList.addAll(menus);
            currentUser.setProm(menuList);
            resultMap.put("id", currentUser.getUserNum());
            request.getSession().setAttribute(Global.LOGIN_USER, currentUser);
            resultMap.put("result", "100");
        } else {
            resultMap.put("result", "101");
        }
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(resultMap));
        return itemJSONObj;
    }

    @RequestMapping(value = "/crs/index.do", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }


    @RequestMapping(value = "/crs/home.do", method = RequestMethod.GET)
    public String home() {
        return "/home";
    }

    @ResponseBody
    @RequestMapping(value = "/crs/logout.do", method = RequestMethod.POST)
    public JSONObject logout(HttpServletRequest request) {
        Object user = request.getSession().getAttribute(Global.LOGIN_USER);
        if (user != null) {
            System.out.println("用户 : " + user.toString() + "退出登录");
            request.getSession().removeAttribute(Global.LOGIN_USER);
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("result", "100");
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
            return jsonObject;
        }
        return null;
    }


    @ResponseBody
    @RequestMapping(value = "/findUser.do", method = RequestMethod.POST)
    public JSONObject findUser(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Object user = request.getSession().getAttribute(Global.LOGIN_USER);
        List<Object> resultList = new ArrayList<Object>();
        if (user!=null){
            resultList.add(user);
            resultMap.put("result", resultList);
        }else {
            resultMap.put("result", "exist");
        }
        System.out.println(JSON.toJSONString(resultMap));
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));

        return jsonObject;

    }
    @ResponseBody
    @RequestMapping(value = "/crs/getMyMenuList.do", method = RequestMethod.POST)
    public JSONObject getMyMenuList(HttpServletRequest request, HttpServletResponse response) {
        Object user = request.getSession().getAttribute(Global.LOGIN_USER);
        User u = (User) user;
        Set<Menu> menuSet = this.getMenus(u);
        List<Menu> resultList = new ArrayList<Menu>();
        for (Menu m : menuSet) {
            if (m.getLevel() < 3) {
                resultList.add(m);
            }
        }
        Collections.sort(resultList, new Comparator<Menu>(){
            public int compare(Menu p1, Menu p2) {
                //按照menuID进行升序排列
                if(p1.getId() > p2.getId()){
                    return 1;
                }
                if(p1.getId() == p2.getId()){
                    return 0;
                }
                return -1;
            }
        });

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", resultList);
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
        System.out.println(JSON.toJSONString(resultMap));
        return jsonObject;
    }


    public Set<Menu> getMenus(User user) {
        List<Menu> menus = new ArrayList<Menu>();
        List<Role> roles = userService.findRoles(user.getUserNum());
        for (int i = 0; i < roles.size(); i++) {
            List<Menu> menuList = roleService.getMenuListByroleNum(roles.get(i).getRoleNum());
            menus.addAll(menuList);
        }
        Set<Menu> menuSet = new HashSet<Menu>();
        for (int i = 0; i < menus.size(); i++) {
            menuSet.add(menus.get(i));
        }
        System.out.println(menuSet.toString());
        return menuSet;
    }

}