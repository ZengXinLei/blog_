import request from '@/utils/request'
import qs from 'querystring'

export function getArticleList(start,offset,tag) {

  return request({
    url:"/getArticleList",
    method:"post",
    data:qs.stringify({
      start:start,
      offset:offset,
      aType:tag
    })
  })

}

export function getArticlePages(tag) {
  return request({
    url:"/getArticlePages",
    method:"post",
    data:qs.stringify({
      aType:tag
    })

  })
}
export function getArticleById (aId) {
  return request({
    url:"/getArticleById",
    method:"post",
    data:qs.stringify({
      aId:aId
    })
  })

}
export function getAllType() {

  return request({
    url:"/getAllType",
    method:"post"
  })
}
