package com.otod.server.otod.controller;

import com.otod.server.otod.model.CommenOrder;
import com.otod.server.otod.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhang on 2018/6/6.
 */

@RestController
@CrossOrigin
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/allServices")
    private String getAllServices(){return serviceService.getAllServices();}
}
