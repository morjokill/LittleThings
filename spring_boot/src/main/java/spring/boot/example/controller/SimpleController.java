package spring.boot.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SimpleController {

    @GetMapping("/hi")
    public String hi() {
        return "Русские вперед!";
    }
}
