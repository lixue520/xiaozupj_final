import * as axios from "@/http/axios";

export function optionFood(url, params){
    return axios.get(url,params)
}