<template>
  <div class="app-container">

    <el-form :model="form" ref="form" label-width="100px" v-loading="formLoading">
      <el-form-item label="教室名称：" required>
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="可容纳人数：" required>
        <el-input v-model="form.totalNum"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { mapActions, mapGetters, mapState } from 'vuex'
import classroomApi from '@/api/classroom'

export default {
  data () {
    return {
      form: {
        id: null,
        name: '',
        hasNum: null,
        totalNum: ''
      },
      formLoading: false
    }
  },
  created () {
    let id = this.$route.query.id
    let _this = this
    if (id && parseInt(id) !== 0) {
      _this.formLoading = true
      classroomApi.select(id).then(re => {
        _this.form = re.response
        _this.formLoading = false
      })
    }
  },
  methods: {
    submitForm () {
      let _this = this
      this.formLoading = true
      classroomApi.edit(this.form).then(data => {
        if (data.code === 1) {
          _this.$message.success(data.message)
          _this.delCurrentView(_this).then(() => {
            _this.$router.push('/education/classroom/list')
          })
        } else {
          _this.$message.error(data.message)
          _this.formLoading = false
        }
      }).catch(e => {
        _this.formLoading = false
      })
    },
    resetForm () {
      let lastId = this.form.id
      this.$refs['form'].resetFields()
      this.form = {
        id: null,
        name: '',
        hasNum: '0',
        totalNum: ''
      }
      this.form.id = lastId
    },
    ...mapActions('tagsView', { delCurrentView: 'delCurrentView' })
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
