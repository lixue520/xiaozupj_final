import * as axios from "@/http/axios";

export function selectFood(url, params){
    return axios.get(url,params)
}