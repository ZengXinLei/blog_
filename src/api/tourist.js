import request from '@/utils/request'
import qs from 'querystring'
function signTourist () {
  return request({
    url:"/signTourist",
    method:"post"
  })

}

export function verify (cookies) {
  return new Promise((e,t)=>{
    let tourist=cookies.get("tourist")

    if (tourist===null) {
      signTourist().then(res => {
        cookies.set("tourist", res.data, '30d')
        e(res.data)
      })
    }
    else {
      e(tourist)
    }


  })

}
