package com.example.dproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    @RequestMapping({
            "/", "/products", "/cart", "/login", "/register"
    })
    public String forward() {
        return "forward:/index.html";
    }
}
