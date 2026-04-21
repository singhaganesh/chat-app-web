package com.ganesh.chat_app_backend.service;

import com.ganesh.chat_app_backend.exception.APIException;
import com.ganesh.chat_app_backend.models.Message;
import com.ganesh.chat_app_backend.models.Room;
import com.ganesh.chat_app_backend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;


    public Room createRoom(String roomId) {
        if (roomRepository.findByRoomId(roomId).isPresent()){
            // room is already there
            throw new APIException("Room already exists!");
        }

        // create new room
        Room room = new Room();
        room.setRoomId(roomId);
        return roomRepository.save(room);

    }

    public Room getRoom(String roomId) {
        return roomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new APIException("Room not found!!"));
    }

    public List<Message> getMessage(String roomId) {
        Room room = getRoom(roomId);
        // get messages :
        return room.getMessages();
    }
}
