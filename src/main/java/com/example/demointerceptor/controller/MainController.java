package com.example.demointerceptor.controller;

import javax.servlet.http.HttpSession;

import com.example.demointerceptor.dao.UserDAO;
import com.example.demointerceptor.model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    UserDAO dao = new UserDAO();

    @GetMapping(value = "/login")
    public String login(HttpSession session) {
        if (null == session.getAttribute("user")) {
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping(value = "/login")
    public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password,
            HttpSession session) {
        if (null == session.getAttribute("user")) {
            User user = dao.findUser(username);
            System.out.println(user == null ? "null" : user.toString());
            if (null != user && user.getPassword().equals(password)) {
                session.setAttribute("user", username);
            }
        }
        return "redirect:/";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/login";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerUser(User user) {
        dao.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value="/view-user")
    public String getMethodName(ModelMap model) {
        model.addAttribute("users", dao.findAll());
        return "view-user";
    }
    

    @GetMapping("/")
    public String mainView() {
        return "welcome";
    }

    @GetMapping(value = "/news")
    public String showNews() {
        return "news";
    }

}