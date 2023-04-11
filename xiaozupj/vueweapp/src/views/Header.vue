<template>
  <el-header class="common-header flex-float">
    <div class="flex">
      <img class="logo" src="../img/logo2.webp" alt="">
      <h2 class="title">华莱士后台管理系统</h2>
    </div>
    <el-button @click="loginOut" type="warning" style="margin: 15px 0px 0px 1000px">退出</el-button>
  </el-header>
</template>

<script >
import { Delete, Edit, Search, Share, Upload } from '@element-plus/icons-vue'
export default {
  name: "HeaderPage",
  methods:{
    loginOut() {
      this.$router.push('/')

    }

  }
}
</script>

<style scoped>

/*.el-container{*/
/*  height: 100vh;*/
/*  overflow: hidden;*/
/*}*/
/*.flex{*/
/*  position: relative;*/
/*  width: 100%;*/
/*  height: 5%;*/
/*}*/
.common-header{

  position: relative;
  overflow: hidden;
  width: 100%;
  height: 7%;
  background: rgb(33, 150, 243);
}

.logo{
  width: 50px;
  float: left;
  margin-left: 35px;
}
.title{
  color: white;
  float: left;
  margin-left: 30px;
  margin-top: 10px;

}
/*.el-main{*/
/*  background: #efefef;*/
/*}*/

/*.header{*/
/*  width: 100%;*/
/*  height: 5%;*/
/*  background-color: rgb(33, 150, 243);*/
/*}*/
/*.navbar-item {*/
/*  display: inline-block;*/
/*  margin-left: 18px;*/
/*  font-size: 22px;*/
/*  color: #5a5e66;*/
/*  box-sizing: border-box;*/
/*  cursor: pointer;*/
/*}*/
</style>