package com.otod.server.otod.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ActivityController {
    @GetMapping("/activity")
    private String getActivityContent(){
        return "activity/xiaoshu.png";
    }
}
