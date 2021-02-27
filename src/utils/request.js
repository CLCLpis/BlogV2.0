import Axios from "axios";

const instance = Axios.create({
  baseURL: 'https://web.bear0901.cn:32443/blog',
  timeout: 3000,
  withCredentials: true, // default
});

export  default  instance;
