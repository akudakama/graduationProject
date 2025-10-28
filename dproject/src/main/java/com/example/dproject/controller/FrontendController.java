

package com.example.dproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontendController {

    @RequestMapping({
            "/",
            "/login",
            "/register",
            "/products/**",
            "/cart",
            "/orders",
            "/dashboard",
            "/admin/**",
            "/addresses"
    })
    public String forwardAll() {
        return "forward:/index.html";
    }
}