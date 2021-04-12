import request from '@/utils/request'
export function getConfigByName(name) {
  return request({
    url:"/getConfigByName",
    method:"get",
    params:{
      name:name
    }
  })
}
