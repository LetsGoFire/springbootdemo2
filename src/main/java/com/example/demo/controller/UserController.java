package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getAll(Model model){
        List<User> userList = userService.getUserList();
        model.addAttribute("page",userList);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(User user){
        return "user/userAdd";
    }

    @PostMapping("/add")
    public String createUser(User user){
        userService.createUser(user);
        return "redirect:/users/";
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model,@PathVariable("id") Long id){
        User user = userService.getUser(id);
        model.addAttribute("user",user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user){
        userService.updateuser(user.getId(),user);
        return "redirect:/users/";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Long id){
        userService.deleteUser(id);
        return "redirect:/users/";
    }

}
