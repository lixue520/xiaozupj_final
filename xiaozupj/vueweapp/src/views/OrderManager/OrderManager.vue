<template>
  <div class="main">
    <el-alert title="全部订单" type="success" effect="dark" :closable="false"  center/>
  <el-table :data="tableData0" style="width: 100%">
    <el-table-column label="ID" width="100">
      <template #default="scope">
          <span >{{ scope.row.order_id }}</span>
      </template>
    </el-table-column>

    <el-table-column label="商品ID" width="100">
      <template #default="scope">
          <span >{{ scope.row.id }}</span>
      </template>
    </el-table-column>

    <el-table-column label="商品名称" width="100">
      <template #default="scope">
        <span >{{ scope.row.name }}</span>
      </template>
    </el-table-column>

    <el-table-column label="库存数量" width="100">
      <template #default="scope">
        <span >{{ scope.row.nums }}</span>
      </template>
    </el-table-column>

    <el-table-column label="下单数量" width="100">
      <template #default="scope">
        <span >{{ scope.row.num }}</span>
      </template>
    </el-table-column>

    <el-table-column label="out_trade_no" width="150">
      <template #default="scope">
        <span >{{ scope.row.out_trade_no }}</span>
      </template>
    </el-table-column>

    <el-table-column label="订单状态" width="100">
      <template #default="scope">
        <span >{{ scope.row.order_solve }}</span>
      </template>
    </el-table-column>

    <el-table-column label="下单时间" width="100">
      <template #default="scope">
        <span >{{ scope.row.generation_time}}</span>
      </template>
    </el-table-column>

    <el-table-column label="操作1" width="290">
      <template #default="scope">
         <el-button size="small" type="danger" @click=fin(scope.row.out_trade_no,scope.row.id)>完成</el-button>
         <el-button size="small" type="danger" @click=audit(scope.row.out_trade_no,scope.row.id)>审核</el-button>
         <el-button size="small" type="danger" @click=find()>测试</el-button>
        <el-button size="small" type="danger" @click=dell()>删除</el-button>
      </template>
    </el-table-column>

  </el-table>
  </div>
</template>

<script>
export default {
    name: "OrderManager",
    data(){
      return{
        tableData0: [{
          order_id:"订单ID",
          id:"商品ID",
          name:"商品名称",
          nums:"商品库存",
          num:"下单数量",
          out_trade_no:"交易号",
          order_solve:"订单状态",
          generation_time:"下单时间"
        }]
      }
    },
  methods: {
    dell(){
      //永久删除订单
    },
    fin(out_trade_no,id){
      //完成订单==>当订单状态为yes时，每天晚上12点订单变成ok,完成=>吃饱不退
      //或者按下此按钮给财务人员确定
      this.$api.order.FinishOrder(
          '/OrderManager/FinishOrder',
          {out_trade_no,id,"order_solve":"finish"},
      ).then((res)=>{
        console.log({"拿到了":res})
        this.$message.success('订单完成')
        this.$router.push({ path: '/index/mainContent/AllOrder' }) //界面刷新但是不会更新数据
      }).catch((res)=>{
        console.log({"没拿到":res})
        this.$message.err('检查软件状态')
      })
    },audit(out_trade_no,id){
      //修改订单成审核态
      this.$api.order.FinishOrder(
          '/OrderManager/auditOrder',
          {out_trade_no,id,"order_solve":"audit"},
      ).then((res)=>{
        console.log({"拿到了":res})
        this.$message.success('订单审核中。。。')
        this.$router.push({ path: '/index/mainContent/AllOrder' }) //界面刷新但是不会更新数据
      }).catch((res)=>{
        console.log({"没拿到":res})
        this.$message.err('检查软件状态')
      })
    },find(){
      //测试查询订单
      this.$api.order.findOrder(
          '/OrderManager/AllOrder',
          "查询订单" ,
          {headers: {'Content-Type': 'multipart/form-data'}}
      ).then((res)=>{
        console.log({"拿到了":res})
        this.$message.success('拿到数据')
          for(let i=0;i<res.length;i++){
            this.tableData0.push(res[i])
          }
      }).catch((res)=>{
        console.log({"没拿到":res})
        this.$message.err('没拿到数据')
      })
    }

  }

}
</script>

<style scoped>
.main{
  height: 90%;
  overflow:auto;
}
.main-right{
  float:left
}
</style>

