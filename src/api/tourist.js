import request from '@/utils/request'
import { cookieLogin } from '@/api/user'
import { getToken } from '@/utils/auth'

export function getTouristTimeCount() {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url: '/getTouristTimeCount',
    method: 'post'

  })
}
