<template>
  <div class="view-body-detail">
    <loading-spining :is-show="isShow"></loading-spining>
    <div class="row">
      <div class="col-md-12 col-sm-12">
        <div id="content-detail" ref="content-detail" class="content-detail content">
<!--          <detail-nav-bar></detail-nav-bar>-->
          <div class="detail-head">
            <div class="detail-title">
              <h1>{{article.aTitle}}</h1>
            </div>
            <hr />
          </div>
          <div v-html="article.aHtml" class="markdown-body">
          </div>
        </div>
        <div class="thumb-for">
          <!-- thumbs-count传递的是点赞的数量，thumbs-up-flag传递的是当前用户是否点赞的标识 -->
          <thumb-up :give="give" :thumbs-count="article.aClickCount" :thumbs-up-flag="isLike" v-if="flag1"></thumb-up>
        </div>
<!--        <article-share :signature-author="signatureAuthor"></article-share>-->
        <hr style="margin-top:8px;" />
<!--        <article-next-pre :pre-next="articleNextPreData"></article-next-pre>-->
<!--        <article-tags :tag-list="tagList"></article-tags>-->
        <hr /><br/>
<!--        评论-->
        <div class="content-detail-end comment shadow">
          <blog-comment :count="count"  v-on:submit-comment="subLeaveMessage">
          </blog-comment><hr />
          <div class="comment-list">
            <div v-if="flag1" id="comment" class="comment-list-head">
              评论列表 <font style="font-size: 14px;color:#c7254e;">(共{{count}}条评论)</font>
              <hr>
            </div>
            <div class="comment-list-body" v-if="flag1">
              <div class="pulldown" v-if="!isGetComment"><a @click="getComment" href="javascript:;">看看别人怎么说</a></div>
              <template v-if="flags2">
                <comment-list @addComment="addComment" :tourist="tourist" :article="article" v-if="isGetComment" :comment-list="commentList"></comment-list>
              </template>
            </div>
          </div>
        </div>
<!--        <article-most-view :most-view="articleMostView"></article-most-view>-->
      </div>
    </div>
  </div>
</template>

<script>
import thumbUp from '@/views/components/thumb-up.vue';
import articleNextPre from '@/views/components/detail/article-next-pre.vue';
import articleTags from '@/views/components/detail/article-tags.vue';
import articleMostView from '@/views/components/detail/article-most-view.vue';
import articleShare from '@/views/components/detail/article-share.vue';
import detailNavBar from '@/views/components/detail-nav-bar.vue';
import blogComment from '@/views/components/blog-comment';
import commentList from '@/views/components/comment-list';

