<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="教室：" >
        <el-select v-model="queryParam.classroomName" clearable>
          <el-option v-for="item in classrooms" :key="item.id" :value="item.id" :label="item.name"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
      <el-table-column prop="id" label="Id" width="100" />
      <el-table-column prop="classroomName" label="教室名称" />
      <el-table-column prop="userName" label="学生" width="100px" />
      <el-table-column prop="startTime" label="开始时间" >
        <template slot-scope="{ row }">
          {{ formatTimestamp(row.startTime) }}
        </template>
      </el-table-column>
      <el-table-column prop="endTime" label="结束时间" >
        <template slot-scope="{ row }">
          {{ formatTimestamp(row.startTime) }}
        </template>
      </el-table-column>
      <el-table-column prop="aStatus" label="出勤情况" width="100px">
        <template slot-scope="{ row }">
          <!-- 根据 aStatus 的值动态绑定不同的 class -->
          <span :class="getStatusClass(row.aStatus)">{{ getStatusText(row.aStatus) }}</span>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total > 0" :total="total" :page.sync="queryParam.pageIndex" :limit.sync="queryParam.pageSize"
                @pagination="search" />
  </div>
</template>

<script>
import { mapGetters, mapActions, mapState } from 'vuex'
import Pagination from '@/components/Pagination'
import attendanceApi from '@/api/attendance'

export default {
  components: { Pagination },
  data () {
    return {
      queryParam: {
        id: null,
        classroomName: null,
        userName: '',
        startTime: '',
        endTime: '',
        pageIndex: 1,
        pageSize: 10
      },
      listLoading: false,
      tableData: [],
      total: 0
    }
  },
  created () {
    this.initClassroom()
    this.search()
  },
  methods: {
    search () {
      this.listLoading = true
      attendanceApi.pageList(this.queryParam).then(data => {
        const re = data.response
        this.tableData = re.list
        this.total = re.total
        this.queryParam.pageIndex = re.pageNum
        this.listLoading = false
      })
    },
    submitForm () {
      this.queryParam.pageIndex = 1
      this.search()
    },
    formatTimestamp (timestamp) {
      // 将接收到的时间戳转换为 JavaScript 的 Date 对象
      const date = new Date(timestamp)
      // 使用 Date 对象中的方法获取可读的日期时间格式
      return date.toLocaleString()
    },
    getStatusText(status) {
      if (status === 0) return '缺勤'
      else if (status === 1) return '出勤'
      else if (status === 2) return '迟到'
      else return '未知状态'
    },
    getStatusClass(status) {
      if (status === 0) return 'absent' // 缺勤
      else if (status === 1) return 'present' // 出勤
      else if (status === 2) return 'late' // 迟到
      else return 'unknown' // 未知状态
    },
    ...mapActions('classroom', { initClassroom: 'initClassrooms' })
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapGetters('classroom', ['classrooms']),
    ...mapGetters('classroom', ['classroomEnumFormat']),
    ...mapState('classroom', { classrooms: state => state.classrooms }),
    ...mapState('enumItem', {
      idEnum: state => state.attendance.id,
      classroomNameEnum: state => state.attendance.classroomName,
      userNameEnum: state => state.attendance.userName,
      startTimeEnum: state => state.attendance.startTime,
      endTimeEnum: state => state.attendance.endTime
    })
  }
}
</script>
<style scoped>
/* 定义不同状态对应的颜色 */
.absent {
  color: red; /* 缺勤显示红色 */
}
.present {
  color: green; /* 出勤显示绿色 */
}
.late {
  color: #ff9900; /* 迟到显示深黄色 */
}
.unknown {
  color: black; /* 未知状态显示黑色 */
}
</style>
