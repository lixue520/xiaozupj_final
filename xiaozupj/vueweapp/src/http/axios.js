/* 导入官方的axios */
import axios from "axios";
import config from "@/http/config";

const instance = axios.create({
    baseURL: config.baseUrl,
    timeout: config.timeOut,
    headers: config.headers
})

//请求拦截器，每次请求的时候，都要拦截加上请求头token
instance.interceptors.request.use(
    config => {
        /*
        请求之前，携带上token，放到请求头里面
         */
        let token = sessionStorage.getItem("token");
        if (token) {
            config.headers.token = token;
        }
        return config;
    },
    err => {
        return Promise.reject(err)
    }
)
//响应拦截器
instance.interceptors.response.use(
    response => {
        return response.data.data;
    },
    err => {
        //如果有具体错误，返回具体的"中文提示"
        console.log(err);
    }
)


const instanceFile = axios.create({
    baseURL: config.baseUrlFile,
    timeout: config.timeOut,
    headers: config.headers
})

//请求拦截器，每次请求的时候，都要拦截加上请求头token
instanceFile.interceptors.request.use(
    config => {
        /*
        请求之前，携带上token，放到请求头里面
         */
        let token = sessionStorage.getItem("token");
        if (token) {
            config.headers.token = token;
        }
        return config;
    },
    err => {
        return Promise.reject(err)
    }
)
//响应拦截器
instanceFile.interceptors.response.use(
    response => {
        return response.data.data;
    },
    err => {
        //如果有具体错误，返回具体的"中文提示"
        console.log(err);
    }
)



export function get(url, params) {
    return instance.get(url, {params});
}

export function post(url, params) {
    return instance.post(url, params)
}
export function postFile(url, params) {
    return instanceFile.post(url, params)
}