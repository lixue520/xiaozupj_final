<template>
  <div style="padding: 10px">
    <el-card style="margin: 10px">
      <el-table :data="tableData" stripe style="width: 100% " max-height="450" show-summary>
        <el-table-column prop="order_id" label="id" width="60" />
        <el-table-column prop="name" label="商品名" width="200" />
        <el-table-column prop="num" label="商品数量" width="100" />
        <el-table-column prop="price" label="商品单价" width="100" />
        <el-table-column prop="total" label="总价" width="100" />
        <el-table-column prop="generation_time" label="下单时间" />
      </el-table>
    </el-card>
    <el-card style="margin: 10px" >
      <div style="margin: 10px auto;width: 63%;">
        <el-button @click="search" style="margin: 10px">刷新</el-button>
        <el-select v-model="year" clearable placeholder="年份" style="width: 80px;margin: 10px">
          <el-option
              v-for="item in yearArr"
              :key="item"
              :label="item.label"
              :value="item"
          />
        </el-select>
        <el-select v-model="month" clearable filterable placeholder="月份" style="width: 80px;margin: 10px">
          <el-option
              v-for="item in monthArr"
              :key="item.month"
              :label="item.label"
              :value="item.month"
          />
        </el-select>
        <el-select v-model="day" clearable filterable placeholder="日期" style="width: 80px;margin: 10px">
          <el-option
              v-for="item in dayArr"
              :key="item"
              :label="item.label"
              :value="item"
          />
        </el-select>
        <el-input v-model="time" placeholder="00:00:00" clearable style="width: 80px;margin: 10px" />
        <el-button @click="searchTime" style="margin: 10px">筛选</el-button>
      </div>
    </el-card>

  </div>
</template>

<script>

import { ref } from 'vue'
import {TableColumnCtx} from "element-plus/es/components/table/src/table-column/defaults";

const month = ref('')
const day = ref('')
const dayArr = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31]
const year = ref('')
const yearArr = [2020,2021,2022,2023,2024,2025,2026,2027,2028,2029,2030]
const monthArr = [
  {month: 'Jan', label: '1月',},
  {month: 'Feb', label: '2月',},
  {month: 'Mar', label: '3月',},
  {month: 'Apr', label: '4月',},
  {month: 'May', label: '5月',},
  {month: 'Jun', label: '6月',},
  {month: 'Jul', label: '7月',},
  {month: 'Aug', label: '8月',},
  {month: 'Sep', label: '9月',},
  {month: 'Oct', label: '10月',},
  {month: 'Nov', label: '11月',},
  {month: 'Dec', label: '12月',},
]
export default {
  name: "profitStatistic",

  data() {
    return {
      month,
      day,
      monthArr,
      dayArr,
      year,
      yearArr,
      time:"",
      startRechargeTime:"",
      tableData:[],
      arr:[],
    }
  },

  mounted:
    function(){
      this.search();
    },



  methods:{

    search(){
      this.$api.profitStatistics.getList("/profitStatistics")
          .then(res =>{
            console.log(res);
            this.tableData = res;
            if(res){
              ElMessage.success('刷新成功！')
            }else{
              ElMessage.error('刷新失败！请重新刷新！')
            }
          })
    },

    searchTime(){
      this.$api.profitStatistics.SearchTime("/profitStatistics",{'startRechargeTime':"%"+this.month+"%"+this.day+"%"+this.year+"%"+this.time+"%"})
          .then(res =>{
            console.log(res);
            this.tableData = res;
            if(res){
              ElMessage.success('筛选成功！')
            }else{
              ElMessage.error('筛选失败！请重新筛选！')
            }
          })
    },






  },

}
</script>

<style scoped>

</style>
