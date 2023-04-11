import {Component} from 'react';
import { AtTabBar }  from 'taro-ui'
import { View } from '@tarojs/components'
import Taro from '@tarojs/taro'
import action from "../../redux/action";
import {food} from "../OrderDishes/MyJson";

//底部选择框
class TabBar extends Component {

    handleClick (value) {
        console.log("tabBar ====" +value);

            if(value==0){
                Taro.reLaunch({
                    url: '/pages/index/index'
                });
            }else if(value==1){
                action.post(food);
                //刷新菜单
                action.GetCaiDan()
                Taro.reLaunch({
                    url: `/pages/OrderDishes/index` //'/pages/physicalIdentity/healthKnowledge'
                });
            }else if(value==2){
                Taro.reLaunch({
                    url: '/pages/OrderForm/index'
                });
            }else if(value==3){
                Taro.reLaunch({
                    url: '/pages/Mine/index'
                });
            }
        }


    render() {
      let BASEURL=`http://120.79.91.8/images/`
      return (
        <View>
          <AtTabBar
            fixed
            backgroundColor='#ffffff'
            color='#cccccc'
            selectedColor='#0F96DB'
            tabList={[
              { title: '首页', image: `${BASEURL}店铺首页1.png`, selectedImage:`${BASEURL}店铺首页.png` },
              { title: '点菜', image: `${BASEURL}购物车1.png`, selectedImage:`${BASEURL}购物车.png` },
              { title: '我的订单', image: `${BASEURL}订单1.png`, selectedImage:`${BASEURL}订单.png` },
              { title: '我的',image: `${BASEURL}我的1.png`, selectedImage:`${BASEURL}我的.png` },
            ]}
            onClick={this.handleClick.bind(this)}
            current={this.props.tabBarCurrent}
          />
        </View>
        );
    }
}

export default TabBar;
