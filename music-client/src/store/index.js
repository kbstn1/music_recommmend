import Vue from 'vue'
import Vuex from 'vuex'
import configure from './configure'
import user from './user'
import song from './song'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    configure,
    user,
    song
  }
})