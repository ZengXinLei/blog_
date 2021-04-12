<template>
  <div class="app-container">
    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column type="index" />

      <el-table-column width="180px" align="center" label="日期">
        <template slot-scope="scope">
          <span>{{ getTime(scope.row.aTime) }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="作者">
        <template slot-scope="scope">
          <span>{{ scope.row.user.uName }}</span>
        </template>
      </el-table-column>

      <el-table-column width="100px" label="标签">
        <template slot-scope="scope">
          <span>{{ scope.row.aType }}</span>
        </template>
      </el-table-column>

      <el-table-column min-width="100px" label="标题">
        <template slot-scope="{row}">
          <a :href="mySettings.url+'detail/'+row.aId" target="view" class="link-type">
            <span>{{ row.aTitle.length>=15?row.aTitle.substring(0,14)+"...":row.aTitle }}</span>
          </a>
        </template>
      </el-table-column>

      <el-table-column min-width="20px" label="浏览">
        <template slot-scope="{row}">

          <span>{{ row.aReadCount }}</span>

        </template>
      </el-table-column>

      <el-table-column min-width="20px" label="评论">
        <template slot-scope="{row}">

          <span>{{ row.aReviewCount }}</span>

        </template>
      </el-table-column>

      <el-table-column min-width="20px" label="点赞">
        <template slot-scope="{row}">

          <span>{{ row.aClickCount }}</span>

        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="190">
        <template slot-scope="scope">
          <router-link :to="'/admin/components/edit/'+scope.row.aId">
            <el-button style="float: left" type="primary" size="mini" icon="el-icon-edit">
              编辑
            </el-button>
          </router-link>
          <el-button style="float: left" type="danger" size="mini" icon="el-icon-delete" @click="deleteArticle(scope.row.aId)">
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
import { parseTime } from '@/utils/parseTime'
import { getArticleList, getArticlePages, deleteArticle } from '@/api/article'
import ArticleTags from '@/components/alert/article-tags'

export default {
  name: 'Essay',
  components: { ArticleTags, Pagination },
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
  computed: {
    getTime() {
      return function(t) {
        return parseTime(t)
      }
    }
  },
  created() {
    this.getList()
    getArticlePages().then(res => {
      this.total = res.data
      console.log(this.total)
    })
  },
  methods: {

    deleteArticle(aId) {
      deleteArticle(aId).then((res) => {
        this.$notify({
          message: res.data.msg,
          type: res.data.type
        })
        if (res.data.type === 'success') {
          for (let i = 0; i < this.list.length; i++) {
            if (this.list[i].aId === aId) {
              this.list.splice(i, 1)
            }
          }
        }
      })
    },
    getList() {
      this.listLoading = true
      getArticleList((this.listQuery.page - 1) * this.listQuery.limit, this.listQuery.limit).then(response => {
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
