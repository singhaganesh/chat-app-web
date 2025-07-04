package com.ganesh.chat_app_backend.service;

import com.ganesh.chat_app_backend.exception.APIException;
import com.ganesh.chat_app_backend.models.Message;
import com.ganesh.chat_app_backend.models.Room;
import com.ganesh.chat_app_backend.payload.MessageRequest;
import com.ganesh.chat_app_backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatService {
    @Autowired
    RoomRepository roomRepository;

    public Message saveMessage(MessageRequest request) {
        Room room = roomRepository.findByRoomId(request.getRoomId());

        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());

        if (room != null){
            room.getMessages().add(message);
            roomRepository.save(room);
        }else {
            throw new APIException("Room not found !!");
        }
        return message;
    }
}
