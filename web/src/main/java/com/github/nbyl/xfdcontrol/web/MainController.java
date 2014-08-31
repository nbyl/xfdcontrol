package com.github.nbyl.xfdcontrol.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {

    @RequestMapping("/")
    public String welcome(Map<String, Object> model) {
        model.put("message", "Hello World!");
        return "index";
    }
}
