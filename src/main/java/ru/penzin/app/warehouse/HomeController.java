package ru.penzin.app.warehouse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String startPage() {
        return "redirect:/shipments";
    }

}
