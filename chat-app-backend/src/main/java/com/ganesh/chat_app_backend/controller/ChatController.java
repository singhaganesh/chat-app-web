package com.ganesh.chat_app_backend.controller;

import com.ganesh.chat_app_backend.config.AppConstants;
import com.ganesh.chat_app_backend.models.Message;
import com.ganesh.chat_app_backend.payload.MessageRequest;
import com.ganesh.chat_app_backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ChatController {
    @Autowired
    ChatService chatService;

    // for sending and receiving message

    @MessageMapping("/sendMessage/{roomId}") // send message (/app/sendMessage/roomId)
    @SendTo("/topic/room/{roomId}") // user subscribe on this endpoint
    public ResponseEntity<Message> sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ){
        Message message = chatService.saveMessage(request);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
