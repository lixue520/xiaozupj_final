import Taro from "@tarojs/taro";

//{nonce_str=DwnMAIxasDdf1Da3,
 //   code_url=weixin://wxpay/bizpayurl?pr=MZtgPHRzz,
    // appid=wxd9a46e74fc279fcc,
    // sign=0ED1BA7C80656522CB5D7F6BA7C7E0D5D715C580A6AA39D1F93B74D291D36DD6,
    // trade_type=NATIVE,
    // return_msg=OK,
    // result_code=SUCCESS,
    // mch_id=1623889015,
    // return_code=SUCCESS,
    // prepay_id=wx1010234773438683834697f4bd38980000}

//由于没有商户类的appid，因此考虑后端完成支付
const CallWeChatPay=
    Taro.requestPayment({
        timeStamp: Date.now()+"",
        nonceStr: 'DwnMAIxasDdf1Da3',
        package: 'prepay_id=wx1010234773438683834697f4bd38980000',
        signType: 'MD5',
        paySign: '0ED1BA7C80656522CB5D7F6BA7C7E0D5D715C580A6AA39D1F93B74D291D36DD6',
        success: function (res) { console.log({"成功:":res})},
        fail: function (res) {  console.log({"失败:":res}) }
    })
Taro.requestOrderPayment({
    orderInfo: {},
    timeStamp:  Date.now()+"",
    nonceStr: 'DwnMAIxasDdf1Da3',
    package: 'wx1010234773438683834697f4bd38980000',
    signType: 'MD5',
    paySign: '0ED1BA7C80656522CB5D7F6BA7C7E0D5D715C580A6AA39D1F93B74D291D36DD6',
    success (res) { console.log({"订单接口:支付成功":res})},
    fail (res) {console.log({"订单接口:支付失败":res}) }
})
console.log("----微信支付调起结束----");

export default CallWeChatPay