import React, { Component } from 'react'
import { View, Text } from '@tarojs/components'
import TabBar from "../tabbar/tabBar";
import {AtInput,AtButton} from 'taro-ui'
import * as Taro from "@tarojs/taro";

//点菜
class OrderForm extends Component {
    constructor (props) {
        super(props)
        this.state = {
            address:'',
            name:'',
            telephone:''
        }
    }

    componentWillUnmount () { }

    componentDidShow () { }

    componentDidHide () { }

    changeAdd(address){
        this.setState({
            address: address
        })
    }
    changeName(name){
        this.setState({
            name: name
        })
    }
    changePhon(phon){
        this.setState({
            telephone: phon
        })
    }
    addInsert(){
        let add
        add={
            address: this.state.address,
            name: this.state.name,
            telephone:this.state.telephone
        }
        Taro.setStorage({
            key: 'address',
            data: add
        })
        Taro.navigateTo({url: 'index'})
    }



    render () {
        return (
            <View>
                <View>
                    <View>
                        <Text>收货地址</Text>
                    </View>
                    <View>
                        <AtInput type='text'
                                 name='address'
                                 value={this.state.address}
                                 placeholder={'请输入收货详细地址'}
                                 onChange={this.changeAdd.bind(this)} />
                    </View>
                    <View>

                    </View>
                </View>
                <View>
                    <View>
                        <Text>联系人</Text>
                    </View>
                    <View>
                        <AtInput
                            type='text'
                            name='name'
                            placeholder='请填写收货人的姓名'
                            value={this.state.name}
                            onChange={this.changeName.bind(this)}
                        />
                    </View>
                </View>
                <View>
                    <View>
                        <Text>手机号</Text>
                    </View>
                    <View>
                        <AtInput
                            type='text'
                            name='telephone'
                            placeholder='请填写收货手机号码'
                            value={this.state.telephone}
                            onChange={this.changePhon.bind(this)}
                        />
                    </View>
                </View>
                <AtButton className='insert' onClick={this.addInsert.bind(this)}>确认添加</AtButton>
                <TabBar tabBarCurrent={3} />
            </View>
        )
    }
}
export default OrderForm

