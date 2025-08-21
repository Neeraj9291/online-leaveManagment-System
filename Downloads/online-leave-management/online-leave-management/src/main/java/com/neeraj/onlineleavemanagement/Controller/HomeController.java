package com.neeraj.onlineleavemanagement.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home/apply-leave")  // changed URL to be unique
    public String showApplyLeaveForm() {
        return "apply-leave"; // Thymeleaf template without ".html"
    }
}
