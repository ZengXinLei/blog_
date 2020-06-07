export  function parseTime(timestamp) {
  let t=Math.floor(new Date().getTime()/1000)

  t=t-timestamp
  let day_num=Math.floor(t/(24 * 3600 ))
  if(day_num>=1){
    if(day_num>30){
      return new Date(timestamp+ 8 * 3600 * 1000).toJSON().substr(0, 19).replace('T', ' ')
    }
    return day_num+"天前"

  }
  let hour=Math.floor(t/(3600))
  if(hour>=1){
    return hour+"小时前"
  }
  let minute=Math.floor(t/(60))

  if(minute>=1){
    return minute+"分钟前"
  }
  return "刚刚"
}


export function computedTime(data) {
  let tmp=[]
  if(data.length===0){
    return []
  }
  data.sort((function (p) {
    return function (m, n) {
      let a = new Date(m[p].replace(/-/g, '/')).getTime() / 1000
      let b = new Date(n[p].replace(/-/g, '/')).getTime() / 1000
      return a - b
    }
  })('time'))

  let start = new Date(data[0]['time'].replace(/-/g, '/')).getTime() / 1000
  let end = new Date().getTime() / 1000


  for (let i = 0; i <= (end - start) / (3600 * 24); i++) {
    let day = start + i * (3600 * 24)

    if (data.length!==0&&new Date(data[0]['time'].replace(/-/g, '/')).getTime() / 1000 === day) {
      tmp.push(data[0])

      data.splice(0,1)
    }else {
      let tmp_day=new Date(day*1000+ 8 * 3600 * 1000).toJSON().substr(0, 19).replace('T', ' ').split(" ")[0]
      tmp.push({
        count:0,
        time:tmp_day

      })
    }
  }

  return tmp
}



