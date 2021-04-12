import request from '@/utils/request'
import { cookieLogin } from '@/api/user'
import { getToken } from '@/utils/auth'

export function getLookedTimeCount() {
  cookieLogin(JSON.parse(getToken()))
  return request({
    url: '/getLookedTimeCount',
    method: 'post'

  })
}
