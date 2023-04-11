import React, {Component} from "@types/react";
import {Image, Text, View} from "@tarojs/components";
import {AtAvatar, AtIcon} from "taro-ui";

class test extends Component {
    componentWillReceiveProps (nextProps) {
        console.log(this.props, nextProps)
    }

    componentWillUnmount () { }

    componentDidShow () { }

    componentDidHide () { }

    render () {
        return (
            <View className='at-row index'>
                <View className='at-col at-col-12'>
                    <View className='at-row header'>
                        <View className='at-col at-col-3 head-por'>
                            <AtAvatar circle image='https://jdc.jd.com/img/200'></AtAvatar>
                        </View>
                        <View className='at-col at-col-6 name'>
                            <Text>登录/注册</Text>
                        </View>
                        <View className='at-col at-col-3 icon-right'>
                            <AtIcon value='chevron-right' size='30' color='#000'></AtIcon>
                        </View>
                    </View>
                    <View className='at-row head-por'>
                        <View className='at-row mycomfor'>
                            <View className='at-col head-por line_right'>
                                <Image
                                    className='myRegister'
                                    style='width: 30px;height: 30px;background: #fff;'
                                    src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                />
                                <Text className='text-center'>我的挂号</Text>
                            </View>

                            <View className='at-col head-por '>
                                <Image
                                    className='myRegister'
                                    style='width: 30px;height: 30px;background: #fff;'
                                    src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                />
                                <Text className='text-center'>就诊人管理</Text>
                            </View>
                        </View>
                    </View>

                    <View className='at-row head-por'>
                        <View className='at-row health-box'>
                            <View className='at-col at-col-12'>
                                <View className='at-row'>
                                    <View className='textbox'>健康数据</View>
                                </View>
                                <View className='at-row health-data'>
                                    <View className='at-col at-col-12'>
                                        <View className='at-row img-box'>
                                            <View className='at-col img-center health-items'>
                                                <Image
                                                    className='health-img'
                                                    style='width: 30px;height: 30px;background: #fff;'
                                                    src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                />
                                                <Text className='health-items-title'>健康档案</Text>
                                            </View>
                                            <View className='at-col img-center health-items'>
                                                <Image
                                                    className='health-img'
                                                    style='width: 30px;height: 30px;background: #fff;'
                                                    src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                />
                                                <Text className='health-items-title'>电子病历</Text>
                                            </View>
                                            <View className='at-col img-center health-items'>
                                                <Image
                                                    className='health-img'
                                                    style='width: 30px;height: 30px;background: #fff;'
                                                    src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                />
                                                <Text className='health-items-title'>就诊费用</Text>
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
                                <View className='at-col at-col-12'>
                                    <View className='at-row'>
                                        <View className='textbox'>我的服务</View>
                                    </View>
                                    <View className='at-row myservice-box'>
                                        <View className='at-col at-col-12'>
                                            <View className='at-row myservice'>
                                                <View className='at-col at-col-3 img-collection'>
                                                    <Image
                                                        className='img-collection'
                                                        style='width: 30px;height: 30px;background: #fff;'
                                                        src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                    />
                                                </View>
                                                <View className='at-col at-col-7'>
                                                    <Text className='text-collection'>我的收藏</Text>
                                                </View>
                                                <View className='at-col at-col-2 service-ico'>
                                                    <AtIcon value='chevron-right' color='#a9a9a9'></AtIcon>
                                                </View>
                                            </View>
                                            <View className='at-row myservice'>
                                                <View className='at-col at-col-3 img-collection'>
                                                    <Image
                                                        className='img-collection'
                                                        style='width: 30px;height: 30px;background: #fff;'
                                                        src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                    />
                                                </View>
                                                <View className='at-col at-col-7'>
                                                    <Text className='text-collection'>效果评价</Text>
                                                </View>
                                                <View className='at-col at-col-2 service-ico'>
                                                    <AtIcon value='chevron-right' color='#a9a9a9'></AtIcon>
                                                </View>
                                            </View>
                                            <View className='at-row myservice'>
                                                <View className='at-col at-col-3 img-constitution'>
                                                    <Image
                                                        className='img-constitution'
                                                        style='width: 30px;height: 30px;background: #fff;'
                                                        src='https://camo.githubusercontent.com/3e1b76e514b895760055987f164ce6c95935a3aa/687474703a2f2f73746f726167652e333630627579696d672e636f6d2f6d74642f686f6d652f6c6f676f2d3278313531333833373932363730372e706e67'
                                                    />
                                                </View>
                                                <View className='at-col at-col-7'>
                                                    <Text className='text-collection'>体质辨识记录</Text>
                                                </View>
                                                <View className='at-col at-col-2 service-ico'>
                                                    <AtIcon value='chevron-right' color='#a9a9a9'></AtIcon>
                                                </View>
                                            </View>
                                        </View>
                                    </View>
                                </View>
                            </View>
                        </View>
                    </View>
                </View>
            </View>
        )
    }
}
export default test