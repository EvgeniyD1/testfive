package com.example.testfive.controller;

import com.example.testfive.domain.MessageRequest;
import com.example.testfive.domain.User;
import com.example.testfive.service.MessageService;
import com.example.testfive.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final MessageService messageService;

    public UserController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("{username}")
    public User findUser(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @PostMapping
    public void create(@RequestBody User user){
        userService.createUser(user.getUsername());
    }

    @PostMapping("/send")
    public void sendMessage(@RequestBody MessageRequest messageRequest){
        messageService.createMessage(messageRequest);
    }
}
