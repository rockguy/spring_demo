package demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class LoginController {

    // внедряем значение из application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    // Обычно я использую интерфейс Model, но в целом разницы нет,
    // т.к. используется реализация LinkedHashMap(Key, Val)
    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public String sign(Map<String, Object> model) {
        return "/WEB-INF/jsp/index.jsp";
    }

    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public String sing(Map<String, Object> model) {
        return "/WEB-INF/jsp/index.jsp";
    }

}