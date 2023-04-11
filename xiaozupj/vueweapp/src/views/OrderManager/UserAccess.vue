<template>
  <div class="main">
  <v-header></v-header>
  <el-alert title="客户支付方式查看" type="success" effect="dark" :closable="false"  center/>
  <el-table :data="tableData0" style="width: 100%">
    <el-table-column label="交易ID" width="150">
      <template #default="scope">
        <span >{{ scope.row.id }}</span>
      </template>
    </el-table-column>
    <el-table-column label="订单号" width="150">
      <template #default="scope">
        <span >{{ scope.row.out_trade_no}}</span>
      </template>
    </el-table-column>
    <el-table-column label="下单时间" width="200">
      <template #default="scope">
        <span >{{ scope.row.simple_time }}</span>
      </template>
    </el-table-column>
    <el-table-column label="略缩图" width="200">
      <template #default="scope">
        <el-image style="width: 100px;
        height: 100px" :src="scope.row.pay_img"/>
      </template>
    </el-table-column>
    <el-table-column label="交易方式" width="100">
      <template #default="scope">
        <span >{{ scope.row.payment_method}}</span>
      </template>
    </el-table-column>
    <el-table-column label="交易状态" width="100">
      <template #default="scope">
        <span >{{ scope.row.order_solve }}</span>
      </template>
    </el-table-column>
   <el-table-column label="管理"  width="290">
      <template #default="scope">

        <el-button size="small" type="danger" @click="dell()">删除</el-button>
        <el-button size="small" type="danger" @click="finAll()">测试</el-button>
        <el-button size="small" type="danger" @click="load()">下载</el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>
</template>

<script>
import BaseElement from './BaseElement.vue';
export default {
  name: "UserAccess",
  data(){
    return {
      tableData0: [{
        id: '',
        out_trade_no: '',
        simple_time:'',
        pay_img:'',
        payment_method:'',
        order_solve:'',
      }]
  }
  },components:{
    'v-header':BaseElement,
  }, methods: {
    finAll(){
      //测试查询订单
      this.$api.order.findOrder(
          '/OrderManager/PayFindAll',
          "查询订单详情" ,
          {headers: {'Content-Type': 'multipart/form-data'}}
      ).then((res)=>{
        console.log({"拿到了":res})
        let NewData={};
        this.$message.success('拿到数据')
        for(let i=0;i<res.length;i++){
          NewData.id=res[i].id;
          NewData.out_trade_no=res[i].out_trade_no;
          NewData.simple_time=res[i].simple_time;
          NewData.pay_img="http://120.27.199.8:8080/images/wxpay/"+res[i].pay_img;
          NewData.payment_method=res[i].payment_method;
          NewData.order_solve=res[i].order_solve;
          this.tableData0.push(NewData);
        }
        console.log("9999999999")
        console.log(tableData0)
      }).catch((res)=>{
        console.log({"没拿到":res})
        this.$message.err('没拿到数据')
      })
    },dell(){

    },load(){

    }
    }
}
</script>

<style scoped>
.main{
  height: 100%;
  overflow:auto;
}
</style>