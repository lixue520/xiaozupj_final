<template>
  <div class="index">
    <Header></Header>
    <side-bar></side-bar>
<!--    路由入口-->
    <router-view></router-view>
  </div>

</template>

<script>
import Header from "@/views/Header";
import SideBar from "@/views/sideBar/SideBar";
import Foot from "@/views/Foot";


export default {
  name: "IndexPage",
  components:{
    Foot,
    Header,
    SideBar,

  }
}
</script>

<style scoped>
  .index{
    width: 100%;
    height: 680px;
  }
</style>