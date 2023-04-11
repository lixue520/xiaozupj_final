import React, { Component } from 'react'
import {Picker, View, Text, Button, Input} from '@tarojs/components'
import TabBar from "../tabbar/tabBar";
import * as Taro from "@tarojs/taro";
import moment from 'moment'
import {AtAvatar, AtIcon,AtForm,AtList,AtListItem,AtInput,AtButton} from 'taro-ui'
import './profile.less'


//点菜
class OrderForm extends Component {
    constructor (props) {
        super(props)
        this.state = {
            value: '',
            isActive1: true,
            isActive2: false,
            userName: '',
            userPhone: '',
            userIdcard: '',
            userIdcardType: '请选择证件类型',
            userBirth: moment().format('YYYY-MM-DD'),
            userGender: '男',
            avatarUrl: '',
            openid: '',
            name: '',
            phone: '',
            birth: '',
            gender: '',
            selector: ['居民身份证', '港澳台身份证'],
            idcardType: '',
            idcard: '',
            form: {
                userPhone:''
            },
            wxOpenId: ''
        }
        this.handleClick1 = this.handleClick1.bind(this);
        this.handleClick2 = this.handleClick2.bind(this);
    }
    componentDidMount() {
        Taro.getStorage({
            key: '__userInfo__',
            success:(res)=> {
                this.setState({
                    userName:res.data.nickName,
                    avatarUrl:res.data.avatarUrl
                })
            }
        })
        Taro.getStorage({
            key: 'info',
            success:(res)=>{
                this.setState({
                    userIdcard:res.data.userIdcard,
                    userPhone:res.data.userPhone
                })
            }
        })
    }


    handleClick1(){
        const isActive2 = !this.state.isActive2;
        const isActive1 = !this.state.isActive1;
        this.setState({
            isActive2:isActive2,
            isActive1:isActive1,

        });
        if(isActive1 === true){
            this.setState({
                userGender:'男'
            })
        }else{
            this.setState({
                userGender:'女'
            })
        }
    }

    handleClick2(){
        const isActive2 = !this.state.isActive2;
        const isActive1 = !this.state.isActive1;
        this.setState({
            isActive2: isActive2,
            isActive1: isActive1,
        });

        if (isActive2 === true) {
            this.setState({
                userGender: '女'
            })
        } else {
            this.setState({
                userGender: '男'
            })
        }
    }

    componentWillUnmount () { }

    componentDidShow () { }

    componentDidHide () { }

    abc(){
        Taro.removeStorageSync('__userInfo__')
        Taro.removeStorageSync('info')
        Taro.navigateTo({url:'index'})
    }
    inputChangeUsername(userName){
        this.setState({
            userName: userName
        })
    }
    inputChangeIdcard(idcard){
        this.setState({
            userIdcard: idcard
        })
    }
    inputChangePhone(userPhone){
        this.setState({
            userPhone:userPhone
        })
        console.log(userPhone);
    }
    onChangeIdcardType = e => {
        this.setState({
            userIdcardType: this.state.selector[e.detail.value]
        })
    }
    onDateChange = e => {
        this.setState({
            userBirth: e.detail.value
        })
    }
    saveMassage(){
        let info
        info={
            userPhone: this.state.userPhone,
            userIdcard: this.state.userIdcard
        }
        Taro.setStorage({
            key: 'info',
            data: info
        })
        let userMess
        userMess = {
            nickName: this.state.userName,
            avatarUrl: this.state.avatarUrl
        }
        Taro.setStorage({
            key: '__userInfo__',
            data: userMess
        })
        Taro.navigateTo({url :'index'})

    }




    render () {
        return (
            <View>
                <View className='at-row per-data'>
                    <View className='at-col at-col-12'>
                        {/*头像*/}
                        <View className='at-row per-line'>
                            <View className='at-col at-col__offset-1'>
                                <Text className='per-text'>头像</Text>
                            </View>
                            <View className='at-col col-10 at-col__offset-6 userava'>
                                <AtAvatar circle image={this.state.avatarUrl}></AtAvatar>
                            </View>
                            <View className='at-col at-col-1'>
                                {/*<AtIcon value='chevron-right' color='#a9a9a9'></AtIcon>*/}
                            </View>
                        </View>
                        {/*姓名*/}
                        <View className="at-row per-line">
                            <View className="at-col at-col__offset-1">
                                <Text className='per-text' >姓名</Text>
                            </View>
                            <View className="at-col col-10 at-col__offset-3">
                                <AtInput type='text'
                                         className='per-text patient-input-font Name'
                                         name='userName'
                                         border={false}
                                         value={this.state.userName}
                                         placeholder={'请输入姓名'}
                                         onChange={this.inputChangeUsername.bind(this)}/>
                            </View>
                            <View className="at-col at-col-1">
                                {/*<AtIcon value='chevron-right' color='#a9a9a9'></AtIcon>*/}
                            </View>
                        </View>
                        {/*性别*/}
                        <View className="at-row per-line">
                            <View className="at-col at-col__offset-1">
                                <Text className='per-text'>性别</Text>
                            </View>
                            <View className="at-col at-col__offset-3">
                                <View className="at-row">
                                    <View className="at-col sex-but-center">
                                        <AtButton className={this.state.isActive1?'sex-but-hover':'sex-but'} onClick={this.handleClick1}>男</AtButton>
                                    </View>
                                    <View className="at-col sex-but-center">
                                        <AtButton className={this.state.isActive2?'sex-but-hover':'sex-but'} onClick={this.handleClick2}>女</AtButton>
                                    </View>
                                </View>
                            </View>
                            <View className="at-col at-col-1">
                                {/*<AtIcon value='chevron-right' color='#a9a9a9'></AtIcon>*/}
                            </View>
                        </View>
                        {/*身份证类型*/}

                        {/*出生日期*/}

                        {/*手机号码*/}
                        <View className='at-row per-line'>
                            <View className="at-col at-col__offset-1">
                                <Text className='per-text'>手机号码</Text>
                            </View>
                            <View className='at-col col-10 at-col__offset-3 per-text idcard-width'>
                                <AtInput type='number'
                                         border={false}
                                         name='userPhone'
                                         maxlength='11'
                                         minlength={11}
                                         className='per-text at-list__item item-extra__info patient-input-font input'
                                         placeholder={'请输入手机号码'}
                                         value={this.state.userPhone}
                                         onChange={this.inputChangePhone.bind(this)}/>
                            </View>
                            <View className="at-col at-col-1">
                                {/*<AtIcon value='chevron-right' color='#a9a9a9'></AtIcon>*/}
                            </View>
                        </View>
                    </View>
                </View>
                {/*保存按钮*/}
                <View className='at-row person-bac'>
                    <View className='at-col at-col-12 person-but'>
                        <Button className='sava-but' formType='submit' onClick={this.saveMassage.bind(this)}>保存</Button>
                    </View>
                </View>
                <AtButton className='tuichu' onClick={this.abc.bind(this)}>退出登录</AtButton>
                <TabBar tabBarCurrent={3} />
            </View>
        )
    }
}
export default OrderForm

