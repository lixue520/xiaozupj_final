<template>


  <div>
    <!-- 面包屑 -->
    <el-breadcrumb >
      <el-breadcrumb-item :to="{ path: 'indexHello' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>餐品列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!--  白色内容区域-->
    <div class="page_content">

      <div class="input_box">
          <el-input
              class="input_hang"
              v-model="input"
              placeholder="输入餐品编号or名称"

          >
            <template #prepend>
              <el-select v-model="select"  placeholder="分类显示" style="width: 115px">
                <el-option label="美味小食" @click="selectPro" value="美味小食" />
                <el-option label="缤纷饮品" @click="selectPro" value="缤纷饮品" />
                <el-option label="招牌全鸡" @click="selectPro" value="招牌全鸡" />
                <el-option label="精选主食" @click="selectPro" value="精选主食" />
                <el-option label="超值套餐" @click="selectPro" value="超值套餐" />
              </el-select>
            </template>
            <template #append>
              <el-button @click="searchList"><el-icon><Search/></el-icon></el-button>
            </template>
          </el-input>
          <el-button type="primary" @click="showData" style="margin: 0px 0px 8px 80px">全部</el-button>
          <el-button type="primary" @click="addUser" style="margin: 0px 0px 8px 80px">添加新品</el-button>

        </div>
      <!--   表格   -->
      <!-- 表格 -->
      <!--
        el-table  的  data:要展示的数据数组
        el-table-column：列 prop每条数据的对应属性
          label：列标题
          scope.row:相当于一条数据
       -->
      <div class="table">
        <el-table v-loading="loading" :data="userList" height="520" :border="border" style="width: 80% ">
          <el-table-column prop="product_id" label="餐品编号" width="100" />
          <el-table-column prop="product_category" label="类别" width="100" />
          <!--        服务器图片show-->
          <!--        <el-table-column min-width="55" prop="img" label="图片">-->
          <!--          <template v-slot:default="scope">-->
          <!--            <el-image :src="scope.row.img " alt=""/>-->
          <!--          </template>-->
          <!--        </el-table-column>-->
          <!--        本地图片show-->
          <el-table-column min-width="55" prop="img"  label="图片" width="100">
            <template v-slot:default="scope">
              <el-image :src="require('@/img/'+scope.row.img)" style="width: 90px; height: 90px" fit="fill" alt=""/>
            </template>
          </el-table-column>
          <el-table-column prop="price" label="价格" width="100"/>
          <el-table-column prop="product_name" label="名称" width="120"/>
          <el-table-column prop="cost_price" label="成本价" width="100"/>-->
          <el-table-column   label="操作" >
            <template #default="scope">
              <el-button type="primary" @click="editRow(scope.row)">编辑</el-button>
              <el-button type="danger" @click="deleteRow(scope.row)">删除</el-button>
            </template>
          </el-table-column>

        </el-table>
      </div>
    </div>


    <!--    新品弹窗-->
    <el-dialog v-model="dialogFormVisible" destroy-on-close title="添加新品">
      <!--
                表单
                | username | 用户名称 | 不能为空 |
                | password | 用户密码 | 不能为空 |
                | email    | 邮箱     | 可以为空 |
                | mobile   | 手机号   | 可以为空 |
             -->
      <el-form
          :model="formData"
          :rules="rules"
      >
        <el-form-item label="餐品编号" prop="product_id">
          <el-input v-model="formData.product_id" placeholder="请输入餐品编号" />
        </el-form-item>
        <el-form-item label="餐品类别" prop="product_category">
          <el-select v-model="value" clearable placeholder="Select">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            />
          </el-select>
