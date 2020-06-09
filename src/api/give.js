import request from '@/utils/request'
import qs from 'querystring'

export function isLike (give) {
  return request({
    url:"/isLike",
    method:"post",
    data:give
  })

}

export function like (give) {
  return request({
    url:"/like",
    method:"post",
    data:give
  })
}
