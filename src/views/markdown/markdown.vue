<template>
  <div class="components-container">

    <div class="editor-container">
      <el-input type="text" v-model="draft.dtitle" placeholder="输入文章标题" maxlength="100" minlength="5"
                show-word-limit></el-input>
      <el-row style="margin-bottom: 10px">
        <el-col :span="18">

        </el-col>
        <el-col :span="2" :offset="1">

        </el-col>
        <el-col :span="2" :offset="1">

        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-tag
            v-for="(tag,index) in tags"
            :key="index"
            closable
            type="success"
            @close="deletetag(index)"
            style="margin: 10px"
            effect="dark"
          >{{tag}}</el-tag>
        </el-col>
      </el-row>
<!--        <MarkdownPro v-if="false"-->
<!--          :autoSave="true"-->
<!--          :interval="60000"-->
<!--          @on-save="saveDrafts"-->
<!--          style="margin-top: 20px;"-->
<!--          ref="markdownEditor"-->
<!--          v-model="draft.dtext"-->
<!--          :language="language"-->
<!--          :toolbars="options"/>-->
      <mavon-editor style="z-index: 0;min-height: 500px" ref="mavon" v-model="draft.dtext" @change="test" @save="saveDrafts"></mavon-editor>
      <div style="margin-top: 20px;float:right">
        <el-button @click="saveDraft">保存草稿</el-button>
        <el-button type="danger" @click="dialog=!dialog">发布文章</el-button>
      </div>
    </div>


    <el-dialog
      :visible.sync="dialog"
      width="30%"
    >

<!--          <el-alert v-for="(tag,index) in tags"-->
<!--                    :key="index"-->
<!--                    :title="tag" :type="index%2===0?'error':'success'"-->
<!--                    @close="deletetag(index)"-->
<!--                    style="margin-top: 10px"></el-alert>-->
<!--      <el-row >-->
<!--        <el-col :span="8" >-->
<!--          <alert v-for="(tag,index) in tags"-->
<!--                 :title="tag"-->
<!--                 :index="index"-->
<!--                 @deletetag="deletetag"-->
<!--                 style="float: left;display: inline;margin: 5px"-->
<!--          ></alert>-->
          <el-tag
            v-for="(tag,index) in tags"
            :key="index"
            closable
            type="success"
            @close="deletetag(index)"
            style="margin: 10px"
            effect="dark"
          >{{tag}}</el-tag>
<!--        </el-col>-->
<!--      </el-row>-->

      <el-input v-model="t" @keydown.enter.native="enter" placeholder="输入标签，回车确认" style="margin-top: 20px; "></el-input>

      <el-row>
        <el-col>
          <el-tag
            v-for="(tag,index) in alltags"
            :key="index"
            type="info"
            @click="addTag(tag)"
            style="margin: 6px;cursor: pointer"
          >{{tag}}</el-tag>
        </el-col>
      </el-row>

      <el-button @click="publishArticle" size="small" type="danger" style="margin-top: 20px;">确认发布</el-button>

    </el-dialog>
  </div>
</template>

