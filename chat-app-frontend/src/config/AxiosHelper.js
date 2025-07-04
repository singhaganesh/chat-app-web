import axios from 'axios';
export const baseURL = "https://chat-app-backend-y4dw.onrender.com";
export const httpClient = axios.create({
    baseURL: baseURL,
});