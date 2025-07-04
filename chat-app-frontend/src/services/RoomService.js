import { httpClient } from "../config/AxiosHelper"

export const createRoomApi=async (roomDetails)=>{
    const respone = await httpClient.post(`/api/v1/rooms`,roomDetails, {
        headers: {
            "Content-Type": "text/plain",
        },
    });
    return respone.data;
};

export const joinChatApi= async(roomId)=>{
    const respone = await httpClient.get(`api/v1/rooms/${roomId}`);
    return respone.data;
}

export const getMessages=async(roomId)=>{
    const respone = await httpClient.get(`/api/v1/rooms/${roomId}/messages`);
    return respone.data;
}