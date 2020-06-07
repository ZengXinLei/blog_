import request from '@/utils/request'
import qs from 'querystring'
import {cookieLogin} from "@/api/user";
import {getToken} from "@/utils/auth";

export function saveDrafts(data) {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:"/saveDrafts",
    method:"post",
    data:data
  })

}
export function getDraftList(start,offset) {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:"/getDraftList",
    method:"post",
    data:qs.stringify({
      start:start,
      offset:offset
    })
  })

}
export function deleteDraft(did) {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url: "/deleteDraft",
    method: "post",
    data: qs.stringify({
      did: did
    })
  })
}


export function getDraftById(did) {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:'/getDraftById',
    method:"post",
    data:qs.stringify({
      did:did
    })
  })
}
export function getDraftPages() {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:'/getDraftPages',
    method:'post',

  })
}

export function getTimeCount() {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:'/getTimeCount',
    method:'post',

  })
}
