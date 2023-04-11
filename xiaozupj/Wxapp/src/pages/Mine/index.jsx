import React, { Component } from 'react'
import {View, Text, Image} from '@tarojs/components'
import TabBar from "../tabbar/tabBar";
import {AtAvatar, AtIcon } from 'taro-ui'
import './index.less'
import Taro from "@tarojs/taro";
//我的
class mine extends Component {
    constructor(props){
        super(props)
        this.state={
            username:'获取权限',
            avatarUrl:''
        }
    }

    componentDidMount(){
        Taro.getStorage({
            key: '__userInfo__',
            success:(res)=> {
                this.setState({
                    username:res.data.nickName,
                    avatarUrl:res.data.avatarUrl
                })
            }
        })
    }




    toTicket(){
        Taro.navigateTo({url:'ticket/index'})
    }
    toMyOrder(){
        Taro.navigateTo({url: '../OrderForm/index'})
    }
    toSaving(){
        Taro.navigateTo({url: 'saving'})
    }
    toIntegral(){
        Taro.navigateTo({url: 'integral'})
    }
    toAddress(){
        Taro.navigateTo({url: '../address/index'})
    }
    toCode(){
        Taro.navigateTo({url: 'code'})
    }
    toMembers(){
        Taro.navigateTo({url: 'members'})
    }
    toInvite(){
        Taro.navigateTo({url: 'invite'})
    }
    toHelp(){
        Taro.navigateTo({url: 'help'})
    }
    toStore(){
        Taro.showModal({
            title: '提示',
            content: '抱歉，该功能暂未开放！'
        })
    }
    toJiStore(){
        Taro.showModal({
            title: '提示',
            content: '抱歉，该功能暂未开放！'
        })
    }
    toChouJ(){
        Taro.showModal({
            title: '提示',
            content: '抱歉，该功能暂未开放！'
        })
    }
    toMyProfile(){
        Taro.navigateTo({url:'profile'})
    }
    toLoginPage(){

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
                Taro.navigateTo({url: 'index'})
            }
        })


    }



    render () {
        return (
            <View className='at-row index'>
                <View className='at-col at-col-12'>
                    <View className='at-row header'>
                        <View className='at-col at-col-3 head-por'>
                            <AtAvatar circle image={this.state.avatarUrl}></AtAvatar>
                        </View>
                        <View className='at-col at-col-6 name'>
                            <Text onClick={this.toLoginPage.bind(this)}>{this.state.username}</Text>
                        </View>
                        <View className='at-col at-col-3 icon-right'>
                            <AtIcon value='chevron-right' size='30' color='#000' onClick={this.toMyProfile.bind(this)}></AtIcon>
                        </View>
                    </View>
                    <View className='at-row head-por'>
                        <View className='at-row mycomfor'>
                            <View className='at-col head-por line_right'>
                                <Image
                                    className='myRegister'
                                    style='width: 30px;height: 30px;background: #fff;'
                                    src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                    onClick={this.toTicket.bind(this)}
                                />
                                <Text className='text-center' >优惠券</Text>
                            </View>

                            <View className='at-col head-por '>
                                <Image
                                    className='myRegister'
                                    style='width: 30px;height: 30px;background: #fff;'
                                    src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                    onClick={this.toSaving.bind(this)}
                                />
                                <Text className='text-center'>储蓄金</Text>
                            </View>
                            <View className='at-col head-por '>
                                <Image
                                    className='myRegister'
                                    style='width: 30px;height: 30px;background: #fff;'
                                    src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                    onClick={this.toIntegral.bind(this)}
                                />
                                <Text className='text-center'>积分</Text>
                            </View>
                        </View>
                    </View>

                    <View className='at-row head-por'>
                        <View className='at-row health-box'>
                            <View className='at-col at-col-12'>
                                <View className='at-row'>
                                    <View className='textbox'>我的功能</View>
                                </View>
                                <View className='at-row health-data'>
                                    <View className='at-col at-col-12'>
                                        <View className='at-row img-box'>
                                            <View className='at-col img-center health-items'>
                                                <Image
                                                    className='health-img'
                                                    style='width: 30px;height: 30px;background: #fff;'
                                                    src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                    onClick={this.toMyOrder.bind(this)}
                                                />
                                                <Text className='health-items-title'>我的订单</Text>
                                            </View>
                                            <View className='at-col img-center health-items'>
                                                <Image
                                                    className='health-img'
                                                    style='width: 30px;height: 30px;background: #fff;'
                                                    src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                    onClick={this.toAddress.bind(this)}
                                                />
                                                <Text className='health-items-title'>收货地址</Text>
                                            </View>
                                            <View className='at-col img-center health-items'>
                                                <Image
                                                    className='health-img'
                                                    style='width: 30px;height: 30px;background: #fff;'
                                                    src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                    onClick={this.toCode.bind(this)}
                                                />
                                                <Text className='health-items-title'>兑换码</Text>
                                            </View>
                                            <View className='at-col img-center health-items'>
                                                <Image
                                                    className='health-img'
                                                    style='width: 30px;height: 30px;background: #fff;'
                                                    src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                    onClick={this.toMembers.bind(this)}
                                                />
                                                <Text className='health-items-title'>会员等级</Text>
                                            </View>
                                        </View>
                                    </View>
                                </View>
                            </View>
                        </View>
                    </View>
                    <View className='at-row'>
                        <View className='at-row head-por'>
                            <View className='at-row service-box'>

                                    <View className='at-row myservice-box'>
                                        <View className='at-col at-col-12'>
                                            <View className='at-row myservice' onClick={this.toInvite.bind(this)}>
                                                <View className='at-col at-col-3 img-collection'>
                                                    <Image
                                                        className='img-collection'
                                                        style='width: 20px;height: 20px;background: #fff;'
                                                        src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                    />
                                                </View>
                                                <View className='at-col at-col-7'>
                                                    <Text className='text-collection'>邀请有礼</Text>
                                                </View>
                                                <View className='at-col at-col-2 service-ico'>
                                                    <AtIcon value='chevron-right' color='#a9a9a9'></AtIcon>
                                                </View>
                                            </View>
                                            <View className='at-row myservice' onClick={this.toHelp.bind(this)}>
                                                <View className='at-col at-col-3 img-collection'>
                                                    <Image
                                                        className='img-collection'
                                                        style='width: 20px;height: 20px;background: #fff;'
                                                        src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                    />
                                                </View>
                                                <View className='at-col at-col-7'>
                                                    <Text className='text-collection'>助力领券</Text>
                                                </View>
                                                <View className='at-col at-col-2 service-ico'>
                                                    <AtIcon value='chevron-right' color='#a9a9a9'></AtIcon>
                                                </View>
                                            </View>
                                            <View className='at-row myservice' onClick={this.toStore.bind(this)}>
                                                <View className='at-col at-col-3 img-constitution'>
                                                    <Image
                                                        className='img-constitution'
                                                        style='width: 20px;height: 20px;background: #fff;'
                                                        src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                    />
                                                </View>
                                                <View className='at-col at-col-7'>
                                                    <Text className='text-collection'>微商城</Text>
                                                </View>
                                                <View className='at-col at-col-2 service-ico'>
                                                    <AtIcon value='chevron-right' color='#a9a9a9'></AtIcon>
                                                </View>
                                            </View>
                                            <View className='at-row myservice' onClick={this.toJiStore.bind(this)}>
                                                <View className='at-col at-col-3 img-constitution'>
                                                    <Image
                                                        className='img-constitution'
                                                        style='width: 20px;height: 20px;background: #fff;'
                                                        src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                    />
                                                </View>
                                                <View className='at-col at-col-7'>
                                                    <Text className='text-collection'>积分商城</Text>
                                                </View>
                                                <View className='at-col at-col-2 service-ico'>
                                                    <AtIcon value='chevron-right' color='#a9a9a9'></AtIcon>
                                                </View>
                                            </View>
                                            <View className='at-row myservice' onClick={this.toChouJ.bind(this)}>
                                                <View className='at-col at-col-3 img-constitution'>
                                                    <Image
                                                        className='img-constitution'
                                                        style='width: 20px;height: 20px;background: #fff;'
                                                        src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                    />
                                                </View>
                                                <View className='at-col at-col-7'>
                                                    <Text className='text-collection'>夺宝抽奖</Text>
                                                </View>
                                                <View className='at-col at-col-2 service-ico'>
                                                    <AtIcon value='chevron-right' color='#a9a9a9'></AtIcon>
                                                </View>
                                            </View>
                                        </View>
                                    </View>
                            </View>
                        </View>
                    </View><TabBar tabBarCurrent={3} />
                </View>

            </View>




        )
    }
}

export default mine

