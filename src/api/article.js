import request from '@/utils/request'
import qs from 'querystring'
import {cookieLogin} from "@/api/user";
import {getToken} from "@/utils/auth";
export function getArticleList(start,offset) {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:"/getArticleList",
    method:"post",
    data:qs.stringify({
      start:start,
      offset:offset,
      aType:""
    })
  })

}

export function getArticlePages() {
  return request({
    url:"/getArticlePages",
    method:"post",
    data:qs.stringify({
      aType:""
    })

  })
}
export function getArticleById(aId) {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:"/getArticleById",
    method:"post",
    data:qs.stringify({
      aId:aId
    })
  })
}

export function saveArticle(article) {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:"/saveArticle",
    method:"post",
    data:article
  })
}
export function deleteArticle(aId) {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:"/deleteArticle",
    method:"post",
    data:qs.stringify({
      aId:aId
    })
  })

}

export function publishArticle(article) {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:"/publishArticle",
    method:"post",
    data:article
  })

}

export function getArticleTimeCount() {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url:'/getArticleTimeCount',
    method:'post',

  })
}


export function getAllType() {

  return request({
    url:"/getAllType",
    method:"post"
  })
}
