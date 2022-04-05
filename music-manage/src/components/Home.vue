<template>
  <div>
    <my-header></my-header>
    <my-aside></my-aside>
    <div class="content-box" :class="{'content-collapse':collapse}">
      <div class="content">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import MyHeader from 'components/MyHeader'
import MyAside from 'components/MyAside'
import _ctrEvent from 'assets/js/ctr-event'

export default {
  components: {
    MyHeader,
    MyAside
  },
  data () {
    return {
      collapse: false
    }
  },
  created () {
    _ctrEvent.$on('collapse', msg => {
      this.collapse = msg
    })
  }
}
</script>

<style>
</style>