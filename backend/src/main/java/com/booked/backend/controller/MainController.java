package com.booked.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    // Chama a página inicial do Booked quando o programa é executado
    @RequestMapping("/")
    public String index() {
        return "/html/index.html";
    }

}
