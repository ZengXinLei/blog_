import request from '@/utils/request'
import qs from 'querystring'

export function readArticle (looked) {
  return request({
    url:"readArticle",
    method:"post",
    data:looked
  })

}
