<template>
  <div class="search">
    <search-songs></search-songs>
  </div>
</template>

<script>
import SearchSongs from 'components/search/SearchSongs'
import { mapGetters } from 'vuex'
import mixin from '../mixins'

export default {
  name: 'search',
  mixins: [mixin],
  components: {
    SearchSongs,
  },
  data () {
    return {
      toggle: 'Songs',
    }
  },
  computed: {
    ...mapGetters([
      'searchword'
    ])
  },
  watch: {
    searchword: function () {
      this.getSong()
    }
  },
  methods: {
    // 切换组件
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/search.scss';
</style>
