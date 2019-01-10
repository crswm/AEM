package com.crs.controller.seller;

import com.alibaba.fastjson.JSON;
import com.crs.Util.Global;
import com.crs.entity.prom.User;
import com.crs.entity.seller.Position;
import com.crs.entity.seller.Seller;
import com.crs.service.prom.UserService;
import com.crs.service.seller.PosService;
import com.crs.service.seller.SellerService;
import com.sun.org.apache.regexp.internal.RE;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/crs") //该注解用来控制url书写时，控制器的选择
public class sellerController {
    @Autowired
    private SellerService sellerService;

    @Autowired
    private UserService userService;

    @Autowired
    private PosService posService;

    @RequestMapping(value = "/seller/seller-list.do", method = RequestMethod.GET)
    public String sellerList(HttpServletRequest request) {
//        this.logout(request);
        return "/seller-list";
    }

    @RequestMapping(value = "/seller/Myseller-list.do", method = RequestMethod.GET)
    public String MysellerList(HttpServletRequest request) {
//        this.logout(request);
        return "/mseller-list";
    }

    @ResponseBody
    @RequestMapping(value = "/seller/sellerList.do", method = RequestMethod.POST)
    public JSONObject sellerList(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        List<Seller> list = sellerService.findSellers();

        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                User user = userService.getUserById(Integer.parseInt(list.get(i).getUserId()));
                list.get(i).setUserName(user.getUserName());
            }
            resultMap.put("sellerList", list);
            resultMap.put("code", 200);
            System.out.println(JSON.toJSONString(resultMap));
            JSONObject jsonObject = JSONObject.fromObject(JSON.toJSONString(resultMap));

            return jsonObject;
        } else {
            resultMap.put("msg", "获取商家列表失败!");
            resultMap.put("code", 201);
            JSONObject jsonObject = JSONObject.fromObject(JSON.toJSONString(resultMap));
            return jsonObject;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/seller/mSellerList.do", method = RequestMethod.POST)
    public JSONObject msellerList(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Object user = request.getSession().getAttribute(Global.LOGIN_USER);
        User u = (User) user;

        List<Seller> list = sellerService.findByUserId(u.getId());

        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                //User user1 = userService.getUserById(Integer.parseInt(list.get(i).getUserId()));
                list.get(i).setUserName(u.getUserName());
            }
            resultMap.put("sellerList", list);
            resultMap.put("code", 200);
            System.out.println(JSON.toJSONString(resultMap));
            JSONObject jsonObject = JSONObject.fromObject(JSON.toJSONString(resultMap));

            return jsonObject;
        } else {
            resultMap.put("msg", "获取商家列表失败!");
            resultMap.put("code", 201);
            JSONObject jsonObject = JSONObject.fromObject(JSON.toJSONString(resultMap));
            return jsonObject;
        }
    }

    /**
     * 设置商家是否营业
     *
     * @return ok/fail
     */
    @RequestMapping(value = "/seller/setIsWork.do", method = RequestMethod.POST)
    @ResponseBody
    public String setIsWork(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String iswork = request.getParameter("is_work");
        String name = request.getParameter("name");

        String msg = "";
        try {
            if (null == id || null == iswork || null == name) {
                return "请求参数有误，请您稍后再试";
            }
            Seller seller = sellerService.findSeller(Integer.parseInt(id));
            // 设置用户是否离职
            boolean w = false;
            if (iswork.equals("1")) {
                w = true;
            }
            if (seller != null) {
                seller.setIs_work(w);
            }
            boolean a = sellerService.update(seller);
            if (a) {
                msg = "ok";
            } else {
                msg = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "操作异常，请您稍后再试！";
        }
        return msg;
    }

    /**
     * 设置商家是否废弃
     *
     * @return ok/fail
     */
    @RequestMapping(value = "/seller/setIsDel.do", method = RequestMethod.POST)
    @ResponseBody
    public String setIsDel(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String is_del = request.getParameter("is_del");
        String name = request.getParameter("name");

        String msg = "";
        try {
            if (null == id || null == is_del || null == name) {
                return "请求参数有误，请您稍后再试";
            }
            Seller seller = sellerService.findSeller(Integer.parseInt(id));
            // 设置用户是否离职
            boolean w = false;
            if (is_del.equals("1")) {
                w = true;
            }
            if (seller != null) {
                seller.setIs_del(w);
            }
            boolean a = sellerService.update(seller);
            if (a) {
                msg = "ok";
            } else {
                msg = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "操作异常，请您稍后再试！";
        }
        return msg;
    }


    /**
     * 设置商家是否废弃
     *
     * @return ok/fail
     */
    @RequestMapping(value = "/seller/editSeller.do", method = RequestMethod.POST)
    @ResponseBody
    public String editSeller(HttpServletRequest request, HttpServletResponse response) {
        String msg = "";
        String id = request.getParameter("id");
//        String is_del  = request.getParameter("is_del");
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        String type = request.getParameter("type");
        String address = request.getParameter("address");
        String nearestPOI = request.getParameter("nearestPOI");
        String lnglat = request.getParameter("lnglat");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String street = request.getParameter("street");
        String streetNumber = request.getParameter("streetNumber");

        Seller seller = sellerService.findSeller(Integer.parseInt(id));
        seller.setName(name);
        seller.setMobile(mobile);
        seller.setType(type);
        List<Position> positions = posService.findBySellerId(Integer.parseInt(id));
        Position p = positions.get(0);
        p.setAddress(address);
        p.setLatitude(Double.parseDouble(lnglat.split(",")[1]));
        p.setLongitude(Double.parseDouble(lnglat.split(",")[0]));
        p.setProvince(province);
        p.setCity(city);
        p.setRegion(region);
        p.setStreet(street);
        p.setStreetNumber(streetNumber);


        try {
            boolean a = sellerService.update(seller);
            boolean b = posService.update(p);
            if (a && b) {
                msg = "ok";
            }else {
                msg = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "操作异常，请您稍后再试！";
        }
        return msg;
    }


    /**
     * 获取商家信息
     */
    @RequestMapping(value = "/seller/getSeller.do", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getSeller(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Seller seller = sellerService.findSeller(Integer.parseInt(id));
        Position p = posService.findBySellerId(Integer.parseInt(id)).get(0);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (seller != null) {
            resultMap.put("seller", seller);
            resultMap.put("p",p);
            resultMap.put("msg", "ok");
        } else {
            resultMap.put("msg", "fail");
        }
        JSONObject jsonObject = JSONObject.fromObject(JSON.toJSONString(resultMap));
        return jsonObject;
    }


}
