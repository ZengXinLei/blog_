<template>
  <div class="components-container">

    <div class="editor-container">

      <el-row style="margin-bottom: 10px">
        <el-col :span="18">
          <el-input type="text" v-model="draft.dtitle" placeholder="输入文章标题" maxlength="100" minlength="5"
                    show-word-limit></el-input>
        </el-col>
        <el-col :span="2" :offset="1">
          <el-button @click="saveDrafts">保存草稿</el-button>
        </el-col>
        <el-col :span="2" :offset="1">
          <el-button type="danger" @click="dialog=true">发布文章</el-button>
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
        <MarkdownPro
          :autoSave="true"
          :interval="60000"
          @on-save="saveDrafts"
          style="margin-top: 20px;"
          ref="markdownEditor"
          v-model="draft.dtext"
          :language="language"
          :toolbars="options"/>

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
        this.article.aHtml = this.$refs.markdownEditor.html
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
            "message": "标题不能少于十个字"
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
              this.$router.push("/")
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
              this.$router.push("/")
            }
          })
        }
      },
      saveDrafts() {
        let title = this.draft.dtitle
        let text = this.draft.dtext
        let html = this.$refs.markdownEditor.html
        this.draft["dtitle"] = title
        this.draft['dtext'] = text
        this.draft["dhtml"] = html


        if (title === undefined || title.length < 5) {
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
