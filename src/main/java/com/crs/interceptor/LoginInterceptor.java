package com.crs.interceptor;

import com.crs.Util.Global;
import com.crs.entity.prom.Menu;
import com.crs.entity.prom.User;
import com.crs.service.prom.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private MenuService menuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute(Global.LOGIN_USER);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Menu> allMeus = menuService.findMenu(resultMap);
        Map<String, Set<Menu>> allm = new HashMap<String, Set<Menu>>();
        for (Menu m : allMeus) {
            Set<Menu> set = new HashSet<Menu>();
            set.add(m);
            allm.put(m.getUrl(), set);
        }
        System.out.println(allMeus.toString());
        if (user == null) {
            System.out.println("尚未登录，调到登录页面");
            response.sendRedirect("/login.do");
            return false;
        }
        User u = (User) user;
        System.out.println(u.getUserNum());
        System.out.println("用户 : " + user.toString() + "已登录");
        List<Menu> prom = u.getProm();
        String url = request.getRequestURI();
        int pos = url.indexOf("?");
        String matchUrl = url;
        if (pos != -1) {
            matchUrl = url.substring(0, pos);
        }
        Set<Menu> s = allm.get(matchUrl);
        if (s == null || s.size() < 1) {//无权限限制的资源，过
            return true;
        } else {
            for (Menu m1 : prom) {
                if (s.toString().contains(m1.toString())) {//匹配权限
                    return true;
                }
            }
            if (request.getHeader("X-Requested-With") == null) {//非ajax请求
                return false;
            } else {
                response.getWriter().write("{\"success\":false,\"message\":\"没有操作权限\"}");
            }
            return false;
        }
        //return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
