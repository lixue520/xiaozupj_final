//Log:20220808优化想法1:分析操作，一次JSON处处更新，降低操作数据的复杂度
import {Button, Image, Input, View} from "@tarojs/components";
import {AtCard, AtCheckbox, AtForm, AtInput, AtInputNumber} from "taro-ui";

import store from "../../redux/store";
import action from "../../redux/action";
import {Component} from "react";
import Taro from "@tarojs/taro";
import {ImgUrl} from "../OrderDishes/MyJson";
import ShoppingCart from "./ShoppingCart";

class CardGenerato extends Component {
    constructor() {
        super(...arguments);
        this.state={
            checkedList:[],
            curID:0, //选中值
            count:1,
            ValueList:[], //将Num值插入，便于后面的计算
        }
    }


    handleChange (value) {     //2.在点击时修改，不管
        this.setState({
            checkedList: value,
        })
        console.log({"选项被选中:":value });
        //将操作立即发送到redux的state存放，并将其计算显示在固定在tarBar更上面的View中
       action.CheckQueue(value);
    }

    componentWillReceiveProps (nextProps) {
        console.log(this.props, nextProps)
    }

    findItem=(e)=>{
        let {Order} = store.getState();
        let cid = e.currentTarget.dataset['index'];
        this.setState({
            curID:cid  //更新选中ID值
        })
        let cnum= Order[cid].num; //找到当前id下的num值
        console.log({"当前商品id:":cid,"当前商品num:":cnum})
    }

    BanUpper=()=>{
        let arr=[];
       var {Order} = store.getState();
       console.log("订单表生成器获取数据Order")
        console.log(Order)
        for(let i=0;i<Order.length;i++){ //因为元数据只放了一个对象
            arr.push(<AtCard key={i} note={Order[i].AtCard.type}
                             extra={"商品ID："+Order[i].id}
                             title={Order[i].AtCard.name}
                             thumb={ImgUrl+Order[i].AtCard.img}>
                <view className='at-row'>
                    <view>
                        <AtCheckbox
                        options={[{value:Order[i].id}]} //更新菜单时
                        selectedList={this.state.checkedList}
                        onChange={this.handleChange.bind(this)}
                        className="imgtest"
                    />
                    </view>
                    <View className='at-col'>
                        <Image src={ImgUrl+Order[i].AtCard.img} className="MyCard-list">
                        </Image>
                    </View>
                    <view>
                        <View> {"价格:"+Order[i].AtCard.price}</View>
                        <View style="margin-left:100px" className="Input-box" data-index={`${i}`}>
                                <Button data-index={`${i}`} className="Button-test"
                                        onClick={(e)=>{
                                            let cid =  parseInt(e.currentTarget.dataset['index']);
                                            let {Order}=store.getState();
                                            action.AorD({AtCard:Order[cid].AtCard,id:(Order[cid].id)+"",num:Order[cid].num},"ADD")
                                            console.log({"加法执行:":Order[i].num,"当前组件ID:":cid})
                                            this.setState({}) //刷新状态
                                        }}
                                >+</Button>
                                <Input  data-index={`${i}`} className="input-test"
                                        onBlur={(e)=>{
                                          //失去焦点
                                            console.log(e.detail.value)
                                            let cid =  e.currentTarget.dataset['index']
                                            action.modOrder({AtCard:Order[cid].AtCard,id:(Order[cid].id)+"",num:e.detail.value})
                                        }}
                                        value={ Order[i].num} type="number"/>
                                <Button data-index={`${i}`} className="Button-test" onClick={
                                    (e)=>{
                                        let cid =  e.currentTarget.dataset['index']
                                        let {Order}=store.getState();
                                        action.AorD({AtCard:Order[cid].AtCard,id:(Order[cid].id)+"",num:Order[cid].num},"DCC")
                                        console.log({"减法执行:":Order[cid].num,"当前组件ID:":cid})
                                        this.setState({}) //刷新状态
                                    }
                                }>-</Button>
                            <Button data-index={`${i}`} className="Button-test" onClick={(e)=>{
                                let id = e.currentTarget.dataset['index']
                                console.log({"将此商品删除:":id})
                                //修改购物车
                                action.ModCar({AtCard:Order[id].AtCard,id:(Order[id].id)+"",num:Order[id].num});
                                Taro.reLaunch({
                                    url: '/pages/OrderForm/index'
                                })
                            }}>删</Button>
                        </View>
                    </view>
                </view>
            </AtCard>)
        }
        return arr;
    }
    render() {

        return(
            <View>
                <View>
                    {this.BanUpper()}
                    <ShoppingCart/>
                </View>
            </View>
        )
    }

}

export default CardGenerato