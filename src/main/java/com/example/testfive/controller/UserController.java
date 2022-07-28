package com.example.testfive.controller;

import com.example.testfive.domain.User;
import com.example.testfive.service.MessageService;
import com.example.testfive.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final MessageService messageService;

    public UserController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @PostMapping
    public String findOrCreate(@RequestParam String username){
        User user = userService.findOrCreate(username);
        return "redirect:" + "/users/" + user.getId();
    }

    @GetMapping("{id}")
    public String findOrCreate(@PathVariable Long id,
                               Model model){
        User user = userService.findById(id);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("messages", user.getMessages());
        return "user";
    }

    @PostMapping("{id}")
    public String sendMessage(@PathVariable Long id,
                               @RequestParam String message,
                               Model model){
        User user = userService.findById(id);
        messageService.createMessage(user, message);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("messages", user.getMessages());
        return "user";
    }
}
