<template>
  <div class="footNote">
    <p>
      <span> Copyright </span><span>©</span><span> 2020 </span>
      <a href="/"> blog </a>
      <span>{{author.value}}</span><span></span>
    </p>
<!--    <p class="friendly-link">友情链接: <a v-for="link in friendlyLink" :href="link.website">{{link.name}}</a>&nbsp;&nbsp;-->
<!--      &lt;!&ndash; <a href="https://www.ailoli.org/">Ailoli</a>&nbsp;&nbsp;-->
<!--      <a href="https://aoaoao.me/">树洞</a> &ndash;&gt;-->
<!--    </p>-->
    <p>
      <a :href="url.value" target="_blank">{{config.value }}</a>
    </p>
  </div>
</template>

<script>

import {getConfigByName} from "../../api/config";

export default {
  name: 'blogFoot',
  data () {
    return {
      friendlyLink: [{
        name: 'Vue.js',
        website: 'https://cn.vuejs.org/'
      }, {
        name: 'Ailoli',
        website: 'https://www.ailoli.org/'
      }, {
        name: '树洞',
        website: 'https://aoaoao.me/'
      }],
      config:{
        name:"",
        value:""
      },
      author:{},
      url:{}
    }
  },
  mounted() {
    getConfigByName("网站备案信息").then(res=>{

      this.config=res.data
    })
    getConfigByName("网站作者").then(res=>{

      this.author=res.data
    })

    getConfigByName("备案跳转链接").then(res=>{

      this.url=res.data
    })
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
