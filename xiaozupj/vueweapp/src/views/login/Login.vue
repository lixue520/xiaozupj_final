<template>
    <div>
      <el-card class="login-form-layout">
        <el-form
            class="login-form"
            autocomplete="on"
            :model="loginForm"
            ref="loginForm"
            label-position="left"
            :rules="rules"
        >
          <div style="text-align: center">
          </div>
          <h2 class="login-title color-main">华莱士后台管理系统</h2>
          <el-form-item class="abc" prop="username">
            <svg-icon icon="user" class="svg-container"></svg-icon>
            <el-input
                name="username"
                type="text"
                v-model="loginForm.username"
                autocomplete="on"
                placeholder="请输入用户名"
            >
            </el-input>
          </el-form-item>
          <el-form-item prop="password">
            <svg-icon icon="password" class="svg-container"></svg-icon>
            <el-input
                name="password"
                :type="passwordType"
                v-model="loginForm.password"
                autocomplete="on"
                placeholder="请输入密码"
            >
            </el-input>
            <svg-icon :icon="passwordType === 'password' ? 'eye' : 'eye-open'"
                      @click="changeState"></svg-icon>
          </el-form-item>
          <el-form-item style="margin-bottom: 60px">
            <el-button
                style="width: 100%"
                type="primary"
                :loading="loading"
                @click="login"
            >登录</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
</template>

<script  >
const {ElMessage} = require("element-plus");
export default {
  name: "LoginPage",
  data() {
    return {
      loginForm: {
        username: "liwei",
        password: "lw1234"
      },
      passwordType:"password",
      rules:{
        username: [
          {
            required: true,
            message: 'Please input Activity name',
            trigger: 'blur'
          }
        ],
        password: [
          {
            required: true,
            message: 'Please input Activity name',
            trigger: 'blur'
          }
        ]

      }
    }
  },
  methods: {
    login() {
      // 1、拿到username和password
      // 2、使用axios发送登录请求给后端（SpringBoot）

      console.log("用户密码：",this.loginForm)
      this.$api.login.login("/login", this.loginForm)
          .then(res => {
            console.log("服务器返回的结果",res)
            // vuex（状态管理）
            //把token给存储起来
            console.log(res.token);
            sessionStorage.setItem("token", res.token);
            // 跳转到页面之前要调用后台的菜单接口，获取当前用户的菜单
            // 再次发请求，但是发请求之前，要带上token
            this.$api.menu.findNavTree("menu/findNavTree", {'userName': this.loginForm.username})
                .then(res => {
                  console.log("当前用户的菜单：", res);

                  //this.$store.commit('setNavTree',res);
                  // this.$store.dispatch('setNavTree',{res});//分发完毕后
                  // 同步的话：下面还有其他代码，就暂时无法执行
                  this.$store.dispatch({// 给全局属性赋值，dispatch是异步赋值
                    type: 'setNavTree',//这里type是个函数，必须和actions中的一样
                    data: res
                  })
                  return new Promise((resolve, reject) => {
                    let a = false;
                    resolve(this.loginForm.username);
                    if (a) {
                      reject("出错了");
                    }
                  })
                }).then(res => {
              console.log("准备获取用户的权限：", res);
              this.$api.user.findPermissions("user/findPermissions", {'name': res})
                  .then(res => {
                    console.log(this.loginForm.username + "的权限：" + res);

                    this.$store.commit('setPerms',res);
                  })
            })
            this.$router.push("/index");
          }).catch(err => {
        console.log(err);
        this.$router.push("/");//跳到登录页面
      })
    },
    changeState() {
      if (this.passwordType  === 'password') {
        this.passwordType = 'text'
      } else {
        this.passwordType = 'password'
      }
    },
  }
}

</script>

<style lang="scss"  scoped>
.login-form-layout {
  position: absolute;
  left: 0;
  right: 0;
  width: 360px;
  margin: 140px auto;
  border-top: 10px solid #409eff;


  .login-form {
    position: relative;

    padding: 70px 35px 0;


    ::v-deep .el-form-item {
      border: 1px solid rgb(255,255,255);

      border-radius: 5px;

    }
      ::v-deep .el-input {
        display: inline-block;
        height: 47px;
        width: 80%;

        input {
          background: transparent;
          border: 0px solid #ffffff;
          -webkit-appearance: none;

          height: 47px;

        }
      }

  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    //color:gray;
    vertical-align: middle;
    display: inline-block;
  }

}
.login-title {
  text-align: center;
}


</style>