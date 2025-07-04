import React, { useState } from "react";
import chatIcan from "../assets/chat.png";
import toast from "react-hot-toast";
import { createRoomApi, joinChatApi } from "../services/RoomService";
import useChatContext from "../context/ChatContext";
import { useNavigate } from "react-router";
const JoinCreateChat = () => {

    const [details, setDetails] = useState({
        roomId: "",
        userName: "",
    });

    const { roomId, currentUser,connected, setRoomId, setCurrentUser,setConnected } = useChatContext();

    const navigate = useNavigate();

    function handleFromInputChange(event) {
        setDetails({
            ...details,
            [event.target.name]: event.target.value,
        });
    }
    async function joinChat() {
        if (validateFrom()) {
            // join chat
            try {
                const room = await joinChatApi(details.roomId);
                toast.success("Joined..");
                setCurrentUser(details.userName);
                setRoomId(room.roomId);
                setConnected(true);
                navigate("/chat");
            } catch (error) {
                console.log(error);
                if (error.status == 400) {
                    toast.error(error.response.data.message);
                } else {
                    toast("Error in joining room");
                }
            }
        }
    }
    async function createRoom() {
        if (validateFrom()) {
            // create room
            console.log(details);

            // call api to create room on backend
            try {
                const response = await createRoomApi(details.roomId);
                console.log(response);
                toast.success("Room Created Successfully !!");
                // join the room
                setCurrentUser(details.userName);
                setRoomId(response.roomId);
                setConnected(true);

                navigate("/chat");
                // froward to chat page...
            } catch (error) {
                console.log(error);
                if (error.status == 400) {
                    toast.error(error.response.data.message);
                } else {
                    toast("Error in creating room");
                }

            }
        }
    }
    function validateFrom() {
        if (details.roomId === "" || details.userName === "") {
            toast.error("Invalid Input !!");
            return false;
        }
        return true;
    }

    return <div className="min-h-screen flex items-center justify-center">
        <div className="p-10 dark:border-gray-700 border w-full flex flex-col gap-5 max-w-md rounded dark:bg-gray-900 shadow">

            <div>
                <img src={chatIcan} className="w-24 mx-auto" />
            </div>

            <h1 className="text-2xl font-semibold text-center">
                Join Room / Create Room..
            </h1>
            {/* name div */}
            <div className="">
                <label htmlFor="name" className="block font-medium mb-2">
                    Your name
                </label>
                <input
                    onChange={handleFromInputChange}
                    value={details.userName}
                    type="text"
                    id="name"
                    name="userName"
                    placeholder="Enter your name"
                    className="w-full dark:bg-gray-600 px-4 py-2 border dark:border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
            </div>
            {/* Room id div */}
            <div className="">
                <label htmlFor="name" className="block font-medium mb-2">
                    Room ID / New Room ID
                </label>
                <input
                    name="roomId"
                    onChange={handleFromInputChange}
                    value={details.roomId}
                    placeholder="Enter room id"
                    type="text"
                    id="name"
                    className="w-full dark:bg-gray-600 px-4 py-2 border dark:border-gray-600 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
            </div>
            {/* Button */}
            <div className="flex justify-center gap-2 mt-4">
                <button onClick={joinChat} className="px-3 py-2 dark:bg-blue-500 hover:dark:bg-blue-800 rounded-lg">
                    Join Room
                </button>
                <button onClick={createRoom} className="px-3 py-2 dark:bg-orange-500 hover:dark:bg-orange-800 rounded-lg">
                    Create Room
                </button>
            </div>

        </div>
    </div>
};

export default JoinCreateChat; 