<template>
  <div class="app-container">
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
<!--      <el-table-column type="index" />-->

      <el-table-column width="100px"  align="center" label="名称">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column  align="center" label="值">
        <template slot-scope="scope">
          <span>{{ scope.row.value }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="时间">
        <template slot-scope="scope">
          <span>{{ getTime(scope.row.time) }}</span>
        </template>
      </el-table-column>


      <el-table-column align="center" label="操作" width="190px">
        <template slot-scope="scope">
          <!--          <router-link :to="'/admin/components/edit/'+scope.row.aId">-->
          <!--            <el-button style="float: left" type="primary"  size="mini" icon="el-icon-edit">-->
          <!--              编辑-->
          <!--            </el-button>-->
          <!--          </router-link>-->
          <el-button style="float: left" type="success" size="mini" icon="el-icon-edit" @click="edit(scope.row.name)">
            编辑
          </el-button>
          <el-button style="float: left" type="danger" size="mini" icon="el-icon-delete" @click="deleteConfig(scope.row.name)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

<!--    <div  style="display:flex;flex-direction: row;justify-content: space-between">-->
      <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" style="float: left" />

      <el-button type="warning" icon="el-icon-plus" size="mini" style="float: right;margin-top: 67px" @click="add">添加</el-button>
<!--    </div>-->
    <el-dialog
      :visible.sync="dialog"
      :title="config.name||'添加值'"
      width="30%"
    >

      <el-input v-model="config.name" placeholder="请输入名称" style="margin-top: 0px; "></el-input>

      <el-input v-model="config.value" placeholder="请输入值" style="margin-top: 10px; "></el-input>


      <el-button @click="confirmModification" size="small" type="danger" style="margin-top: 20px;">确认修改</el-button>

    </el-dialog>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import {parseTime} from "@/utils/parseTime";
import {deleteComment, getAllComments, getAllCommentsPage} from "@/api/comment";
import {deleteConfig, getConfig, getConfigCount, setConfig} from "@/api/config";

export default {
name: "index",
  components:{Pagination},
  data(){
    return{
      list: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20
      },
      dialog:false,
      config:{
        name:"",
        value:""
      },
      isAdd:false
    }
  },
  computed: {
    getTime() {
      return function(t) {
        return parseTime(t)
      }
    }
  },
  created() {
    this.getList()
    getConfigCount().then(res => {
      this.total = res.data
      console.log(this.total)
    })
  },
  methods: {

    deleteConfig(name) {
      deleteConfig(name).then((res) => {
        this.$notify({
          message: "删除成功",
          type:"success"
        })
        // if (res.data.type === 'success') {
          for (let i = 0; i < this.list.length; i++) {
            if (this.list[i].name === name) {
              this.list.splice(i, 1)
            }
          }
        // }
      })
    },
    getList() {
      this.listLoading = true
      getConfig((this.listQuery.page - 1) * this.listQuery.limit, this.listQuery.limit).then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    edit(name){
      this.config.name=name
      this.dialog=true
      this.isAdd=false
    },
    add(){
      this.dialog=true
      this.isAdd=true
      this.config={
        name:"",
        value:""
      }
    },
    confirmModification(){
      setConfig(this.config).then(res=>{
        if(res.data.code===200)
        {
          this.dialog=false

          this.$notify({
            message: res.data.msg,
            type: "success"
          })
          let config=this.list.filter(e=>e.name===this.config.name)
          if(config.length>0){
            config[0].value=this.config.value
          }else {
            this.list.push(this.config)
          }
        }else {
          this.$notify({
            message: res.data.msg,
            type: "error"
          })
        }


      })
    }
  }
}
</script>

<style scoped>

</style>
