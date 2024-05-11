package com.pomogSpringBoot.testApp.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoTestFormController {
    @RequestMapping("/showform")
    public String showForm() {
        return "test-form";
    }
    
    @RequestMapping("/processForm")
    public String processFrom(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        if (name != null) {
            model.addAttribute("message", name.toUpperCase());
        }
        return "helloworld";
    }
}
