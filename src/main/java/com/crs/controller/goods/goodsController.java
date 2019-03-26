package com.crs.controller.goods;

import com.alibaba.fastjson.JSON;
import com.crs.Util.Global;
import com.crs.entity.classify.b_classify_base;
import com.crs.entity.goods.goods;
import com.crs.entity.prom.User;
import com.crs.service.classify.classify_baseService;
import com.crs.service.goods.goodsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/crs") //该注解用来控制url书写时，控制器的选择
public class goodsController {

    @Autowired
    private goodsService goodsService;

    @Autowired
    private  classify_baseService classify_baseService;

    @RequestMapping(value = "/goods/goods-list.do", method = RequestMethod.GET)
    public String goodsList(HttpServletRequest request) {
        return "/goods-list";
    }

    @ResponseBody
    @RequestMapping(value = "/goods/goodsList.do", method = RequestMethod.POST)
    public JSONObject goodsList(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        List<goods> list = goodsService.findTs("");

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (!list.isEmpty()) {
            resultMap.put("goodsList", list);
            resultMap.put("code", 200);
            System.out.println(JSON.toJSONString(resultMap));
            JSONObject jsonObject = JSONObject.fromObject(JSON.toJSONString(resultMap));

            return jsonObject;
        } else {
            resultMap.put("msg", "获取列表失败!");
            resultMap.put("code", 201);
            JSONObject jsonObject = JSONObject.fromObject(JSON.toJSONString(resultMap));
            return jsonObject;
        }
    }

    /**
     * 设置商品上下架
     *
     * @return ok/fail
     */
    @ResponseBody
    @RequestMapping(value = "/goods/goodsDel.do", method = RequestMethod.POST)
    public String goodsDel(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String is_del = request.getParameter("del");
        String name = request.getParameter("name");

        String msg = "";
        try {
            if (null == id || null == is_del || null == name) {
                return "请求参数有误，请您稍后再试";
            }
            goods goods = goodsService.findTbyid(Integer.parseInt(id));
            // 设置用户是否离职
            boolean w = false;
            if (is_del.equals("1")) {
                w = true;
            }
            if (goods != null) {
                goods.setDel(w);
            }
            boolean a = goodsService.update(goods);
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

        @ResponseBody
        @RequestMapping(value = "/goods/getClassfy.do", method = RequestMethod.POST)
        public com.alibaba.fastjson.JSONObject findAllMenu(HttpServletRequest request, HttpServletResponse response) {
            Map<String, Object> resultMap = new HashMap<String, Object>();
            List<b_classify_base> classifies = classify_baseService.findClassifies(" and  id not in (select pid from b_classify_base  )");
            if (!classifies.isEmpty()) {
                System.out.println(JSON.toJSONString(classifies));
                List<b_classify_base> result = new ArrayList<b_classify_base>();
                for (b_classify_base c : classifies) {
                    //if (c.getLevel() < 3) {
                        result.add(c);
                  //  }
                }
                resultMap.put("classifyList", result);
                com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(resultMap));
                return jsonObject;
            } else {
                resultMap.put("msg", "获取分类表失败!");
                com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(JSON.toJSONString(resultMap));
                return jsonObject;
            }
        }

    /**
     * 设置商家是否废弃
     *
     * @return ok/fail
     */
    @RequestMapping(value = "/goods/editGoods.do", method = RequestMethod.POST)
    @ResponseBody
    public String editGoods(HttpServletRequest request, HttpServletResponse response) {
        String msg = "";
        String id = request.getParameter("id");
        String classfyName = request.getParameter("classfyName");
        String classfy = request.getParameter("classfy");
        String code = request.getParameter("code");

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String discount = request.getParameter("discount");
        String num = request.getParameter("num");
        String unit = request.getParameter("unit");
        String lnglat = request.getParameter("lnglat");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String region = request.getParameter("region");
        String street = request.getParameter("street");
        String streetNumber = request.getParameter("streetNumber");
        String address = request.getParameter("address");

        Object user = request.getSession().getAttribute(Global.LOGIN_USER);
        User u = (User) user;
        try {
            if (!id.equals("") && id != null) {
//                Seller seller = sellerService.findSeller(Integer.parseInt(id));
//                seller.setName(name);
//                seller.setMobile(mobile);
//                seller.setType(type);
//                List<Position> positions = posService.findBySellerId(Integer.parseInt(id));
//                Position p = positions.get(0);
//                p.setAddress(address);
//                p.setLatitude(Double.parseDouble(lnglat.split(",")[1]));
//                p.setLongitude(Double.parseDouble(lnglat.split(",")[0]));
//                p.setProvince(province);
//                p.setCity(city);
//                p.setRegion(region);
//                p.setStreet(street);
//                p.setStreetNumber(streetNumber);
//                boolean a = sellerService.update(seller);
//                boolean b = posService.update(p);
//                if (a && b) {
//                    msg = "ok";
//                } else {
//                    msg = "fail";
//                }
            }else {//新增

//                p.setLatitude(Double.parseDouble(lnglat.split(",")[1]));
//                p.setLongitude(Double.parseDouble(lnglat.split(",")[0]));

                goods goods = new goods();

                goods.setDel(true);
                goods.setCode(Integer.parseInt(code));
                goods.setClassify(classfyName);
                goods.setClassifyCode(classfy);
                goods.setCreateTime(new Date());
                goods.setCreateUser(u.getUserName());
                goods.setDesc("");
                goods.setDiscount(Double.parseDouble(discount));
                goods.setLat((lnglat.split(",")[1]));
                goods.setLon((lnglat.split(",")[0]));
                goods.setName(name);
                goods.setPlace(address);
                goods.setPrice(Double.parseDouble(price));
                goods.setUnit(unit);
                goodsService.add(goods);
                msg = "ok";
            }


        } catch (Exception e) {
            e.printStackTrace();
            msg = "操作异常，请您稍后再试！";
        }
        return msg;
    }
}
