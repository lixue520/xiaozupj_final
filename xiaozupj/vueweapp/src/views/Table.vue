<template>
  <div class="container">
    <el-container>
    <el-header><h1>用户管理</h1>
      <input type="text" v-model="name">
      <input
              v-model="password"
              type="password"
              placeholder="Please input password"
              show-password
      />

      <el-button type="success" @click="addUser">添加</el-button>
      <el-button type="success" @click="getUser">刷新</el-button>
    </el-header>
    </el-container>
    <el-main>
        <table>
          <thead>
          <tr>
            <th>编号</th>
            <th>用户名</th>
            <th>密码</th>
            <th>用户</th>
            <th>操作</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="user in users">

            <template v-if="user.isFlag">
            <td>{{user.id}}</td>
            <td>{{user.name}}</td>
            <td>******</td>
            <td>{{user.nickName}}</td>
            <td>
              <el-button type="primary" @click="user.isFlag=false">修改</el-button>
              <el-button type="danger">删除</el-button>
            </td>
            </template>

            <template v-else>
              <td><input type="text" v-model="user.id" disabled></td>
              <td><input type="text" v-model="user.name" ></td>
              <td><input type="text" v-model="new_password" placeholder="原密码"></td>
              <td><input type="text" v-model="user.nickName" ></td>

                <td>
                  <el-button type="primary" @click="user.isFlag=!user.isFlag,updateUser(user)">保存</el-button>
                  <el-button type="danger">删除</el-button>
                </td>

            </template>
          </tr>
          </tbody>
        </table>
    </el-main>
  </div>
</template>

<script>
import {hasPermission} from "@/permissions";
import {Axios as axios} from "axios";

export default {
  name: "UserPage",
  data() {
    return {
      users:[],
      new_password:null
    }
  },
  mounted:function(){
    this.getUser();
  },
  methods: {
    getUser(){
      this.$api.user.getUser("user/getUser").then(
              res=>{
                console.log(res);
                let i;
                for(i=0;this.users[i];){
                  this.users.splice(0,1);
                }
                for(i=0;res[i];i++)
                this.users.push({

                  id:res[i].id,
                  name:res[i].name,
                  password:res[i].password,
                  nickName:res[i].nickName,
                  isFlag:true,
                }
              )
              }
      )
    },
    addUser(){
      let params = {
        name:this.name,
        password:this.password,
      }
      console.log(params.name);
      console.log(params.password);
      console.log(params);
      this.$api.user.addUser("user/addUser",{"name":params.name,"password":params.password,"nick_name":params.name}).then(res=>{
        this.getUser();
        console.log(res);
      })
    },
    updateUser(user){
      user.password=this.new_password;
      this.$api.user.updateUser("user/updateUser",{"id":user.id,"name":user.name,"password":user.password,"nick_name":user.nickName}).then(
              res=>{
                this.getUser();
                console.log(res);
              }
      )
    }
  },
  computed: {

  }

}
</script>

<style scoped>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}

td {
  padding-left: 10px;
  padding-right: 10px;
}
input{
  width: 200px;
  height: 20px;

}
.inputWidth {
  width: 20px;
}
</style>