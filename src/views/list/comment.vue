<template>
  <div  class="app-container">
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column type="index">
      </el-table-column>

      <el-table-column width="180px"  align="center" label="日期">
        <template slot-scope="scope">
          <span>{{getTime(scope.row.cTime) }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="作者">
        <template slot-scope="scope">
          <span>{{ scope.row.tourist.tName }}</span>
        </template>
      </el-table-column>

      <el-table-column width="150px" label="所在帖子">
        <template  slot-scope="scope">
          <a v-if="scope.row.article!==null" :href="'http://www.miluyiguan.club/detail/'+scope.row.article.aId">{{scope.row.article.aTitle.length>=10?scope.row.article.aTitle.substring(0,9)+"....":scope.row.article.aTitle}}</a>
          <a v-else href="http://www.miluyiguan.club/contact" target="view">留言墙</a>
        </template>
      </el-table-column>



      <el-table-column min-width="100px" label="内容">
        <template slot-scope="{row}">
          <el-tooltip class="item" effect="dark" :content="row.cText" placement="top">
            <span>{{row.cText.length>=20?row.cText.substring(0,19)+"...":row.cText}}</span>
          </el-tooltip>
        </template>
      </el-table-column>





      <el-table-column align="center" label="操作" width="100px">
        <template slot-scope="scope">
<!--          <router-link :to="'/admin/components/edit/'+scope.row.aId">-->
<!--            <el-button style="float: left" type="primary"  size="mini" icon="el-icon-edit">-->
<!--              编辑-->
<!--            </el-button>-->
<!--          </router-link>-->
          <el-button style="float: left" type="danger" size="mini" icon="el-icon-delete" @click="deleteComment(scope.row.cId)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>


    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
  </div>
</template>

<script>
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
  import {parseTime} from "@/utils/parseTime"
  import {getArticleList,getArticlePages,deleteArticle} from "@/api/article";
  import ArticleTags from "@/components/alert/article-tags";
  import {deleteComment, getAllComments, getAllCommentsPage} from "@/api/comment";

  export default {
    name: 'comment',
    components: {ArticleTags, Pagination },
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'info',
          deleted: 'danger'
        }
        return statusMap[status]
      }
    },
    data() {
      return {
        list: [],
        total: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          limit: 20
        }
      }
    },
    created() {
      this.getList()
      getAllCommentsPage().then(res=>{
        this.total=res.data
        console.log(this.total)
      })
    },
    computed: {
      getTime(){
        return function (t) {
          return parseTime(t)

        }
      }
    },
    methods: {

      deleteComment(cId){
        deleteComment(cId).then((res)=>{
          this.$notify({
            message:res.data.msg,
            type:res.data.type
          })
          if(res.data.type==="success"){
            for (let i = 0; i < this.list.length; i++) {
              if(this.list[i].cId===cId){
                this.list.splice(i,1)
              }
            }
          }
        })
      },
      getList() {
        this.listLoading = true
        getAllComments((this.listQuery.page-1)*this.listQuery.limit,this.listQuery.limit).then(response => {
          this.list = response.data
          this.listLoading = false
        })
      }
    }
  }
</script>

<style scoped>
  .edit-input {
    padding-right: 100px;
  }
  .cancel-btn {
    position: absolute;
    right: 15px;
    top: 10px;
  }
</style>