<script>
  // import MarkdownEditor from '@/components/MarkdownEditor'
  import {MarkdownPro} from 'vue-meditor'
  import option from "@/views/markdown/option";
  import {saveDrafts, getDraftById, deleteDraft} from "@/api/draft"
  import {getAllType, getArticleById, publishArticle, saveArticle} from "@/api/article";
  import {transitionTimestamp} from "@/utils/parseTime";
  import Alert from "@/components/alert/alert";
  import mavonEditor from 'mavon-editor'
  import 'mavon-editor/dist/css/index.css'
  export default {
    name: 'MarkdownDemo',
    components: {Alert, MarkdownPro},
    data() {
      return {
        t: "",
        tags:[],
        alltags:[],
        dialog: false,
        options: option,
        article: {},
        draft: {},
        languageTypeList: {
          'en': 'en_US',
          'zh': 'zh_CN',
          'es': 'es_ES'
        }
      }
    },
    mounted() {
      let path = window.location.href.split("/").splice(-1)[0]
      getAllType().then(res=>{
        this.alltags=res.data
      })
      if (path === "markdown") {
        return
      }
      let did = window.location.href.split("/").splice(-1)[0]
      if (did.length < 10) {
        getArticleById(did).then(res => {
          if (res.data.type === "error") {
            this.$router.push("/404")
          }
          this.draft = {
            dtext: res.data.aText,
            dtitle: res.data.aTitle
          }
          this.article = res.data
          if(this.article.aType!==undefined&&this.article.aType!==null){
            this.tags=this.article.aType.split("|")
          }
        })
      } else {
        getDraftById(did).then(res => {

          if (res.data.type === "error") {
            this.$router.push("/404")
          }
          let data = res.data.draft

          this.draft = data
        })
      }


    },
    // watch: {
    //   t: function () {
    //     let tags = this.article.aType
    //     if (tags === undefined||tags === null) {
    //       return
    //     }
    //     this.tags=tags.split('|')
    //
    //   }
    // },
    computed: {
      language() {
        return this.languageTypeList['en']
      },

    },
    methods: {
      test:function(v,r){
        this.draft.dtext=v
        this.draft.dhtml=r
        this.article.aText=v
        this.article.aHtml=r
      },
      judge:function(tag){
        if(tag===""){
          return
        }
        for (let i = 0; i < this.tags.length; i++) {
          if(tag===this.tags[i]){
            this.$notify.error({
              "message":"标签已添加，不能重复添加"
            })
            return false
          }
        }
        return true
      },
      addTag:function(tag){
        if(this.judge(tag)){
          this.tags.push(tag)
        }

      },
      enter: function () {
        if(this.judge(this.t)) {
          this.tags.push(this.t)
        }
        this.t = ""
      },

      deletetag:function(index){
        console.log(index)
        this.tags.splice(index,1)

        console.log(this.tags.join("|"))
      },
      publishArticle() {
        this.article.aText = this.draft.dtext
        this.article.aHtml = this.draft.dhtml
        this.article.aTitle = this.draft.dtitle
        if(this.judge(this.t)){
          this.tags.push(this.t)
        }
        if(this.tags.length!==0){
          this.article.aType=this.tags.join("|")
        }else {
          this.article.aType=null
        }
        if (this.article.aTitle === undefined || this.article.aTitle < 5) {
          this.$notify.error({
            "message": "标题不能少于五个字"
          })
          return
        }

        if (this.article.aId === undefined) {
          publishArticle(this.article).then(res => {
            this.$notify({
              "type": res.data.type,
              "message": res.data.message
            })
            if (res.data.type === "success") {
              deleteDraft(this.draft.did)
              this.$router.push("/admin")
            }
          })
        } else {

          saveArticle(this.article).then(res => {
            this.$notify({
              "type": res.data.type,
              "message": res.data.msg
            })
            if (res.data.type === "success") {
              deleteDraft(this.draft.did)
              this.$router.push("/admin")
            }
          })
        }
      },
      saveDrafts(v,r) {

        // let text = this.draft.dtext
        // let html = this.draft.dhtml

        this.draft['dtext'] = v
        this.draft["dhtml"] = r
        this.saveDraft()


      },
      saveDraft(){
        if (this.draft.dtitle === undefined || this.draft.dtitle.length < 5) {
          this.$notify.error({
            message: "标题不能低于五个字符"
          })
          return
        }
        saveDrafts(this.draft).then(res => {
          this.$notify({
            type: res.data.type,
            message: res.data.msg
          })
          this.draft = res.data.draft
        })
      }
    }
  }
</script>

<style scoped>
  .editor-container {
    margin-bottom: 30px;
  }

  .tag-title {
    margin-bottom: 5px;
  }
</style>