<!--          <el-input v-model="formData.product_category" placeholder="请输入餐品类别" />-->
        </el-form-item>
        <el-form-item label="餐品名称" prop="product_name">
          <el-input v-model="formData.product_name" placeholder="请输入餐品名称" />
        </el-form-item>
        <el-form-item label="餐品价格" prop="price">
          <el-input v-model="formData.price" placeholder="请输入餐品价格" />
        </el-form-item>
        <el-form-item label="成本价" prop="cost_price">
          <el-input v-model="formData.cost_price" placeholder="请输入餐品成本价" />
        </el-form-item>
        <el-form-item label="图片URL" prop="img">
          <el-input v-model="formData.img" placeholder="请输入餐品图片URL" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="flex">
          <el-button @click="hideDialog">取消</el-button>
          <el-button type="primary"   @click="submitForm" >保存</el-button>
        </div>
      </template>
    </el-dialog>
    <el-dialog v-model="dialogFormEVisible" destroy-on-close title="编辑餐品">
      <!--
                表单
                | username | 用户名称 | 不能为空 |
                | password | 用户密码 | 不能为空 |
                | email    | 邮箱     | 可以为空 |
                | mobile   | 手机号   | 可以为空 |
             -->
      <el-form
          :model="formData"
          :rules="rules"
      >
        <el-form-item label="餐品编号" prop="product_id">
          <el-input v-model="formData.product_id" disabled placeholder="请输入餐品编号" />
        </el-form-item>
        <el-form-item label="餐品类别" prop="product_category">
          <el-input v-model="formData.product_category" disabled placeholder="请输入餐品类别" />
        </el-form-item>
        <el-form-item label="餐品名称" prop="product_name">
          <el-input v-model="formData.product_name" placeholder="请输入餐品名称" />
        </el-form-item>
        <el-form-item label="餐品价格" prop="price">
          <el-input v-model="formData.price" placeholder="请输入餐品价格" />
        </el-form-item>
        <el-form-item label="图片" prop="img">
          <el-input v-model="formData.img" placeholder="请输入图片URL" />
        </el-form-item>
        <el-form-item label="成本价" prop="cost_price">
          <el-input v-model="formData.cost_price" placeholder="请输入餐品成本价" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="flex">
          <el-button @click="hideEditDialog">取消</el-button>
          <el-button type="primary"   @click="editSubmitForm" >确定</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>
