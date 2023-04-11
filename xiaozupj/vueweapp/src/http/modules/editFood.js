import * as axios from "@/http/axios";
import {post} from "@/http/axios";

export function editFood(url,params){
    return post(url,params)
}