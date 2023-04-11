import { Component } from 'react'
import { View, Text } from '@tarojs/components'
import TabBar from "../../tabbar/tabBar";

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
                <View><Text>优惠券</Text></View>
            </View>
        )
    }
}
export default OrderForm

