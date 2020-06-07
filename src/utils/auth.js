import Cookies from 'js-cookie'

const User = 'User'

export function getToken() {
  return Cookies.get(User)
}

export function setToken(user) {
  return Cookies.set(User, user,{ expires: 7 })
}

export function removeToken() {
  return Cookies.remove(User)
}
