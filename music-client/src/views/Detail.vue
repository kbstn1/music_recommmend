<template>
  <div class="song-detail">
    <h1 class="detail-title">{{songName}}</h1>
    <div class="detail-body">
      <div class="body-left">
        <img :src="attachImageUrl(pic)" alt="">
      </div>
      <div class="body-right">
        <div>演唱：{{singerName}}</div>
        <div>介绍：{{introduction}}</div>
      </div>
    </div>
    <comment :playId="$route.params.id"></comment>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import mixin from '../mixins'
import Comment from '../components/Comment'
import { getSongOfId } from 'api/index'

export default {
  name: 'detail',
  mixins: [mixin],
  components: {
    Comment
  },
  data () {
    return {
      songName: '',
      singerName: '',
      pic: '',
      introduction: ''
    }
  },
  computed: {
    ...mapGetters([
      'curTime',
      'id', // 歌曲ID
      'listOfSongs', // 存放的音乐
      'listIndex' // 当前歌曲在歌曲列表的位置
    ])
  },
  created () {
    getSongOfId(this.$route.params.id)
    .then(res=>{
      console.log(res)
      this.songName = res[0].name
      this.singerName = res[0].singerName
      this.introduction = res[0].introduction
      this.pic = res[0].pic
    })
    .catch(err=>{
      this.notify('查看歌曲详情错误', 'error')
    })
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/detail.scss';
</style>
