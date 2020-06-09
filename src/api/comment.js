import request from '@/utils/request'
import qs from 'querystring'

export function addComment (comment) {
  return request({
    url:"/addComment",
    method:"post",
    data:comment
  })

}


export function getComments (aId,start,offset) {
  return request({
    url:"/getComments",
    method:"post",
    data:qs.stringify({
      aId:aId,
      start:start,
      offset:offset
    })
  })

}

export function getCommentCount (aId) {
  return request({
    url:"/getCommentCount",
    method:"post",
    data:qs.stringify({
      aId:aId
    })
  })

}
