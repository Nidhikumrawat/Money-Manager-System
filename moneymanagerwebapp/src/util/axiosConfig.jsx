import axios from "axios";
import { BASE_URL } from "./apiEndpoints.js"; 

const axiosConfig = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json"
  }
});


//List of endpoints that do not require authentication
const excludeEndpoints = ["/login", "/register", "/status", "/activate", "/health"];

//request interceptor to add token to headers
axiosConfig.interceptors.request.use((config) => {
  const shouldSkipToken = excludeEndpoints.some((endpoint) => config.url?.includes(endpoint));


  if (!shouldSkipToken) {
    const accessToken = localStorage.getItem("token");

    if (accessToken) {
      config.headers.Authorization = `Bearer ${accessToken}`;
    }
  }
  return config;
}, (error) => {
  return Promise.reject(error);

});

// response interceptor to handle errors
axiosConfig.interceptors.response.use((response) => {
  return response;
}, (error) => {
  if (error.response) {
    if (error.response.status === 401) {
      // Handle unauthorized access
      console.error("Unauthorized access - redirecting to login");
      window.location.href = "/login"; // Redirect to login page
    } else if (error.response.status === 500) {
      console.error("Server error - please try again later");
    }
  }else if(error.code === "ECONNABORTED") {
    console.error("Request timeout - please check your internet connection");
  }
  return Promise.reject(error);
})

export default axiosConfig;