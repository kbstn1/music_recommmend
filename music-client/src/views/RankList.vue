<template>
  <div class="rank-list">
    <ul class="list-header">
      <li
        v-for="(item, index) in rankTypeList"
        :key="index"
        :class="{active: item.type === type}"
        @click="changeList(item.type)">
        {{item.name}}
      </li>
    </ul>
    <album-content :songList="contentList">
      <template slot="title">{{rankName}}</template>
    </album-content>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import mixin from '../mixins'
import AlbumContent from '../components/AlbumContent'
import { getPlayRank, getCollectRank, getDownloadRank } from 'api/index'

export default {
  name: 'rank-list',
  mixins: [mixin],
  components: {
    AlbumContent
  },
  computed: {
    ...mapGetters([
      'userId',
      'id',
      'listOfSongs' // 存放的音乐
    ])
  },
  data () {
    return {
      rankTypeList: [{name:'按播放排名',type:0},{name:'按收藏排名',type:1},{name:'按下载排名',type:2}],
      type: 0,
      rankName: '播放排行',
      contentList: []
    }
  },
  created() {
    this.getRankList()
  },
  methods: {
    getRankList() {
      if(this.type == 0) {
        getPlayRank()
        .then(res=>{
          this.contentList = res
        })
        .catch(err=>{
          this.notify('获取排行榜失败','error')
        })
      }
      if(this.type == 1) {
        getCollectRank()
        .then(res=>{
          this.contentList = res
        })
        .catch(err=>{
          this.notify('获取排行榜失败','error')
        })
      }
      if(this.type == 2) {
        getDownloadRank()
        .then(res=>{
          this.contentList = res
        })
        .catch(err=>{
          this.notify('获取排行榜失败','error')
        })
      }
    },
    changeList(type) {
      this.type = type
      if(type == 0) {
        this.rankName = '播放排行'
      }
      if(type == 1) {
        this.rankName = '收藏排行'
      }
      if(type == 2) {
        this.rankName = '下载排行'
      }
      this.getRankList()
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/rank-list.scss';
</style>