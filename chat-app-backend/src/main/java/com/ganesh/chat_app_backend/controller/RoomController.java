package com.ganesh.chat_app_backend.controller;

import com.ganesh.chat_app_backend.config.AppConstants;
import com.ganesh.chat_app_backend.models.Message;
import com.ganesh.chat_app_backend.models.Room;
import com.ganesh.chat_app_backend.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    RoomService roomService;

    //create room

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody String roomId){
        Room savedRoom = roomService.createRoom(roomId);
        return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
    }

    //get room: join
    @GetMapping("/{roomId}")
    public ResponseEntity<Room> joinRoom(
            @PathVariable String roomId
    ){
        Room getRoom = roomService.getRoom(roomId);
        return new ResponseEntity<>(getRoom,HttpStatus.OK);
    }


    //get message of room
    @GetMapping("{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomId
    ){
        List<Message> messages = roomService.getMessage(roomId);
        return new ResponseEntity<>(messages,HttpStatus.OK);
    }

}