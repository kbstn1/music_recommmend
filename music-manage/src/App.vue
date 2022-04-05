<template>
  <div id="app">
    <song-audio></song-audio> 
    <router-view/>
  </div>
</template>

<script>
import SongAudio from 'components/SongAudio'

export default {
  name: 'App',
  components: {
    SongAudio
  }
}
</script>

<style lang="scss">
</style>
