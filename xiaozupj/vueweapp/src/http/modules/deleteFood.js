import * as axios from "@/http/axios";

export function deleteFood(url, params){
    return axios.get(url,params)
}