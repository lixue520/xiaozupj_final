import React, { Component } from 'react'
import { View,Text } from '@tarojs/components'

// import IndexSlideshow from "./indexSlideshow";
// import IndexDiscounts from "./indexDiscounts";


import TabBar from "../tabbar/tabBar";



class Index extends Component {
  componentWillReceiveProps (nextProps) {
    console.log(this.props, nextProps)
  }

  componentWillUnmount () { }

  componentDidShow () { }

  componentDidHide () { }

  render () {
    return (
      <View className='index'>
        {/*<IndexSlideshow />*/}
        {/*<IndexDiscounts />*/}
        <TabBar tabBarCurrent={0} />
      </View>
    )
  }
}

export default Index

