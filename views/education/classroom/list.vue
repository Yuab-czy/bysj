<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="教室编号：">
        <el-input v-model="queryParam.name" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
        <router-link :to="{path:'/education/classroom/edit'}" class="link-left">
          <el-button type="primary">添加</el-button>
        </router-link>
      </el-form-item>
    </el-form>
    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
      <el-table-column prop="id" label="教室序号" width="90px"/>
      <el-table-column prop="name" label="教室名称" width="120px"/>
      <el-table-column prop="hasNum" label="人数"  width = "120px">
        <template slot-scope="{row}">
          {{row.hasNum}} / {{row.totalNum}}
        </template>
      </el-table-column>
      <el-table-column  label="操作" align="center"  width="160px">
        <template slot-scope="{row}">
          <el-button size="mini" @click="$router.push({path:'/education/classroom/edit',query:{id:row.id}})" >编辑</el-button>
          <el-button size="mini" type="danger"  @click="deleteClassroom(row)" class="link-left">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParam.pageIndex" :limit.sync="queryParam.pageSize"
                @pagination="search"/>
  </div>
</template>

<script>
import Pagination from '@/components/Pagination'
import ClassroomApi from '@/api/classroom'
import { mapGetters, mapState } from 'vuex'

export default {
  components: { Pagination },
  data () {
    return {
      queryParam: {
        id: null,
        name: null,
        hasNum: '',
        totalNum: '',
        pageIndex: 1,
        pageSize: 10
      },
      listLoading: true,
      tableData: [],
      total: 0
    }
  },
  created () {
    this.search()
  },
  methods: {
    submitForm () {
      this.queryParam.pageIndex = 1
      this.search()
    },
    search () {
      this.listLoading = true
      ClassroomApi.pageList(this.queryParam).then(data => {
        const re = data.response
        this.tableData = re.list
        this.total = re.total
        this.queryParam.pageIndex = re.pageNum
        this.listLoading = false
      })
    },
    deleteClassroom (row) {
      let _this = this
      ClassroomApi.deleteClassroom(row.id).then(re => {
        if (re.code === 1) {
          _this.search()
          _this.$message.success(re.message)
        } else {
          _this.$message.error(re.message)
        }
      })
    }
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapState('enumItem', {
      hasNumEnum: state => state.classroom.hasNum,
      totalNumEnum: state => state.classroom.totalNum
    })
  }
}
</script>
