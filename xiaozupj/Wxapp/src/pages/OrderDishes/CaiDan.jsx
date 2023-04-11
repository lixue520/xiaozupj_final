
//点菜
import store from "../../redux/store";
import {Component} from "react";
import {Button, Image, ScrollView, View} from "@tarojs/components";
import {AtCard} from "taro-ui";
import {food, ImgUrl, scrollStyle} from "./MyJson";
import "./index.scss";
import action from "../../redux/action";


class CaiDan extends Component {
  componentWillReceiveProps (nextProps) {
    console.log(this.props, nextProps)
  }
  //组件即将要卸载时执行
  componentWillUnmount () {

  }

  //即将挂载前：propType=>constructor=>willMount=>render=>DidMount(已经挂载)=>组件运行=>WillUnMount(卸载)
  //  组件运行同级运行生命周期：父组件=>WillReceiveProps==>
    //  shouldCompentUpdate<==state改变==>WillUpdate=>render=>DidUpdate最终消亡
  componentWillMount() {
      let {CaiDan} = store.getState()
      let NewData=[];
      for(let i=0;i<CaiDan.data.length;i++){
          NewData.push(CaiDan.data[i].type);
      }
      let arr = Array.from(new Set(NewData));
      console.log(arr);
      console.log("索引去重")
      this.setState({
          CaiDanA: arr
      })
  }


    componentDidShow () {

  }
  //程序切后台时触发。
  componentDidHide () {
  }
  constructor () {
    super(...arguments)
    this.state = {
      current: 0,
        test:1,
        index:1,
        CaiDanA:null
    };
  }


    onScrollToUpper() {}

    onScroll=(e)=>{
        console.log(e.detail)
            this.setState({
                test:this.state.test=e.detail.scrollTop
            })
    }

     fun=(e)=> {
        let query = e.currentTarget.dataset['index'];
        //成功获取左侧index数据
        console.log(query)
        //执行调度,该事件修改状态值
         this.setState({
             index:this.state.index=1
         })
         this.setState({
             index:this.state.index=(query*140*2)
         })
    }
    //创建左侧index，替代原始键值
     Aan=()=>{
        let newarr=[];
        for(let i=0; i<this.state.CaiDanA.length; i++){
            //可以考虑用过滤算法排除相同字符
            newarr.push(<View
                data-index={`${i}`}
                id={`${i}`}
                onClick={this.fun}
                className="test">
                   <View className="imgtest"><Image src={require("../../static/image/汉堡.png")} className="imgtest"></Image>
                   </View>
                    <View >
                        {this.state.CaiDanA[i]}
                    </View>
            </View>) //记得设置字体大小
        }
        return newarr;
    }

  render() {
      let {CaiDan} = store.getState() //获取状态,解构赋值
      console.log(CaiDan.data[0].name);
      function Ban(){
          let newarr=[];
          for(let i=0;i<CaiDan.data.length;i++){
              newarr.push(<AtCard key={i} note={CaiDan.data[i].name}
                                  extra={"商品ID:"+CaiDan.data[i].id}
                                  title={CaiDan.data[i].name}
                                  thumb={ImgUrl+CaiDan.data[i].img}>
                  <view className='at-row'>
                      <View className='at-col'>
                          <Image src={ImgUrl+CaiDan.data[i].img} className="MyCard-list">
                          </Image>
                          <Button data-index={`${i}`} className="Button-test" onClick={(e)=>{
                              //将顾客选的商品id加到购物车,状态更新，发送数据时，要过滤重复信息，
                              action.postOrder({AtCard:CaiDan.data[i],
                                  id:(CaiDan.data[parseInt(e.currentTarget.dataset['index'])].id)+"",
                                  num:1 //点一次，后期更新弹窗改数功能
                              })
                              // Taro.reLaunch({
                              //     url: '/pages/OrderForm/index'
                              // });
                          }}>加入购物车</Button>
                      </View>
                      <view className='ar-col'>
                          {food.AtCard.text}
                      </view>
                  </view>
                  </AtCard>)
          }
          return newarr;
      }

    return (
      <View>
        <View className="box">
          <View className="title">天天华莱士</View>
          <View className="info">
          </View>
            <View>
                {/*建议使用绑定换算绑定scolltop的位置*/}
                <View className="detail">
                    <View className="leftbox">
                        <ScrollView
                            className='scrollview'
                            scrollY
                            scrollWithAnimation
                            scrollTop={this.state.test}//大概在10的位置，需要计算1：140;可传值改动,并且需要上升到状态，后期封装成json
                            style={scrollStyle}
                            lowerThreshold={20}
                            upperThreshold={20}
                            onScrollToUpper={this.onScrollToUpper.bind(this)} // 使用箭头函数的时候 可以这样写 `onScrollToUpper={this.onScrollToUpper}`
                            // onScroll={this.onScroll}//避免同步影响，必须屏蔽进行异步操作
                        >
                        {this.Aan()}
                        </ScrollView>
                    </View>
                    <View className="rightbox">
                        <ScrollView
                            className='scrollview'
                            scrollY
                            scrollWithAnimation
                            scrollTop={this.state.index}//大概在10的位置，需要计算1：140;可传值改动,并且需要上升到状态，后期封装成json
                            style={scrollStyle}
                            lowerThreshold={20}
                            upperThreshold={20}
                            onScrollToUpper={this.onScrollToUpper.bind(this)} // 使用箭头函数的时候 可以这样写 `onScrollToUpper={this.onScrollToUpper}`
                            onScroll={this.onScroll}
                        >
                            {Ban()}
                        </ScrollView>
                    </View>
            </View>
          </View>
        </View>
      </View>
    )
  }
}

export default CaiDan

