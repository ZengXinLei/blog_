import request from '@/utils/request'
import qs from 'querystring'
import {cookieLogin} from "@/api/user";
import {getToken} from "@/utils/auth";

export function getAllComments(start,offset) {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:"/getAllComments",
    method:"post",
    data:qs.stringify({
      start:start,
      offset:offset
    })
  })
}

export function getAllCommentsPage() {
  return request({
    url:"getAllCommentsPage",
    method:"post"
  })
}


export function deleteComment(cId) {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:"/deleteComment",
    method:"post",
    data:qs.stringify({
      cId:cId
    })
  })
}
