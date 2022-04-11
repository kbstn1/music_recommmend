<template>
  <div class="home">
    <!--轮播图-->
    <swiper :swiperList="swiperList"/>
    <div class="content">
      <i>音乐是比一切智慧、一切哲学更高的启示。</i>
    </div>
  </div>
</template>

<script>
import { swiperList } from '../assets/data/swiper'
import Swiper from '../components/Swiper'
import ContentList from '../components/ContentList'
import { getSongList, getAllSinger } from '../api/index'
import {mapGetters} from 'vuex'

export default {
  name: 'home',
  components: {
    Swiper,
    ContentList
  },
  data () {
    return {
      swiperList: [] // 轮播图列表
    }
  },
  computed: {
    ...mapGetters([
      'userId'
    ])
  },
  created () {
    this.swiperList = swiperList
    // 获取歌单列表
    // eslint-disable-next-line no-undef
  },
  methods: {

  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/home.scss';
.content {
  margin: 60px auto;
  text-align: center;
  font-family: 'Courier New', Courier, monospace;
  font-size: 60px;
}
</style>
