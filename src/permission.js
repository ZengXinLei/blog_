import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken,setToken ,removeToken} from '@/utils/auth' // get token from cookie
import api, {cookieLogin} from '@/api/user'
import getPageTitle from '@/utils/get-page-title'

NProgress.configure({ showSpinner: false }) // NProgress Configuration


router.beforeEach(async(to, from, next) => {
  // start progress bar

  NProgress.start()

  // set page title
  document.title = getPageTitle(to.meta.title)

  // determine whether the user has logged in
  const hasToken = getToken()
    //
    if (hasToken!==undefined) {


      setToken(JSON.parse(hasToken))
      cookieLogin(JSON.parse(hasToken)).then((res)=>{

        if(res.data!==""){
          next()
        }else {
          NProgress.done()
          removeToken()
          next("login")
        }
      })

      // NProgress.done()
    }
    else {
      /*本地无cookie数据*/
      if(to.path==="/login")
      {
        next()
      }
      else {
        NProgress.done()
        next("login")
      }

    }
  // next(`/login`)
})

router.afterEach(() => {
  // finish progress bar
  NProgress.done()
})
