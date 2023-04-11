<template>
  <div class="main">
<!--    接受路由-->
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "MainContent"
}
</script>

<style scoped>
  .main{
    width: 85%;
    height: 80%;
    background-color: rgb(255, 255, 255);
    float: left;
  }
</style>