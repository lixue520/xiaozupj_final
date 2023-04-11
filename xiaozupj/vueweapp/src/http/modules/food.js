
import * as axios from "@/http/axios";

export function getFoodList(url,params){
    return axios.get(url,params)
}