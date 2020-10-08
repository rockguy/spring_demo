package demo.controller;

import com.google.gson.Gson;
import demo.dto.RequestDto;
import demo.jms.Producer;
import demo.model.Request;
import demo.model.User;
import demo.service.EmailService;
import demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//TODO: уведомить пользователя, что запрос отправлен
@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    Producer producer;

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
        Request request = new Request();
        request.setStatus(Request.RequestStatus.WAIT);
        user.getRequests().add(request);
        request.setUser(user);
        userService.saveUser(user);
        producer.sendMessage("verifier.in.queue", new Gson().toJson(new RequestDto(request)));
        return "/WEB-INF/jsp/index.jsp";
    }

}