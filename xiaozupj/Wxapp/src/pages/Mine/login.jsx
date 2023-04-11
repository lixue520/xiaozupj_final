import React, { Component } from 'react'
import { View, Text } from '@tarojs/components'
import TabBar from "../tabbar/tabBar";
import Taro from "@tarojs/taro";

//点菜
class OrderForm extends Component {



    LoginPage(){

        Taro.getUserProfile({
            desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
            success: (res) => {
                // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
                console.log("用户信息：", res);
                Taro.setStorage({
                    key: '__userInfo__',
                    data: res.userInfo
                })
                this.setState({
                    userInfo: res.userInfo,
                    hasUserInfo: true
                })
            }
        })

    }



    render () {
        return (
            <View>
                <Text onClick={this.LoginPage.bind(this)}>确认授权</Text>
            </View>
        )
    }
}
export default OrderForm

