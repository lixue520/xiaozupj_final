
import {Button, Form, Image, Input, Text, View} from "@tarojs/components";
import Taro from "@tarojs/taro";
import "./index.scss"
import {Component} from "react";
import store from "../../redux/store";
import action from "../../redux/action";
import {AtActionSheet, AtActionSheetItem} from "taro-ui";

export class ShoppingCart  extends Component {
    constructor () {
        super(...arguments)
        this.state = {
           value: 1  //记录添加商品个数,生成订单商品数量至少是1个
        }
    }


    render () {
        //将确认的订单计算价值，并将订单数据发送到后端
        const GenerateOrders=<Button onClick={()=>{
            //获取当前操作序列，并将序列中的对应的Order进行统一，发送到后台
            action.PostConfirmOrder();
            Taro.reLaunch({
                url: '/pages/OrderForm/index'
            });
            console.log("结算购物车，提交订单")
        }
        }>选中的生成订单</Button>

        //生成订单后生成订单界面，并提示用户支付
        const CallWeChatPay=  <AtActionSheet isOpened>
            <AtActionSheetItem>
                <Image src="http://120.27.199.8:8080/images/wxpay/pay.jpg" className="MyCard-list"/>
            </AtActionSheetItem>
        </AtActionSheet>

        function OrderAndPayment(){
            let {Order,ConfirmOrder,PayState}=store.getState();//检查购物车是否有订单,检查确认订单是否有数据
            if(Order.length!=0){
                return GenerateOrders
            }
        }
        //展示给用户二维码，扫码支付后，后端得到回调信息，
        // 并向前台发送数据，接收到后二维码消失，提示完成支付.

        return(
            <View>
                {OrderAndPayment()}
            </View>
        )
    }
}

export default ShoppingCart