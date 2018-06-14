package com.otod.server.otod.controller.vrss;

import com.otod.server.otod.services.vrss.VrssUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/vrss/User")
public class VrssUserController {
    @Autowired
    private VrssUserService vrssUserService;
    @RequestMapping(value = "/getName")
    public String getName(@RequestParam("user_id") Integer user_id){
        return vrssUserService.getName(user_id);
    }
    @RequestMapping(value = "/register")
    public void register(@RequestParam("user_id") Integer user_id){
        vrssUserService.register(user_id);
    }
}
