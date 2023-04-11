import React from "react";
import {Text, View} from "@tarojs/components";
import * as Taro from "@tarojs/taro";


class addressList extends Component{

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

    componentDidMount() {
        Taro.getStorage({
            key: 'address',
            success:(res)=> {
                this.setState({
                    address:res.data.address,
                    name:res.data.name,
                    telephone:res.data.telephone
                })
            }
        })
    }


    render(){
        return(
            <View>
                {'123'}
            </View>
        )
    }
}

export default addressList