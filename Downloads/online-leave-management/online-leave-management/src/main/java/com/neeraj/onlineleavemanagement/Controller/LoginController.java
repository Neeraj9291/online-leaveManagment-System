package com.neeraj.onlineleavemanagement.Controller;  // make sure package name matches your project structure

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // returns login.html template
    }
}
