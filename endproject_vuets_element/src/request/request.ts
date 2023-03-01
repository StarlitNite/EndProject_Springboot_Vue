import axios from "axios";
import Cookie from "js-cookie";

const instance = axios.create({
    baseURL:'http://localhost:8000/api',
    timeout:15000
})

//拦截器
instance.interceptors.request.use(config =>{
        let token = Cookie.get('token')
        if (token){
            config.headers=config.headers||{};
            config.headers.Authorization = token;
        }
        return config;
    },error => {
        return Promise.reject(error)
    }
)
//拦截器
instance.interceptors.response.use(result =>{
        return result.data;
    },error => {
        return Promise.reject(error)
    }
)

export default instance;