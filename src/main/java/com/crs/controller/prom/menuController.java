package com.crs.controller.prom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crs.entity.prom.Menu;
import com.crs.service.prom.MenuService;
import com.crs.service.prom.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/crs")
public class menuController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping(value = "/menu-list.do", method = RequestMethod.GET)
    public String menuPage() {
        return "/menu-list";
    }

    @RequestMapping(value = "/menu-add.do", method = RequestMethod.GET)
    public String menuAddPage() {
        return "/menu-add";
    }

    @RequestMapping(value = "/menu-update.do", method = RequestMethod.GET)
    public String menuUpdatePage() {
        return "/menu-update";
    }

    @ResponseBody
    @RequestMapping(value = "/findMenuList.do", method = RequestMethod.POST)
    public JSONObject findMenus(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Menu> menuList = menuService.findMenu(resultMap);
        if (!menuList.isEmpty()) {
            resultMap.put("menuList", menuList);
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
            return jsonObject;
        } else {
            resultMap.put("msg", "获取角色列表失败!");
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
            return jsonObject;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/menu/updateMenu.do", method = RequestMethod.POST)
    public JSONObject updateMenu(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String menuId = request.getParameter("menuId");
        String menuName = request.getParameter("menuName");
        String px = request.getParameter("px");
        String url = request.getParameter("menuUrl");
        String pid = request.getParameter("pid");
        String descr = request.getParameter("descr");
        if (menuId != "" && menuId != null) {
            Menu m = menuService.findMenuById(Integer.parseInt(menuId));
            m.setId(Integer.parseInt(menuId));
            m.setMenuOrder(px);
            m.setUrl(url);

            m.setParentId(Integer.parseInt(pid));
            m.setDescrition(descr);
            if (checkName(menuName) || m.getName().equals(menuName)) {
                m.setName(menuName);
                menuService.updateMenu(m);
                resultMap.put("result", "100");
            } else {
                resultMap.put("result", "102");
            }
        } else {
            resultMap.put("result", "101");
        }
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/menu/findMenuById.do", method = RequestMethod.POST)
    public JSONObject findMenuById(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String menuId = request.getParameter("id");
        if (menuId != "" && menuId != null) {
            Menu m = menuService.findMenuById(Integer.parseInt(menuId));
            System.out.println("-----------------" + m.getParentId());
            resultMap.put("result", m);
        } else {
            resultMap.put("result", "101");
        }
        //{hasSubMenu: false, level: 1, num: 1, name: "权限管理", id: 11, url…}
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/menu/deleteMenuById.do", method = RequestMethod.POST)
    public JSONObject deleteMenuById(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String menuId = request.getParameter("id");
        if (menuId != "" && menuId != null) {
            Menu m = menuService.findMenuById(Integer.parseInt(menuId));
            int i = roleMenuService.deleteBymenuNum(m.getNum() + "");
            boolean a = false;
            if (i > 0) {
                a = menuService.deleteById(m);
            }
            if (a) {
                resultMap.put("result", "100");
            } else {
                resultMap.put("result", "102");
            }
        } else {
            resultMap.put("result", "101");
        }
        //{hasSubMenu: false, level: 1, num: 1, name: "权限管理", id: 11, url…}
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
        return jsonObject;
    }


    @ResponseBody
    @RequestMapping(value = "/menu/addMenu.do", method = RequestMethod.POST)
    public JSONObject MenuAdd(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String menuName = request.getParameter("menuName");
        String px = request.getParameter("px");
        String url = request.getParameter("menuUrl");
        String pid = request.getParameter("pid");
        String descr = request.getParameter("descr");
        if (menuName != "" && menuName != null) {
            Menu m = new Menu();
            m.setName(menuName);
            m.setMenuOrder(px);
            m.setUrl(url);
            if (pid == "" || pid == null) {
                m.setParentId(0);
                m.setLevel(1);
            } else {
                m.setParentId(Integer.parseInt(pid));
                m.setLevel(menuService.findLevelByNum(Integer.parseInt(pid)) + 1);
            }
            m.setDescrition(descr);
            if (this.checkName(menuName)) {
                menuService.save(m);
                m.setNum(10040 + m.getId());
                menuService.updateNum(m);
                resultMap.put("result", "100");
            }
        } else {
            resultMap.put("result", "101");
        }
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/menu/checkName.do", method = RequestMethod.POST)
    public JSONObject checkName(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String menuName = request.getParameter("menuName");
        boolean flag = checkName(menuName);
        if (flag) {
            resultMap.put("result", "ok");
        } else {
            resultMap.put("result", "exist");
        }
        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));

        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/menu/findMenuList.do", method = RequestMethod.POST)
    public JSONObject findAllMenu(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Menu> menuList = menuService.findMenu(resultMap);
        if (!menuList.isEmpty()) {
            System.out.println(JSON.toJSONString(menuList));
            List<Menu> result = new ArrayList<Menu>();
            for (Menu m : menuList) {
                if (m.getLevel() < 3) {
                    result.add(m);
                }
            }
            resultMap.put("menuList", result);
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
            return jsonObject;
        } else {
            resultMap.put("msg", "获取菜单表失败!");
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(resultMap));
            return jsonObject;
        }
    }

    public boolean checkName(String name) {
        Menu m = menuService.findMenuByName(name);
        if (m == null) {
            return true;
        }
        return false;
    }

}
