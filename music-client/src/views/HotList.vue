<template>
  <div class="hot-page">
    <album-content :songList="contentList">
      <template slot="title">热门歌曲</template>
    </album-content>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import mixin from '../mixins'
import AlbumContent from '../components/AlbumContent'
import { getHotSong } from 'api/index'

export default {
  name: "hot-list",
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
  data() {
    return {
      contentList: []
    }
  },
  created() {
    this.getHotSong()
  },
  methods: {
    getHotSong(){
      getHotSong()
        .then(res=>{
          this.contentList = res
          this.$store.commit('setListOfSongs', this.contentList)
        })
        .catch(err=>{
          this.notify('获取热门歌曲失败','error')
        })
    }
  }
  
}
</script>

<style lang="scss" scoped>
@import '../assets/css/hot-list.scss';
</style>