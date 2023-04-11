<template>


  <el-button type="success">Success</el-button>
  <input type="text" v-model="ser_name">
  <button @click.prevent="find1" >商品名查询</button>
  <el-button type="success">Success</el-button>
  <button @click.prevent="find2" >分类排序</button>
  <el-button type="success">Success</el-button>
  <button @click.prevent="find" >ID排序</button>

  <div  class="inputWidth" >
    <a href="" size="small" > ID： </a>
    <input class="width1" type="text"  v-model="user_id">
    <a href=""> 用户名: </a>
    <input class="width2" type="text" v-model= "user_name">
    <a href=""> 商品名称: </a>
    <input class="width2" type="text" v-model="item_name">
    <a href=""> 评价: </a>
    <input class="width3" type="text"  v-model="evaluate1">
    <button @click.prevent="insertRow()">  添加订单</button>
  </div>

  <el-table
      :data="tableData"
      border
      style="width: 100%">
    <el-table-column
        prop="id"
        label="ID"
        width="50">
    </el-table-column>
    <el-table-column
        prop="user_name"
        label="用户名"
        width="80">
    </el-table-column>
    <el-table-column
        prop="item_name"
        label="商品名称"
        width="100">

    </el-table-column>

    <el-table-column
        prop="time"
        label="评论时间"
        width="200">

    </el-table-column>
    <el-table-column
        prop="evaluate"
        label="评论"
        width="400">

    </el-table-column>
    <el-table-column
        fixed="right"
        label="操作"
        width="120">
      <template v-slot="scope">
        <el-button
            @click.prevent="deleteRow(scope.$index, scope.row)"
            type="text"
            size="small">
          移除
        </el-button>

      </template>
    </el-table-column>

  </el-table>



</template>

<script>
import {hasPermission} from "@/permissions";

export default {
  name: "EvaluatePage",
  data() {
    return {
      tableData: [],
      ser_name:'鸡米花',
      user_id:null,
      user_name:'liwei',
      item_name:'鸡米花',
      evaluate1:'很好吃',
    }
  },
  mounted: function(){
    this.find();//需要触发的函数
    this.getCurrentTime();
  },

  methods: {

    getCurrentTime() {
      //获取当前时间
      let myDate = new Date()
      let yy = String(myDate.getFullYear())
      let mm = myDate.getMonth() + 1
      let dd = String(myDate.getDate() < 10 ? '0' + myDate.getDate() : myDate.getDate())
      let hou = String(myDate.getHours() < 10 ? '0' + myDate.getHours() : myDate.getHours())
      let min = String(myDate.getMinutes() < 10 ? '0' + myDate.getMinutes() : myDate.getMinutes())
      let sec = String(myDate.getSeconds() < 10 ? '0' + myDate.getSeconds() : myDate.getSeconds())
      this.nowDate = yy + '-' + mm + '-' + dd +' '
      this.nowTime = hou + ':' + min + ':' + sec
      this.nodata=this.nowDate+this.nowTime
      console.log(this.nodata)
    },

    newBook() {
      this.books.unshift({id: 5, checked: true, name: this.name, price: 99, num: 1})
    },
    hasPerms: function (perms) {
      // 根据权限标识和外部指示状态进行权限判断
      return hasPermission(perms)
    },
    add(index) {
      // 确定哪个图书的数量？
      this.books[index].num++;
    },
    del(index) {
      this.books.splice(index, 1);
    },

    deleteRow(index, row) {
      console.log("id：",row.id);
      this.$api.evaluate.deleteEvaluate("evaluate/deleteEvaluate" ,{'id':row.id})
          .then(res => {
            console.log(res)
            this.tableData.splice(index, 1);
          })
    },

    insertRow() {
      console.log(this.nodata)
      this.$api.evaluate.insertEvaluate("evaluate/insertEvaluate" ,{'id':this.user_id,'user_name':this.user_name,'item_name':this.item_name,'time':this.nodata,'evaluate':this.evaluate1})
          .then(res => {
            console.log(res)
            this.tableData.unshift({id: this.user_id, user_name:this.user_name,item_name:this.item_name,time:this.nodata,evaluate:this.evaluate1})
          })
    },

    find(){
      this.$api.evaluate.findEvaluate("evaluate/findEvaluate")
          .then(res => {
            console.log("评价++++：", res);
            // vuex（状态管理）
            let i;
            for(i=0;this.tableData[i];){
              this.tableData.splice(0,1);
            }
            for(i=0;res[i];i++){
              this.tableData.push({id:res[i].id,user_name: res[i].user_name, item_name: res[i].item_name,time: res[i].time, evaluate: res[i].evaluate});
            }

          }).catch(err => {
        console.log(err);

      })
    },

    find2(){
      this.$api.evaluate.findEvaluate("evaluate/find2Evaluate")
          .then(res => {
            console.log("评价++++：", res);
            // vuex（状态管理）
            let i;
            for(i=0;this.tableData[i];){
              this.tableData.splice(0,1);
            }
            for(i=0;res[i];i++){
              this.tableData.push({id:res[i].id,user_name: res[i].user_name, item_name: res[i].item_name,time: res[i].time, evaluate: res[i].evaluate});
            }

          }).catch(err => {
        console.log(err);

      })
    },





    find1() {
      this.$api.evaluate.findEvaluate("evaluate/find1Evaluate",{'user_name':this.ser_name })
          .then(res => {
            console.log("评价++++：", res);
            // vuex（状态管理）
            let i;
            for (i = 0; this.tableData[i];) {
              this.tableData.splice(0, 1);
            }
            for (i = 0; res[i]; i++) {
              this.tableData.push({
                id: res[i].id,
                user_name: res[i].user_name,
                item_name: res[i].item_name,
                time: res[i].time,
                evaluate: res[i].evaluate
              });
            }


          }).catch(err => {
        console.log(err);

      })
    }

  },
  computed: {
    evaluate() {
      let tree = this.$store.getters.getEvaluate;
      return tree;
    },

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

.find{
  width: 500px;
  height: 35px;
}

.inputWidth {
  width: 100%;
  height: 35px;
  float: left;
}
.width1{
  width: 50px;
}
.width2{
  width: 100px;
}
.width3{
  width: 200px;
}

</style>