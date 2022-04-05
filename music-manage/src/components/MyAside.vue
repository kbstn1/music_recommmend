<template>
  <div class="sidebar">
    <el-menu
      class="sidebar-el-menu"
      :default-active="$route.path"
      background-color="#334296"
      text-color="#B0B3B2"
      active-text-color="#ffffff"
      unique-opened
      router
    >
      <template v-for="item in items">
          <template>
            <el-menu-item :index="item.index" :key="item.index">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import _ctrEvent from '../assets/js/ctr-event'

export default {
  data () {
    return {
      collapse: false,
      items: [
        {
          icon: 'el-icon-user-solid',
          index: '/home/consumer',
          title: '用户管理'
        },
        {
          icon: 'el-icon-service',
          index: '/home/song',
          title: '歌曲管理'
        }
      ]
    }
  },
  computed: {
    onRoutes () {
      return this.$route.path
    }
  },
  created () {
    _ctrEvent.$on('collapse', msg => {
      this.collapse = msg
    })
  }
}
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  background-color: #334296;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
  width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
  width: 150px;
}
.sidebar > ul {
  height: 100%;
}
</style>
