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

