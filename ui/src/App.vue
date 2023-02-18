<template>
  <div id="app">
    <a-layout>
      <a-layout-header><div class="header"></div></a-layout-header>
      <a-layout-content><div class="body">
        <SearchPanel/>
      </div></a-layout-content>
      <a-layout-footer>2018 by jwang84</a-layout-footer>
    </a-layout>
  </div>
</template>

<script>
/* eslint-disable */
import SearchPanel from './components/SearchPanel.vue'


export default {
  name: 'app',
  components: {
    SearchPanel
  }
}
</script>

<style>
#app{
  text-align: center;
  background-color: #F6E8E7;
}

.ant-layout-header{
  background-image:url("./assets/images/background3.jpg") !important;
  height: 75px !important;
}

.body{
  min-height: 100vh;
  background-color: #F6E8E7;
}

.ant-input {
  font-size: 16px !important;
}
</style>
