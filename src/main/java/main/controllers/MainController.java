package main.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("title", "Запланированные дела");
        return "homePage";
    }
}
