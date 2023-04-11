import {Component} from "react";
import {Button, View} from "@tarojs/components";
import "./index.scss"
class InputNumber extends Component{

    render() {
        return(
            <View className="Input-box">
                <Button className="Button-test">+</Button>
                <input className="input-test" type="number"/>
                <Button className="Button-test">-</Button>
            </View>
        )
    }
}

export default InputNumber