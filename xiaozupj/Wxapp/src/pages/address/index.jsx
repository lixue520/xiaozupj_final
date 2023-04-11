import React, { Component } from 'react'
import {View, Text, Button} from '@tarojs/components'
import TabBar from "../tabbar/tabBar";
import "./index.less"
import * as Taro from "@tarojs/taro";
import addressList from "./addressList"

//点菜
class OrderForm extends Component {
    componentWillReceiveProps (nextProps) {
        console.log(this.props, nextProps)
    }

    componentWillUnmount () { }

    componentDidShow () { }

    componentDidHide () { }
    insertAdd(){
        Taro.navigateTo({url: 'insertAdd'})
    }

    render () {
        return (
            <View>
                <addressList></addressList>
                <View>
                    <Button className='address' onClick={this.insertAdd.bind(this)}>添加地址</Button>
                </View>
                <TabBar tabBarCurrent={3} />
            </View>
        )
    }
}
export default OrderForm

