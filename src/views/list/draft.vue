<template>
  <div class="app-container">
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column type="index">
      </el-table-column>

      <el-table-column width="180px"  align="center" label="日期">
        <template slot-scope="scope">
          <span>{{getTime(scope.row.dtime) }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="作者">
        <template slot-scope="scope">
          <span>{{ scope.row.user.uName }}</span>
        </template>
      </el-table-column>

      <el-table-column width="100px" label="标签">
<!--        <template slot-scope="scope">-->
<!--          <svg-icon v-for="n in +scope.row.importance" :key="n" icon-class="star" class="meta-item__icon" />-->
<!--        </template>-->
      </el-table-column>



      <el-table-column min-width="300px" label="标题">
        <template slot-scope="{row}">
          <router-link :to="'/admin/components/edit/'+row.did" class="link-type">
            <span>{{ row.dtitle.length>=15?row.dtitle.substring(0,14)+"...":row.dtitle }}</span>
          </router-link>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="190">
        <template slot-scope="scope">
          <router-link :to="'/admin/components/edit/'+scope.row.did">
            <el-button style="float: left" type="primary"  size="mini" icon="el-icon-edit">
              编辑
            </el-button>
          </router-link>
          <el-button style="float: left" type="danger" size="mini" icon="el-icon-delete" @click="deleteDraft(scope.row.did)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
  </div>
</template>

<script>
import { getDraftList } from '@/api/draft'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import {parseTime} from "@/utils/parseTime"
import {deleteDraft,getDraftPages} from "@/api/draft";

export default {
  name: 'draft',
  components: { Pagination },
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
    getDraftPages().then(res=>{
      this.total=res.data
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

    deleteDraft(did){
      deleteDraft(did).then((res)=>{
        this.$notify({
          message:res.data.msg,
          type:res.data.type
        })
        if(res.data.type==="success"){
          for (let i = 0; i < this.list.length; i++) {
            if(this.list[i].did===did){
              this.list.splice(i,1)
            }
          }
        }
      })
    },
    getList() {
      this.listLoading = true
      getDraftList((this.listQuery.page-1)*this.listQuery.limit,this.listQuery.limit).then(response => {
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
