// State 请求的数据缓存在这里,用于解构状态与解构新状态传值
export default {
    name: '覃爸爸',
    age : 18,
    chick:18,
    json:null, //接收JSON数据
    Order:[], //接收JSON数据，订单的数据更新
    ActualOrder:[],
    queue:[],   //操作序列
    CaiDan:[],   //服务器获取菜单
    ConfirmOrder:null,  //订单确认时发送的数据
    user_id:1900800724,
    PayState:null,   //null,no,yes
    out_trade_no:null
}