package styleshare.task.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import styleshare.task.model.User;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    
    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }
    
    @GetMapping("/login")
    public String login(@ModelAttribute("User") User user, Model model) {
        return "login";
    }

}
