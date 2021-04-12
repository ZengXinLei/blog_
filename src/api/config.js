import request from '@/utils/request'
import qs from 'querystring'
import {cookieLogin} from "@/api/user";
import {getToken} from "@/utils/auth";


export  function getConfig(start,offset)
{
  return request({
    url:"/getConfig",
    method:"post",
    data:qs.stringify({
      start:start,
      offset:offset
    })
  })
}


export function getConfigCount() {
  return request({
    url:"/getConfigCount",
    method:"get",
  })
}

export function setConfig(config) {
  return request({
    url:"/setConfig",
    method:"post",
    data:config
  })
}
export function deleteConfig(name) {
  return request({
    url:"/deleteConfig",
    method:"get",
    params:{
      name:name
    }
  })
}