<script >
const {ElMessageBox} = require("element-plus");
export default {
  name: "foodMange",
  data() {
    return {
      loginForm: {
        username: 'liwei',
        password: 'lw1234'
      },
      userList: [
      ],
      formData:{
        product_id:"",
        product_category:"",
        product_name:"",
        price:"",
        img: "",
        cost_price: "",
      },
      select:"",
      input:"",
      border:true,
      loading:false,
      url:'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
      dialogFormVisible: false,//新增
      dialogFormEVisible: false,//编辑
      value: "",
      options: [
        {
          value: '美味小食',
          label: '美味小食',
        },
        {
          value: '缤纷饮品',
          label: '缤纷饮品',
        },
        {
          value: '招牌全鸡',
          label: '招牌全鸡',
        },
        {
          value: '精选主食',
          label: '精选主食',
        },
        {
          value: '超值套餐',
          label: '超值套餐',
        },
      ],


      rules:{
        product_id:[
          {required:true,message:"此项为必填",trigger:"blur"}
        ],
        product_category:[
          {required:true,message:"此项为必填",trigger:"blur"}
        ],
        product_name:[
          {required:true,message:"此项为必填",trigger:"blur"}
        ],
        price:[
          {required:true,message:"此项为必填",trigger:"blur"}
        ]
      },
    }
  },
  mounted: function () {
    this.showData()
  },
  methods: {
    showData() {
      this.loading = true;
      this.$api.food.getFoodList("food/foodList", {'abc': ""})
          .then(res => {
            console.log("商品items：", res)
            console.log("商品来咯：")
            this.userList = res;
            this.loading = false;
          }).catch(err => {
        console.log(err);
      })
    },
    addUser() {
      this.dialogFormVisible = true
    },
    hideDialog() {
      this.dialogFormVisible = false;
      this.formData = {
        product_id:"",
        product_category:"",
        product_name:"",
        price:"",
        img: "",
        cost_price: "",
      }
    },
    //添加新品
    submitForm() {
      this.formData.product_category = this.value;
      if (this.formData.product_id === "" || this.formData.product_name=== "" || this.formData.product_category=== "" || this.formData.cost_price=== "" || this.formData.img=== "" || this.formData.price=== "") {
        // ElMessage.error('请补充完整餐品信息');
        ElMessage({
          message: '请补充完整餐品信息',
          type: 'error',
          duration: 2000,
        });
      } else {
        console.log("新品添加表单--------->", this.formData);
        this.$api.addFood.addFood("food/addFood", this.formData)
            .then(res => {
              console.log("请求添加新品返回的数据------------>", res)
              console.log("请求添加新品返回的数据product_id-------->", res.product_id)
              if (res.product_id) {
                ElMessage({
                  message: '添加成功',
                  type: 'success',
                  duration: 2000,
                })
                // 隐藏弹窗
                this.dialogFormVisible = false;
                // 清空form
                this.formData = {
                  product_id:"",
                  product_category:"",
                  product_name:"",
                  price:"",
                  img: "",
                  cost_price: "",
                }
                this.value = "";
                // 重新更新列表
                this.loading = true;
                this.showData();
              }
            }).catch(err => {
          console.log(err)
        });
      }
    },
    editRow(row) {
      this.dialogFormEVisible = true;
      console.log("当前table一行的数据--------->", this.formData)

      this.formData = row;
      console.log("当前table一行的数据--------->", this.formData)

    },
    editSubmitForm() {
      console.log("修改", this.formData);
      this.$api.editFood.editFood("food/editFood", this.formData)
          .then(res => {
            console.log("修改后的数据------>", res);
            ElMessage({
              message: '保存成功',
              type: 'success',
              duration: 2000,
            })
            this.dialogFormEVisible = false;
            this.formData = {
              product_id:"",
              product_category:"",
              product_name:"",
              price:"",
              img: "",
              cost_price: "",
            }
          })
    },
    hideEditDialog() {
      // this.dialogFormEVisible = false;
      console.log("取消编辑",this.formData)
      this.formData = {
        product_id:"",
        product_category:"",
        product_name:"",
        price:"",
        img: "",
        cost_price: "",
      }
      this.dialogFormEVisible = false;
      console.log("取消编辑",this.formData)
    },
    searchList() {

      console.log("input内容",this.input)
      if (this.input) {
        this.loading = true;
        this.$api.selectFood.selectFood("food/selectFood", {'parameter': this.input})
            .then(res => {
              ElMessage({
                message: '查询成功',
                type: 'success',
                duration: 2000,
              })

              console.log("选择的数据------->", res);
              this.userList = res;
              this.loading = false;
            });
      } else {
        ElMessage({
          message: '请输入查询信息',
          type: 'error',
          duration: 2000,
        })

      }

    },
    selectPro() {
      this.loading = true;
      console.log("选择了-------", this.select);
      this.$api.optionFood.optionFood("food/optionFood",{'parameter':this.select})
      .then(res => {
        console.log("分来来咯", res);
        this.userList = res;
        this.loading = false;

      })
    },
    deleteRow(row) {
      console.log("删除的行数据",row)
      ElMessageBox.confirm(
          '确定删除该餐品吗?',
          'Warning',
          {
            confirmButtonText: 'OK',
            cancelButtonText: 'Cancel',
            type: 'warning',
          }
      )
          .then(() => {
            this.$api.deleteFood.deleteFood("food/deleteFood",{'parameter':row.product_id})
                .then(res => {
                  console.log("删除咯",res);
                  this.showData();
                })
            ElMessage({
              type: 'success',
              message: '删除成功',
            })
          })
          .catch(() => {
            ElMessage({
              type: 'info',
              message: 'Delete canceled',
            })
          })


    }
  }
}
</script>

<style scoped>

.input_box{
  /*width: 500px;*/
  /*margin-right: 15px;*/
  margin-top: 10px;
}
.el-button--text {
  margin-right: 15px;
}
.el-select {
  width: 300px;
}
.el-input {
  width: 300px;
}
/*.dialog-footer button:first-child {*/
/*  margin-right: 10px;*/
/*}*/
.table {
  margin-left: 150px ;
}
.input_hang{
  width: 500px;
}

</style>