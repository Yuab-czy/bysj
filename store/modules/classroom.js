import classroomApi from '@/api/classroom'

const state = {
  classrooms: []
}

const getters = {
  classroomById: (state) => (id) => {
    return state.classrooms.find(classroom => classroom.id === id)
  }
}

const actions = {
  initClassrooms ({ commit }, action) {
    classroomApi.list().then(re => {
      commit('setClassrooms', re.response)
      if (action !== undefined) {
        action()
      }
    })
  }
}

const mutations = {
  setClassrooms: (state, classrooms) => {
    state.classrooms = classrooms
  }
}

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
