package com.ganesh.chat_app_backend.repository;

import com.ganesh.chat_app_backend.models.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room,String> {

    // get room using room id
    Room findByRoomId(String roomId);
}
