import {post} from "@/http/axios";
import {get} from "@/http/axios";
import {postFile} from "@/http/axios";

export function upload(url,params){
    return postFile(url,params);
}

export  function getShowVue(url,params){
    return get(url,params);
}

export function changAble(url,params){
    return get(url,params);
}

export function deleteFile(url,params){
    return get(url,params);
}
