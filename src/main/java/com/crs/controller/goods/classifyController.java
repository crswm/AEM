package com.crs.controller.goods;

import com.alibaba.fastjson.JSON;
import com.crs.Util.Global;
import com.crs.entity.classify.b_classify_base;
import com.crs.entity.prom.User;
import com.crs.service.classify.classify_baseService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/crs") //该注解用来控制url书写时，控制器的选择
public class classifyController {
    @Autowired
    private classify_baseService classify_baseService;

    @RequestMapping(value = "/goods/classify-list.do", method = RequestMethod.GET)
    public String classifyList(HttpServletRequest request) {
        return "/classify-list";
    }


    /**
     * 获取商家信息
     */
    @RequestMapping(value = "/goods/getClass.do", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getClass(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        b_classify_base classify = classify_baseService.findTbyid(Integer.parseInt(id));
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (classify != null) {
            resultMap.put("classify", classify);
            resultMap.put("msg", "ok");
        } else {
            resultMap.put("msg", "fail");
        }
        JSONObject jsonObject = JSONObject.fromObject(JSON.toJSONString(resultMap));
        return jsonObject;
    }

    @ResponseBody
    @RequestMapping(value = "/goods/classifyList.do", method = RequestMethod.POST)
    public JSONObject classifyList(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        List<b_classify_base> list = classify_baseService.findClassifies("");

        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (!list.isEmpty()) {
            resultMap.put("classifyList", list);
            //resultMap.put("code", 200);
            System.out.println(JSON.toJSONString(resultMap));
            JSONObject jsonObject = JSONObject.fromObject(JSON.toJSONString(resultMap));

            return jsonObject;
        } else {
            resultMap.put("msg", "获取类目列表失败!");
            //resultMap.put("code", 201);
            JSONObject jsonObject = JSONObject.fromObject(JSON.toJSONString(resultMap));
            return jsonObject;
        }
    }

    /**
     * 设置是否废弃
     *
     * @return ok/fail
     */
    @RequestMapping(value = "/goods/setIsDel.do", method = RequestMethod.POST)
    @ResponseBody
    public String setIsDel(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String is_del = request.getParameter("is_del");
        String name = request.getParameter("name");

        String cds = " and id =" + id;
        String msg = "";

        try {
            if (null == id || null == is_del || null == name) {
                return "请求参数有误，请您稍后再试";
            }
            b_classify_base c = classify_baseService.findTbyid(Integer.parseInt(id));
            // 设置用户是否离职
            boolean w = false;
            if (is_del.equals("1")) {
                w = true;
            }
            if (c != null) {
                c.setDel(w);
            }
            boolean a = classify_baseService.update(c);
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
     * 编辑分类
     *
     * @return ok/fail
     */
    @RequestMapping(value = "/goods/editClass.do", method = RequestMethod.POST)
    @ResponseBody
    public String editClass(HttpServletRequest request, HttpServletResponse response) {
        String msg = "";
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String pid = request.getParameter("pid");

        String pid2 = request.getParameter("c_prarent_id");

        Object user = request.getSession().getAttribute(Global.LOGIN_USER);
        User u = (User) user;
        try {
            if (!id.equals("") && id != null) {//修改
                b_classify_base classify = classify_baseService.findTbyid(Integer.parseInt(id));
                // classify.setPid(Integer.parseInt(pid));
                classify.setC_name(name);
                classify.setUpdate_date(new Date());
                classify.setUpdator(u.getUserName());

                boolean a = classify_baseService.update(classify);
                if (a) {
                    msg = "ok";
                } else {
                    msg = "fail";
                }
            } else {//新增
                b_classify_base classify = new b_classify_base();
                if (pid==null||"".equals(pid)){
                    classify.setPid(Integer.parseInt(pid2));
                }else {
                    classify.setPid(Integer.parseInt(pid));

                }
                classify.setC_name(name);
                classify.setCreate_date(new Date());
                classify.setCreator(u.getUserName());
                classify_baseService.add(classify);
                msg = "ok";
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
    @RequestMapping(value = "/goods/del.do", method = RequestMethod.POST)
    @ResponseBody
    public String del(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        String msg = "";
        try {
            if (null == id) {
                return "请求参数有误，请您稍后再试";
            }
            b_classify_base classify_base = new b_classify_base();
            classify_base.setId(Integer.parseInt(id));

            recursion(Integer.parseInt(id));
            classify_baseService.delete(classify_base);
            msg = "ok";
        } catch (Exception e) {
            e.printStackTrace();
            msg = "操作异常，请您稍后再试！";
        }
        return msg;
    }

    public void recursion(int id) {
        List<b_classify_base> cs = classify_baseService.findSons(id);
        if (!cs.isEmpty()) {
            for (b_classify_base c : cs) {
                recursion(c.getId());
                classify_baseService.delete(c);
            }
        }
    }


}