// 这里异步加载本地文章，模拟从服务器请求
import content from '../../assets/content.txt';
// 可以由外部引入您的markdown样式，这里引入的是我自己博客的markdown文章的样式。
// 需要更改的可以注释掉，引入自己需要的比如富文本的样式或者自己在markdown.CSS里面修改
import markdown from '../../assets/css/markdown/markdown.css';
import config from '@/config/blog-config.json';
import {getArticleById} from '../../api/blog';
import {isLike} from '../../api/give';
import {verify} from '../../api/tourist';
import {addComment, getCommentCount, getComments} from '../../api/comment'
import {readArticle} from '../../api/looked';
import LoadingSpining from '../components/loading/loading-spining';
export default {
  name: 'blog-detail',
  components: {
    LoadingSpining,
    detailNavBar: detailNavBar,
    thumbUp: thumbUp,
    articleNextPre: articleNextPre,
    articleTags: articleTags,
    blogComment: blogComment,
    commentList: commentList,
    articleMostView: articleMostView,
    articleShare: articleShare
  },
  data () {
    return {
      isLike:true,
      article:{},
      flag1:false,
      flags2:false,
      give:{},
      tourist: {},
      count:0,
      isShow:true,

      content: content,
      markdownHtmlTest: '',
      thumbsUpFlag: false,
      thumbsUpCount: 0,
      signatureAuthor: '2018-07-24 8:00 By Leon',
      isGetComment: false,

      articleMostView: [],
      commentList: [],
      // 默认第一个是pre，第二个是next
      articleNextPreData: [
        {
          type: 'pre',
          name: 'Javascript基础设计(四)——不可忽视的细节',
          route: '/detail/12'
        }, {
          type: 'next',
          name: '没有更多了',
          route: 'javascript:;'
        }
      ],
      tagList: []
    }
  },
  created () {

    /**
     * 先调getArticleById获取文章，
     * 查看本地cookie,是不是游客，如果不是，成为游客
     * 调接口 readArticle 判断该文章访问数是否增加
     * 调isLike接口判断该游客是否点赞
     */
    getArticleById(this.$route.params.articleId).then(res=>{
      this.article=res.data

      getCommentCount(this.article.aId).then(res=>{
        this.count=res.data
      })
    }).then(()=>{
      verify(this.$cookies).then(tourist=>{
        this.tourist=tourist
        readArticle({
          lArticleId:this.article.aId,
          lTouristId:this.tourist.tId,
          lTime:new Date().getTime()/1000
        })
        this.give={
          gArticleId:this.article.aId,
          gTouristId:tourist.tId
        }
        isLike({
          gArticleId:this.article.aId,
          gTouristId:this.tourist['tId']
        }).then(res=>{
          this.isLike = res.data !== "";

          //防止异步加载数据不及时
          this.flag1=true
          this.isShow=false
        })
      })
    })



  },
  methods: {
    init () {
      this.getConfig();
    },
    getConfig () {
      this.tagList = config.data.detail['tagList'];
      this.commentList = JSON.parse(JSON.stringify(config.data.detail['detailComment']));
      this.articleMostView = JSON.parse(JSON.stringify(config.data.detail['articleMostView']))
      this.articleMostView.forEach(item => {
        item.photo = require('../../' + item.photo);
      })
      this.commentList.forEach(item => {
        item.userPhoto = require('../../' + item.userPhoto);
      })
    },

    //对评论进行评论

    addComment(data, id, index){
      if(data.length===0||data.length>=1000){
        return
      }
      let comment={
        cText:data,
        cTouristId:this.tourist.tId,
        cArticleId:this.article.aId,
        cReviewId:id,
        cTime:new Date().getTime()/1000
      }
      addComment(comment).then(res=>{

        res.data["comment"]=this.commentList[index]
        res.data.tourist=this.tourist
        this.commentList.push(res.data)
        this.count++
      })
    },



    /**
     * 发表评论
     * @param data
     */
    subLeaveMessage (data) {
      console.log(this.article.aId)
      let comment={
        cText:data,
        cTouristId:this.tourist.tId,
        cArticleId:this.article.aId,
        cReviewId:'',
        cTime:new Date().getTime()/1000
      }

      if(data.length===0||data.length>=1000){
        return
      }
      addComment(comment).then(res=>{
        res.data["tourist"]=this.tourist
        this.commentList.push(res.data)
        this.count++
      })
    },
    getComment () {
      // alert('这里通过请求获取了评论信息，通过isGetComment标识来判断是否显示请求的数据与此按钮');
      this.isGetComment = !this.isGetComment;
      getComments(this.article.aId,0,0).then(res=>{
        this.commentList=res.data
        this.flags2=true
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.view-body-detail{
  opacity: 0;
  position: absolute;
  top: 0;
  background: 0 0;
  transition: all .4s ease;
  -webkit-transition: all .4s ease;
  -moz-transition: all .4s ease;
  width: 100%;
}
.slide .view-body-detail{
  opacity: 1;
}
.content-detail,
.content-detail-end{
  background-color: #fafafa;
  padding: 5% 4%;
}
.detail-head{
  font-weight: 400;
  text-align: center;
  font-family: Vollkorn,Palatino,Times;
  color: #333;
  line-height: 1.4;
  margin-top: 50px;
  margin-bottom: 30px;
}
.comment-list-head{
  font-weight: 500;
  font-size: 18px;
}
.comment-list-head hr{
  margin-top: 10px;
  margin-bottom: 10px;
}
@media (min-width: 768px){
  .view-body-detail {
    padding: 10px 14% 0 14%;
  }
  .view-body-detail>.row {
    padding: 0 10%;
  }
}
@media (max-width: 767px){
  .row {
    margin: 0;
  }
}
@media (max-width: 540px) {
  .content-detail{
    padding: 5% 0%;
  }
}
</style>
