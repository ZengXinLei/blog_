import request from '@/utils/request'


import qs from 'querystring'

export function login(data) {
  return request({
    url:"/login",
    method:"post",
    data:qs.stringify(data)
  })
}

export function cookieLogin(data) {
  return request({
    url:"/cookieLogin",
    method:"post",
    data:data
  })
}
export function getInfo() {
  return request({
    url: '/getInfo',
    method: 'get'
  })
}

export function logout() {
  return request({
    url: '/logOut',
    method: 'post'
  })
}


