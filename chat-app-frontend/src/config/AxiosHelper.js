import axios from 'axios';
export const baseURL = "https://chat-app-backend-ybfj.onrender.com";
export const httpClient = axios.create({
    baseURL: baseURL,
});