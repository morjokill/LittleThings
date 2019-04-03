package single.page.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class SinglePageController {

    @GetMapping("/hi")
    public String hi(@RequestParam(name = "name", required = false,
            defaultValue = "Друг") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
