import React, { Component } from 'react'
import TabBar from "../tabbar/tabBar";
import './index.less'

//我的
class mine extends Component {


    componentWillUnmount () { }

    componentDidShow () { }

    componentDidHide () { }

    render () {
        return (


            <view>
                <TabBar tabBarCurrent={1} />
                <CaiDan/>
            </view>

        )
    }
}

export default mine

