import React, { Component } from 'react'
import { View, Text } from '@tarojs/components'
import TabBar from "../tabbar/tabBar";

//点菜
class OrderForm extends Component {
    componentWillReceiveProps (nextProps) {
        console.log(this.props, nextProps)
    }

    componentWillUnmount () { }

    componentDidShow () { }

    componentDidHide () { }

    render () {
        return (
            <View>
                <View><Text>助力领券</Text></View>
                <TabBar tabBarCurrent={3} />
            </View>
        )
    }
}
export default OrderForm

