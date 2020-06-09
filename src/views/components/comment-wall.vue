<template>
  <div class="comment-list-body">
    <comment-list :comment-list="commentList" @addComment="addComment"></comment-list>
    <p v-show="!isLoading && !isFinish" class="falls-item"><i class="fa fa-angle-double-down animate-movedown fa-2x fa-fw margin-bottom"></i> 下滑更多留言</p>
    <p v-show="isLoading" class="falls-item"><i class="fa fa-spinner fa-spin fa-3x fa-fw margin-bottom"></i> 刷新中</p>
    <div style="width: 100%;text-align: center;padding-top: 20px;" v-show="isFinish">
      <h5><img src="../../assets/image/icon/cry.svg"> &nbsp;&nbsp;没有更多的留言啦 </h5>
    </div>
  </div>
</template>

<script>
import {scrollToBottom} from '@/utils/utils';
import commentList from './comment-list'
import {addComment, getComments} from '../../api/comment';

export default {
  props: ['commentList', 'waterfallData','tourist'],
  name: 'commentWall',
  components: {
    commentList: commentList
  },
  data:function () {
    return {
      isLoading: false,
      isFinish: false,
      count:0,
      offset:3
    }
  },
  watch: {
    // waterfallData (newValue, oldValue) {
    //   console.log(newValue);
    //   console.log(oldValue);
    //   // this.isLoading = newValue.isLoading;
    //   // this.isFinish = newValue.isFinish && true;
    // }
  },
  mounted: function () {
    window.addEventListener('scroll', this.handleScroll);
  },
  methods: {
    handleScroll: function (e) {
      const _self = this;
      scrollToBottom.onScrollEvent(function () {
          this.isLoading = true;
          _self.waterFall();
      });
    },
    waterFall: function () {
      // let _self = this;
      // this.$emit('waterfall', {
      //   isLoading: isLoading,
      //   isFinish: _self.isFinish
      // });
      if(this.isFinish===false){
        getComments(-1,this.count,this.offset).then(res=>{
          for (let i = 0; i < res.data.length; i++) {
            this.commentList.push(res.data[i])
          }
          this.count+=this.offset
          this.isLoading = false;
          if(res.data.length===0){
            this.isFinish=true
          }
          // console.log(res.data)


        })
      }
    },
    addComment:function (data, id, index) {
      if(data.length===0||data.length>=1000){
        return
      }
      let comment={
        cText:data,
        cTouristId:this.tourist.tId,
        cArticleId:-1,
        cReviewId:id,
        cTime:new Date().getTime()/1000
      }
      addComment(comment).then(()=>{

        comment["comment"]=this.commentList[index]
        comment.tourist=this.tourist
        this.commentList.push(comment)
      })

    }
  },
  beforeDestroy () {
    console.log('瀑布流组件开始销毁');
    window.removeEventListener('scroll', this.handleScroll);
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
