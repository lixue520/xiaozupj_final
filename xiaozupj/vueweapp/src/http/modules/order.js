import {post} from "@/http/axios";
import {get} from "@/http/axios"

export function findOrder(url, params) {
    return get(url, params);
}

export function DellOrder(url,params){
    return post(url,params);
}

export function FinishOrder(url,params){
    return post(url,params);
}