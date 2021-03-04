import Axios from "axios";

const instance = Axios.create({
  baseURL: '/api',
  timeout: 60000,
  withCredentials: true, // default
});

export  default  instance;
