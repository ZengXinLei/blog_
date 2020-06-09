<template>
  <div id="view-body" class="view-body view-body-blog">
    <div class="row">
      <div class="col-md-12 col-sm-12 ">
        <div class="moreTag" @click="tagShowFlag=!tagShowFlag">
          <img :class="tagShowFlag?'moreTagTransform':''" src="../../assets/image/icon/moreTag.svg"> {{tagShowFlag?'收起标签墙':'展开标签墙'}}</div>
          <transition name="slide-more-tag">
            <div v-show="tagShowFlag" class="tag-wall shadow">
              <div class="tag-wall-head">标签墙</div>
              <div class="tag-wall-body">
                <a @click="refreshArticle(tagItem)" v-for="tagItem in tags" class="tag" style="cursor: pointer">{{tagItem}}</a>
              </div>
            </div>
          </transition>
          <loading-spining :is-show="isShow"></loading-spining>
          <div  class="row-content">
                <article-item v-if="!isShow"  v-on:thumbclick="thumbSubmit" :article-list="rowitem"></article-item>
                <article-page :current-page="currentPage" :page-all="currentPageAll" v-on:pageprocss="pageProcss"></article-page>
          </div>
        </div>
      </div>
    <blog-foot></blog-foot>
  </div>
</template>

<script>
import blogFoot from '@/views/components/blog-foot';
import articleItem from '@/views/components/article-item';
import articlePage from '@/views/components/article-page';

import config from '@/config/blog-config.json';
import {getArticleList} from '@/api/blog'
import {getAllType, getArticlePages} from '../../api/blog';
import LoadingSpining from '../components/loading/loading-spining';
export default {

  name: 'blog',

  components: {
    LoadingSpining,
    blogFoot: blogFoot,
    articleItem: articleItem,
    articlePage: articlePage
  },
  data () {
    return {
      isShow:true,
      tag:"",
      tags:[],
      tagShowFlag: false,
      currentPage: 1, // 当前页
      offset:5,
      currentPageAll: 1, // 总页数
      tagList: [], // 标签数组
      rowitem: []// 目录列表
    }
  },
  created () {

    this.init();
    getArticlePages(this.tag).then(res=>{
      this.currentPageAll=Math.ceil(res.data/this.offset)
    })
    getAllType().then(res=>{
      this.tags=res.data
    })
    getArticleList((this.currentPage-1)*this.offset,this.offset,this.tag).then(res=>{
      this.rowitem=res.data
      this.isShow=false
    })

  },
  methods: {
    refreshArticle:function(tag){
      this.rowitem=[]
      this.currentPage=1
      this.tag=tag
      getArticlePages(this.tag).then(res=>{
        this.currentPageAll=Math.ceil(res.data/this.offset)
      })
      getArticleList((this.currentPage-1)*this.offset,this.offset,this.tag).then(res=>{
        this.rowitem=res.data
      })
    },
    init () {
      this.tagList = config.data.detail['tagList'];

      // this.rowitem = JSON.parse(JSON.stringify(config.data.article['articleList']));
      // this.rowitem.forEach(item => {
      //   item.photoUrl = require('../../' + item.photoUrl);
      // });
    },
    thumbSubmit () {
      // alert('您点赞了');
    },
    pageProcss (data) {
      // alert(`消息是：${data.message}
      //   您使用了分页功能，点击后当前页数为${data.current}，您可以在这里写翻页的逻辑。`);
      getArticleList((data.current-1)*this.offset,this.offset,this.tag).then(res=>{
        this.rowitem=res.data
      })
    }

  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.moreTag{
  color: #555;
  text-align: center;
  margin-bottom: 20px;
  margin-top: 10px;
  cursor: pointer;
}
.moreTag img{
  width: 30px;
  transition: all;
  -webkit-transition: all .4s;
  -moz-transition: all .4s;
}
.tag-wall{
  background-color: #fff;
  padding: 10px 0;
  margin-bottom: 10px;
}
.tag-wall-head{
  padding-left: 10px;
  font-size: 18px;
  margin-bottom: 10px;
  border-left: 4px solid #2298d1;
}
.row-content{
  margin-bottom: 6px;
}
.slide-more-tag-enter-active {
  transition: all .4s ease;
}
.slide-more-tag-leave-active {
  transition: all .4s ease;
}
.slide-more-tag-enter, .slide-more-tag-leave-to
{
  transform: translate3d(0,-20px,0);
  -webkit-transform: translate3d(0,-20px,0);
  opacity: 0;
}

</style>
