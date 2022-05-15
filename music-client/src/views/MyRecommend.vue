<template>
  <div class="recommend-page">
    <album-content :songList="contentList">
      <template slot="title">猜你喜欢</template>
    </album-content>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import mixin from '../mixins'
import AlbumContent from '../components/AlbumContent'
import { getRecommendSong } from 'api/index'

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
    this.getRecommendSong()
  },
  methods: {
    getRecommendSong(){
      getRecommendSong({userId: this.userId})
        .then(res=>{
          this.contentList = res
          this.$store.commit('setListOfSongs', this.contentList)
        })
        .catch(err=>{
          this.notify('获取个性化推荐歌曲失败','error')
        })
    }
  }
  
}
</script>

<style lang="scss" scoped>
@import '../assets/css/my-recommend.scss';
</style>