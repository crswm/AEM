package com.crs.controller.prom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crs.entity.prom.Menu;
import com.crs.entity.prom.Role;
import com.crs.entity.prom.RoleMenu;
import com.crs.service.prom.MenuService;
import com.crs.service.prom.RoleMenuService;
import com.crs.service.prom.RoleService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller//该注解用来识别控制器
@RequestMapping("/crs") //该注解用来控制url书写时，控制器的选择
public class roleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/admin-role.do", method = RequestMethod.GET)
    public String rolePage() {
        return "/admin-role";
    }


    @RequestMapping(value = "/admin-role-add.do", method = RequestMethod.GET)
    public String roleAddPage() {
        return "/admin-role-add";
    }


    @RequestMapping(value = "/role-prom-list.do", method = RequestMethod.GET)
    public String menuList() {
        return "/role-prom-list";
    }


    @RequestMapping(value = "/user-role-add.do", method = RequestMethod.GET)
    public String userRoleAddPage() {
        return "/user-role-add";
    }

    @ResponseBody
    @RequestMapping(value = "/roleList.do", method = RequestMethod.POST)
    public JSONObject roleList(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        List<Role> list = roleService.findUserRoleRalation();

        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (!list.isEmpty()) {
            resultMap.put("roleList", list);
            System.out.println(JSON.toJSONString(resultMap));
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
            return jsonObject;
        } else {
            resultMap.put("msg", "获取角色列表失败!");
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
            return jsonObject;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/roleAdd.do", method = RequestMethod.POST)
    public JSONObject roleAdd(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:24mm:ss");
        String roleName = request.getParameter("roleName");
        String description = request.getParameter("beizhu");
        if (roleName != "" && roleName != null) {
            Role role = new Role();
            role.setDescription(description);
            role.setRoleName(roleName);
            role.setCreateDate(sdf.parse(sdf.format(new Date())));
            if (checkName(roleName)) {
                roleService.saveRole(role);
                role.setRoleNum(role.getId() + 10000 + "");
                roleService.updateRole(role);
                resultMap.put("result", "100");
            }

        } else {
            resultMap.put("result", "101");
        }
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));

        return jsonObject;
    }

    public boolean checkName(String roleName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleName", roleName);
        List<Role> roles = roleService.findRole(paramMap);
        if (roles.isEmpty()) {
            return true;
        }
        return false;
    }


    @ResponseBody
    @RequestMapping(value = "/getRoleList.do", method = RequestMethod.POST)
    public JSONObject getRoleList(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<Role> roles = roleService.findRole(map);
        if (!roles.isEmpty()) {
            resultMap.put("roleList", roles);
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
            return jsonObject;
        } else {
            resultMap.put("msg", "获取角色列表失败!");
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
            return jsonObject;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addUserRole.do", method = RequestMethod.POST)
    public JSONObject addUserRole(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String userNum = request.getParameter("userNum");
        String roleNum = request.getParameter("roleNum");
        if ((userNum != "" && userNum != null) && (roleNum != "" && roleNum != null)) {
            if (roleService.checkUserRole(userNum, roleNum)) {
                if (roleService.saveUserRole(userNum, roleNum)) {
                    resultMap.put("result", "100");
                } else {
                    resultMap.put("result", "101");
                }
            } else {
                resultMap.put("result", "102");
            }
        } else {
            resultMap.put("result", "103");
        }
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));

        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/findMenuList.do", method = RequestMethod.GET)
    public String findMenus(HttpServletRequest request, HttpServletResponse response) {
        String roleNum = request.getParameter("roleNum");
        List<Menu> list = roleService.getMenuListByroleNum(roleNum);
        System.out.println(list.toString());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Menu> menuList = menuService.findMenu(resultMap);
        for (Menu m : menuList) {
            for (int i = 0; i < list.size(); i++) {
                if ((m.getNum()+"").equals(list.get(i).getNum()+"")) {
                    m.setHasSubMenu(true);
                }
            }
        }
        JSONArray jsonArray = JSONArray.fromObject(menuList);
        String json = jsonArray.toString();
        json = json.replaceAll("num", "id").replaceAll("parentId", "pId").
                replaceAll("name", "name").replaceAll("hasSubMenu", "checked");
        System.out.println(json);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/addRoleMenu.do", method = RequestMethod.POST)
    public JSONObject addRoleMenu(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String roleNum = request.getParameter("roleNum");
        String menuStr = request.getParameter("menuStr");
        List list = roleService.getMenuListByroleNum(roleNum);
        boolean flag = roleMenuService.delete(roleNum);
        if (list.isEmpty()){
            flag =true;
        }
        if (flag){
            if (menuStr.indexOf(",") != -1 && menuStr != "") {
                String[] menu = menuStr.split(",");
                for (int i = 0; i < menu.length; i++) {

                    RoleMenu rm = new RoleMenu();
                    rm.setMenuNum(menu[i]);
                    rm.setRoleNum(roleNum);
                    roleMenuService.saveRole(rm);
                }
            } else {
                    RoleMenu rm = new RoleMenu();
                    rm.setMenuNum(menuStr);
                    rm.setRoleNum(roleNum);
                    roleMenuService.saveRole(rm);
            }
            resultMap.put("result", "100");
        }else {
            resultMap.put("result", "101");
        }
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));

        return jsonObject;
    }

}
