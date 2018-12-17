package com.crs.controller.prom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/crs")
public class permissionController {

    @RequestMapping(value = "/admin-permission.do", method = RequestMethod.GET)
    public String permissionPage() {
        return "/admin-permission";
    }

    @RequestMapping(value = "/admin-permission-add.do", method = RequestMethod.GET)
    public String permissionAddPage() {
        return "/admin-permission-add";
    }
}
