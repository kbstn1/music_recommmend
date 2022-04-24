import { selectByKeyword, getCollectionOfUser, addPlayRecord } from '../api/index'
import { mapGetters } from 'vuex'

const mixin = {
  computed: {
    ...mapGetters([
      'userId',
      'loginIn'
    ])
  },
  methods: {
    // 提示信息
    notify(title, type) {
      this.$notify({
        title: title,
        type: type
      })
    },
    // 获取图片信息
    attachImageUrl(srcUrl) {
      return srcUrl ? this.$store.state.configure.HOST + srcUrl || '../assets/img/user.jpg' : ''
    },
    attachBirth(val) {
      let birth = String(val).match(/[0-9-]+(?=\s)/)
      return Array.isArray(birth) ? birth[0] : birth
    },
    // 得到名字后部分
    replaceFName(str) {
      let arr = str.split('-')
      return arr[1]
    },
    // 得到名字前部分
    replaceLName(str) {
      let arr = str.split('-')
      return arr[0]
    },
    // 播放
    toplay: function (id, url, pic, index, name, singerName) {
      this.$store.commit('setId', id)
      this.$store.commit('setListIndex', index)
      this.$store.commit('setUrl', this.$store.state.configure.HOST + url)
      this.$store.commit('setpicUrl', this.$store.state.configure.HOST + pic)
      this.$store.commit('setTitle', name+'-'+singerName)
      this.$store.commit('setArtist', singerName)
      //显示已收藏
      if (this.loginIn) {
        this.$store.commit('setIsActive', false)
        getCollectionOfUser(this.userId)
          .then(res => {
            for (let item of res) {
              if (item.id === id) {
                this.$store.commit('setIsActive', true)
                break
              }
            }
          })
          .catch(err => {
            console.log(err)
          })
        let params = new URLSearchParams()
        params.append('songId', id)
        params.append('userId', this.userId)
        addPlayRecord(params)
        .then(res=>{
          console.log(res)
        })
        .catch(err=>{
          this.notify('收集播放数据失败','error')
        })
      }
    },
    // 搜索音乐
    getSong() {
      if (!this.$route.query.keywords) {
        this.$store.commit('setListOfSongs', [])
        return
      }
      selectByKeyword(this.$route.query.keywords)
      .then(res => {
        console.log(res)
        if (!res.length) {
          this.$store.commit('setListOfSongs', [])
          this.notify('系统暂无该歌曲', 'warning')
        } else {
          this.$store.commit('setListOfSongs', res)
        }
      })
      .catch(err => {
        console.log(err)
      })
    }
  }
}

export default mixin
