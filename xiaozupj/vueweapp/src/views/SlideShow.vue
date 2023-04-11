<template>

<!--  <el-button size="small" type="danger" @click="getShowVue()">刷新图片</el-button>-->
  <el-breadcrumb >
    <el-breadcrumb-item :to="{ path: 'indexHello' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item>轮播图列表</el-breadcrumb-item>
  </el-breadcrumb>
  <el-alert title="轮播图管理" type="success" effect="dark" :closable="false"  center/>

  <input type="file" @change="getFile($event)">
  <el-button size="small" type="danger" @click="insertFile()">新增图片</el-button>

  <el-table :data="tableData0" style="width: 100%">
    <el-table-column label="ID" width="50">
      <template #default="scope">
          <span >{{ scope.row.id }}</span>
      </template>
    </el-table-column>
    <el-table-column label="名称" width="100">
      <template #default="scope">
          <span >{{ scope.row.name }}</span>
      </template>
    </el-table-column>
    <el-table-column label="图片url地址" width="350">
      <template #default="scope">
        <span >{{ scope.row.url }}</span>
      </template>
    </el-table-column>

    <el-table-column label="略缩图" width="200">
      <template #default="scope">
          <el-image style="width: 100px; height: 100px" :src="scope.row.url" />
      </template>
    </el-table-column>

    <el-table-column label="替换轮播图" width="200">
      <template #default="scope">

        <input type="file" @change="getFile($event,scope.row.name)">
        <el-button size="small" type="danger" @click="upload()">上传替换</el-button>

      </template>
    </el-table-column>


    <el-table-column label="动作" width="200">
      <template #default="scope">

        <el-switch
            v-model="scope.row.enable"
            active-value="1"
            inactive-value="0"
            active-text="启用"
            inactive-text="关闭"
            size="large"
            @change="changAble(scope.row.id,scope.row.enable)"
        />
      </template>
    </el-table-column>


    <el-table-column label="删除图片" width="150">
      <template #default="scope">

        <el-button size="small" type="danger" @click="deleteFile(scope.row.id)">删除</el-button>

      </template>
    </el-table-column>
  </el-table>



  <el-alert title="限量特惠管理" type="warning" effect="dark" :closable="false"  center/>
  <el-table :data="tableData1" style="width: 100%">

    <el-table-column label="名称" width="150">
      <template #default="scope">
          <span >{{ scope.row.name }}</span>
      </template>
    </el-table-column>

    <el-table-column label="图片url地址" width="400">
      <template #default="scope">
          <span >{{ scope.row.url }}</span>
      </template>
    </el-table-column>

    <el-table-column label="略缩图" width="200">
      <template #default="scope">
          <el-image style="width: 100px; height: 100px" :src="scope.row.url"  />
      </template>
    </el-table-column>

    <el-table-column label="操作">
      <template #default="scope">
        <input type="file" @change="getFile($event,scope.row.name)" >
        <el-button size="small" type="danger" @click="upload()">上传替换</el-button>
      </template>
    </el-table-column>

  </el-table>


</template>

<script >


export default {
  name: "SlideShowPage",
  data(){
    return {
      tableData0: [],

      tableData1: [{
        name: '优惠1',
        url: 'http://120.79.91.8/images/yks/优惠1.png',
      }, {
        name: '优惠2',
        url: 'http://120.79.91.8/images/yks/优惠2.png',
      }, {
        name: '优惠3',
        url: 'http://120.79.91.8/images/yks/优惠3.png',
      }]

    }
  },

 mounted: function(){
    this.getShowVue();
},

  methods:{


    insertFile(){
      let file = new FormData();
      file.append("file", this.file);
      this.$api.file.upload('/file/insertFile',
          file,
          {headers: {'Content-Type': 'multipart/form-data'}}
      ).then((res) => {
        this.$message.success('文件上传成功');
      }).catch((res) => {
        this.$message.error('文件上传失败');
      })
    },


    deleteFile(id){
      this.$api.file.deleteFile('file/deleteFile',{'ID':id}
      ).then((res) =>{
        this.$message.success('修改成功');
      })
      console.log(id)
    },


    changAble(id,enable){
      this.$api.file.changAble('file/changAble',{'ID':id,'enable':enable}

      ).then((res) =>{
        this.$message.success('修改成功');
      })
    },


    getShowVue(){
      this.$api.file.getShowVue('file/getShowVue',{'ID':""}
      ).then((res) =>{
        this.tableData0=res;
        this.$message.success('获取图片成功');
      })
    },

    upload() {
        let file = new FormData();
        file.append("file", this.file);
        file.append("filename", this.filename);
        this.$api.file.upload('/file/upload',
            file,
            {headers: {'Content-Type': 'multipart/form-data'}}
        ).then((res) => {
          this.$message.success('文件上传成功');
        }).catch((res) => {
          this.$message.error('文件上传失败');
        })
    },


    getFile(event,idx) {
      this.filename=idx;
      this.file = event.target.files[0];
      let fileName = this.file.name;
      let index = fileName.lastIndexOf(".");
      let fileType = ['png']
      if (index != -1) {
        let suffix = fileName.substr(index + 1).toLowerCase();
        if (fileType.includes(suffix)) {
          this.$message.success('文件添加成功');
        }else {
          this.$message.error("文件格式错误！请选择 'png' 格式的文件")
        }
      }

    },
  }
}
</script>

<style >

</style>