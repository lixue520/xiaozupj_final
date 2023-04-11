
import {post} from "@/http/axios";
import {get} from "@/http/axios"


export function getList(url,params){
    return post(url,params);
}

export function SearchTime(url,params){
    return get(url,params);
}