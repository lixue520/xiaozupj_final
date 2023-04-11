<template>
    <div>
        <span>餐品类型：</span>
        <el-select v-model="value" clearable placeholder="请选择餐品类型">
            <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                    @click="selectMenu(item.label)">
            </el-option>
        </el-select>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <el-button type="primary" @click="findMenu()" plain>显示全部</el-button>
        &nbsp;&nbsp;&nbsp;
        <el-button type="primary" @click="insert" plain>添加餐品</el-button>
        <el-table
                :data="tableData"
                height="500"
                border
                style="width: 100%">
            <el-table-column
                    prop="id"
                    label="餐品编号"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="type"
                    label="餐品类别"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="餐品名称"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="price"
                    label="原价"
                    width="180">
            </el-table-column>
            <el-table-column
                    prop="num"
                    label="库存"
                    width="180">
            </el-table-column>
            <el-table-column label="操作">
                <template v-slot="scope">
                    <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
    <div :class="submenu">
        <div class="mmm">
            <div class="input">
                <el-form :model="form">
                    <el-form-item label="餐品编号" :label-width="'200px'">
                        <el-input v-model="form.id" autocomplete="off" placeholder="请输入内容" :disabled="true"></el-input>
                    </el-form-item>
                    <el-form-item label="餐品类别" :label-width="'200px'">
                        <el-input v-model="form.type" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="餐品名称" :label-width="'200px'">
                        <el-input v-model="form.name" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="餐品价格" :label-width="'200px'">
                        <el-input v-model="form.price" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="库存" :label-width="'200px'">
                        <el-input v-model="form.nums" autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
            </div>

            <div slot="footer" class="button">
                <el-button @click="cancel">取 消</el-button>
                <el-button type="primary" @click="confirm">确 定</el-button>
            </div>
        </div>
    </div>

</template>

<script>
    import {findMenu} from "../http/modules/menu";

    export default {
        name: "MenuPage",
        data (){
            return{
                submenu:{
                    submenuHide: true,
                    submenuShow: false
                },
                tableData: [],
                form: {
                    id: '',
                    type:'',
                    name: '',
                    price: '',
                    nums: '',
                    operation:'',
                },
                options: [{
                    value: '选项1',
                    label: '美味小吃'
                }, {
                    value: '选项2',
                    label: '精选主食'
                }, {
                    value: '选项3',
                    label: '招牌全鸡'
                }, {
                    value: '选项4',
                    label: '特惠套餐'
                }, {
                    value: '选项5',
                    label: '缤纷饮品'
                }],
                value: ''
            }

        },
        mounted: function(){
            this.findMenu();//需要触发的函数
        },
        methods: {

            findMenu(){
                this.$api.menu.findMenu("dishes/findMenu")
                    .then(res=>{
                        console.log("................",res);
                        let i;
                        for(i=0;this.tableData[i];){
                            this.tableData.splice(0,1);
                        }
                        for(i=0;res[i];i++){
                            this.tableData.push({id:res[i].id,type: res[i].type, name: res[i].name,price: res[i].price, num: res[i].nums});
                        }

                    })
            },
            insert(){
                this.submenu.submenuHide=false
                this.submenu.submenuShow=true
                this.form.id='0'
                this.form.type=''
                this.form.name=''
                this.form.price=''
                this.form.nums=''
                this.form.operation=1
            },
            confirm(){
                this.$confirm('确定要修改此信息吗！', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    if(this.form.operation==0){
                        this.$api.menu.modifyMenu("dishes/modifyMenu",{'id' :this.form.id,'type': this.form.type, 'name':this.form.name,'price':this.form.price,'nums':this.form.nums})
                            .then(res=>{
                                this.findMenu();
                            })
                    }else if(this.form.operation==1){
                        this.$api.menu.insertMenu("dishes/insertMenu",{'id' :this.form.id,'type': this.form.type, 'name':this.form.name,'price':this.form.price,'nums':this.form.nums})
                            .then(res=>{
                                this.findMenu();
                            })
                    }
                    this.$message({
                        type: 'success',
                        message: '修改成功!'
                    });
                    this.cancel();
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消操作',
                    });
                    this.cancel();
                });
            },
            selectMenu(value){
                this.$api.menu.selectMenu("dishes/selectMenu",{'type' :value})
                    .then(res=>{
                        let i;
                        for(i=0;this.tableData[i];){
                            this.tableData.splice(0,1);
                        }
                        for(i=0;res[i];i++){
                            this.tableData.push({id:res[i].id,type: res[i].type, name: res[i].name,price: res[i].price, num: res[i].nums});
                        }
                    })
            },
            handleEdit(index, row) {
                this.submenu.submenuHide=false
                this.submenu.submenuShow=true
                this.form.id=row.id
                this.form.type=row.type
                this.form.name=row.name
                this.form.price=row.price
                this.form.nums=row.num
                this.form.operation=0
            },
            cancel(){
                this.submenu.submenuHide=true
                this.submenu.submenuShow=false
            },
            handleDelete(index, row) {
                console.log("index...",index);
                console.log(row.id);
                this.$confirm('此操作将永久删除该信息, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$api.menu.deleteMenu("dishes/deleteMenu",{'id' :row.id})
                        .then(res=>{
                            console.log(res);
                            this.tableData.splice(index,1);
                        })
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            /*findMenu(){
                this.$api.menu.findMenu("dishes/findMenu")
                    .then(res=>{
                        console.log("................",res);
                        let i;
                        for(i=0;this.tableData[i];){
                            this.tableData.splice(0,1);
                        }
                        for(i=0;res[i];i++){
                            this.tableData.push({id:res[i].id,type: res[i].type, name: res[i].name,price: res[i].price, num: res[i].nums});
                        }

                    })
            }*/
        }
    }
</script>

<style scoped>
    .submenuHide {
        display: none;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background:rgba(0,0,0,0.5);
        z-index: 10;
    }
    .submenuShow{
        display: block;
        position: absolute;
        top: 0%;
        left: 0%;
        width: 100%;
        height: 100%;
        background:rgba(0,0,0,0.5);
        z-index: 10;
    }
    .mmm{
        width: 600px;
        height: 450px;
        background-color: white;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        opacity: 1;
    }
    .input{
        width: 400px;
        height: 400px;
        position: absolute;
        top: 20%;
        left: 5%;
    }
    .button{
        position: absolute;
        top: 80%;
        left: 40%;
    }
</style>