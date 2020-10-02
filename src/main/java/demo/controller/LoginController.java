package demo.controller;

import demo.model.User;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//TODO: уведомить пользователя, что запрос отправлен
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    // Обычно я использую интерфейс Model, но в целом разницы нет,
    // т.к. используется реализация LinkedHashMap(Key, Val)
    @RequestMapping(value = "/sign", method = RequestMethod.GET)
    public String sign(Map<String, Object> model) {
        return "/WEB-INF/jsp/index.jsp";
    }


    //TODO: добавить проверку аргументов, и наличие пользователя в БД
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public String sing(@RequestParam("login") String login,
                       @RequestParam("password") String password,
                       @RequestParam("email") String email,
                       @RequestParam("fio") String fio) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setFullName(fio);
        userService.addUser(user);
        return "/WEB-INF/jsp/index.jsp";
    }

}