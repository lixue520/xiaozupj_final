// actionCreator 本质是一个对象. 这个对象里面有很多的方法
// 哪里需要调用这里面的方法, 就在哪个组件中引入actionCreator
import store from './store'
import Taro from "@tarojs/taro";

export default {
    changeName () { // 修改name的方法
        let action = { // action对象
            type: 'CHANGE_NAME', // type 标识: 必须的属性, 固定属性只能是type
            payload: '李打雷' // 这是传递数据的参数
        }
        /* store.dispatch 是 View 发出 Action 的唯一方法。
        接受一个 Action 对象作为参数，将它发送出去。
        */
        store.dispatch(action)
    },AorD(data,type){
        let NewData=[]
        let {Order} = store.getState();
        if(type=="ADD"){
            for(let i=0;i<Order.length;i++){
                if(Order[i].id==data.id){
                    let num = parseInt(data.num)+1; //通过查看输出数字类型发现文本域将数字强行更改成字符类型
                    let AtCard = data.AtCard;
                    let id = data.id;
                    NewData.push({AtCard,id,num})
                }
                else{
                    NewData.push(Order[i]);
                }
            }
        }else {
            for(let i=0;i<Order.length;i++){
                if(Order[i].id==data.id){
                    let num = data.num-1;
                    let AtCard = data.AtCard;
                    let id = data.id;
                    NewData.push({AtCard,id,num})
                }else{
                    NewData.push(Order[i]);
                }
            }
        }
        let action = {
            type: 'Order',
            payload: NewData
        }
        store.dispatch(action)
    },
    changeAge (age) {
        let action = {
            type: 'CHANGE_AGE',
            payload: age // 可以使用参数
        }
        store.dispatch(action)
    },controlQueue(man){
        let action = {
            type: 'queue',
            payload: man
        }
        store.dispatch(action)
    },ModCar(data){
        let {Order}= store.getState();
        var NewData = [];
        for(let i=0;i<Order.length;i++){
            if(Order[i].id==data.id){
                //检测到相同的ID
                console.log("检测到相同id:"+Order[i].id)
            }else {
                if(Order[i]!=null){
                    NewData.push(Order[i]);
                }
            }
        }
        console.log(NewData)
        let action = {
            type: 'Order',
            payload: NewData
        }
        store.dispatch(action)
    },
    //get和post只是模仿网络请求
    //GET请求数据，页面刷新时，进行网络请求
    get(data){
        let action = {
            type: 'GET',
            payload: data // 可以使用参数
        }
        store.dispatch(action)
    },
    //POST请求，本地发送json数据反馈后台
    post(data){
        let action = {
            type: 'GET',
            payload: data // 可以使用参数
        }
        store.dispatch(action)
    },
    //将value值更改到Order特定的位置Value上,必定存在重复ID
    modOrder(data){
        let {Order}= store.getState();
        var NewData = [];
        //找相同ID
        for(let i=0;i<Order.length;i++){
            if(Order[i].id==data.id){
                let num = data.num;
                let AtCard = data.AtCard;
                let id = data.id;
                NewData.push({AtCard,id,num})
            }else{
                NewData.push(Order[i]);
            }
        }
        let action = {
            type: 'Order',
            payload: NewData // 可以使用参数
        }
        store.dispatch(action)
    }
    ,
    postOrder(data){
        if(data==null){
            store.dispatch({type:"Order",payload:data})
            console.log("订单清除成功")
            return 0;
        }
        //遍历数据，并判断之前与现在是否有相同的id,
        // 有的话，将当前的数据与原来的数据相加再，再将原来的数据拿出来构造新对象
        // 没有的话，直接拿原来的数据和本次数据构造新对象
        let {Order}= store.getState();
        let {name} = store.getState();
        let flag=true;//找出重复id标志
            var NewData=[];
        for(let i=0;i<Order.length;i++){
            if((Order[i].id==data.id)){
                //数据重构
                let num=parseInt(data.num)+parseInt(Order[i].num);
                let AtCard = data.AtCard;
                let id  = data.id;
                flag=false; //找到重复标志
                NewData.push({AtCard,id,num});
                i++;
            }
            if(Order[i]!=null){ //避免null插入
                NewData.push(Order[i]);
            }
        }
        if(flag){
            //不存在重复id,刚好将旧态装完时，加入data
            NewData.push(data)
        }

        let action = {
            type: 'Order',
            payload: NewData // 可以使用参数
        }
        console.log("旧订单数据")
        console.log(Order);
        console.log("发送下购物车数据")
        console.log(NewData); //拿来看数据的
        store.dispatch(action)
    },
      GetCaiDan()  {

            Taro.request({
                url: 'http://120.27.199.8:8090/wxApi/CaiDan', //仅为示例，并非真实的接口地址
                data: {},
                header: {
                    'content-type': 'application/json' // 默认值
                },method:'GET',
                success: function (res) {
                    // 调reducer修改数据
                    console.log("action执行")
                    console.log(res)
                    store.dispatch({type:'CaiDan', payload:res});
                }
            })
    },
    CheckQueue(Data){
        //无论Data是否为引用类型都将操作Value,影响他下一步的控制，因此这里只将它存下
        //待到统一处理时，再进行数值转换，或者不是用这种操作本身的修改方法，比如引用类型的
        let arr=[];
        let NewData;
        // Data.forEach((item,index)=>{
        //     Data[index]=parseInt(Data[index])
        // })
        for(let i=0;i<Data.length;i++){
            arr.push(parseInt(Data[i]));
        }
        NewData = [...new Set(arr.sort())] //排序去重
        console.log({"装载成功":NewData});
        let action = {
            type: 'queue',
            payload: NewData
        }

        store.dispatch(action)
    },
    PostConfirmOrder(){
        //将订单信息发送到远端
        let {queue} = store.getState();
        let {Order,user_id} = store.getState();
        let NewData=[];
        for(let i=0;i<Order.length;i++){
          for(let k=0;k<queue.length;k++){
              // console.log({"测试:":Order})
              // console.log({"找到ID:":Order[i].id,"找到序列":queue[k]})
              if(Order[i].id==queue[k]+""){   //嵌套判断，当前Order是否在queue有值
                  //数据构造，添加用户的ID
                  console.log({"找到的数据":Order[i]});
                   NewData.push(Order[i]={
                       ...Order[i],
                       ...{"user_id":user_id}, //调用用户接口获取
                       ...{"Generation_time":Date()}
                   });
              }
          }
        }
        let action = {
            type: 'SendOrder',
            payload: NewData
        }
        console.log({"预上载数据成功":NewData});
        store.dispatch(action)
        //数据上载
        Taro.request({
            url: 'http://localhost:8090/wxApi/receive', //仅为示例，并非真实的接口地址
            data: {"Car":NewData},
            header: {
                'content-type': 'application/json' // 默认值
            },method:'POST',
            success: function (res) {
                console.log({"接收商户:":res.data.out_trade_no})
                let action1={
                    type:'out_trade_no',
                    payload:res.data.out_trade_no
                }
                store.dispatch(action1)
                let action2={
                    type:'PayState',
                    payload:"no"   //回调成功说明当前订单状态为no
                }
                store.dispatch(action2)
            }
        })
        console.log("删除与NewData相同ID的顶单")
        this.modSelectOrder(NewData)
    }, modSelectOrder(data){
        let {Order}= store.getState();
        console.log({"当前要删除的数据:":data});
        let NewData=[]
        let fag=false; //找到相同标志的ID
        let ID=0;
        for(let i=0;i<Order.length;i++){
            for(let k=0;k<data.length;k++){
                if(Order[i].id==data[k].id){
                    //找到了就啥也不干，跳过
                    console.log("找到ID:"+Order[i].id+"要删除ID:"+data[k].id);
                    fag=true;
                    ID=data[k].id;
                }
            }
            if(fag){
                fag=false;
                continue;//跳到下一步
            }
            NewData.push(Order[i]);//默认插入当前
        }
        console.log({"修改完数据:":NewData})
        let action = {
            type: 'Order',
            payload: NewData // 可以使用参数
        }
        store.dispatch(action)

    },SetPayState(){
        let {out_trade_no}=store.getState();
        console.log("-- 进入判断支付环节---"+out_trade_no);
        Taro.request({
            url: 'http://120.27.199.8:8090/wxApi/getPayState', //仅为示例，并非真实的接口地址
            data:{"out_trade_no":out_trade_no},   //将当前的订单号发送到后端，拿到当前的订单状态共给二维码是否显示
            header: {
                'content-type': 'application/json' // 默认值
            },method:'POST',
            success: function (res) {
                console.log({"接收订单状态:":res})
                if(res.data.order_solve=="yes"){ //订单支付成功时,将其置null
                    let action={
                        type:'PayState',
                        payload:null
                    }
                    console.log("完成支付")
                    store.dispatch(action)
                }else{
                    console.log("当前订单未支付")
                }
            }
        })
    }
}
