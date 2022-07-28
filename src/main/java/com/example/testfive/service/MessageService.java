package com.example.testfive.service;

import com.example.testfive.dao.MessageRepository;
import com.example.testfive.domain.Message;
import com.example.testfive.domain.User;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void createMessage(User user, String text){
        Message message = new Message();
        message.setUser(user);
        message.setText(text);
        save(message);
    }

    public void save(Message message){
        messageRepository.save(message);
    }
}
