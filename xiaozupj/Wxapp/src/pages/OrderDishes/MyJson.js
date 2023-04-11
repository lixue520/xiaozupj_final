//数据封装-->显示菜单数据（应该从后台获取）
//备用数据
//用来测试生成数据
let ImgUrl="http://120.27.199.8:8080/images/caidan/"
var Order={
    "AtCard":[
        {
            "note":"美食家老八的小Tips",
            "extra":"详情",
            "title":"老八秘制汉堡",
            "thumb":"http://cnhls.com/Upload/img/taocan/%E5%A5%97%E9%A4%90/%E5%8A%B2%E8%84%86%E9%B2%9C%E8%99%BE%E5%A0%A1%E5%A5%97%E9%A4%90-17094366268.jpg",
            "Image":"http://cnhls.com/Upload/img/taocan/%E5%A5%97%E9%A4%90/%E5%8A%B2%E8%84%86%E9%B2%9C%E8%99%BE%E5%A0%A1%E5%A5%97%E9%A4%90-17094366268.jpg",
            "text":"很喜欢这家的华莱士汉堡，天天来真的不骗你"
        }
    ]
}
var food={
    "index":[
        {
            "name":"进群拿鸡",
            "img":""
        },{
            "name":"暑期特惠",
            "img":""
        },{
            "name":"新品尝鲜",
            "img":""
        },{
            "name":"舔手套餐",
            "img":""
        },{
            "name":"8月每日特价",
            "img":""
        },{
            "name":"招牌全鸡",
            "img":""
        },{
            "name":"超值桶系列",
            "img":""
        },{
            "name":"超值套餐",
            "img":""
        },{
            "name":"精选主食",
            "img":""
        },{
            "name":"美味小食",
            "img":""
        },{
            "name":"缤纷饮品",
            "img":""
        }
    ],
    "AtCard":[
        {
            "note":"美食家老八的小Tips",
            "extra":"详情",
            "title":"老八秘制汉堡",
            "thumb":"http://cnhls.com/Upload/img/taocan/%E5%A5%97%E9%A4%90/%E5%8A%B2%E8%84%86%E9%B2%9C%E8%99%BE%E5%A0%A1%E5%A5%97%E9%A4%90-17094366268.jpg",
            "Image":"http://cnhls.com/Upload/img/taocan/%E5%A5%97%E9%A4%90/%E5%8A%B2%E8%84%86%E9%B2%9C%E8%99%BE%E5%A0%A1%E5%A5%97%E9%A4%90-17094366268.jpg",
            "text":"很喜欢这家的华莱士汉堡，天天来真的不骗你"
        }
    ]
}

//首页栅格操作菜单和下方的标签页
var  indexdata={
     "tabList" : [{ "title": '美味食谱' }, { "title": '新闻资讯' }, { "title": '华粉天地' }],
     "data":[
        {
            "image": require('../../static/image/汉堡.png'),
            "value": '立即点餐'
        },
        {
            "image": require('../../static/image/折扣券.png'),
            "value": '折扣券'
        },
        {
            "image": require('../../static/image/优惠券.png'),
            "value": '优惠券'
        },
        {
            "image": require('../../static/image/积分商城.png'),
               "value": '赚积分'
        },
        {
            "image": require('../../static/image/套餐.png'),
            "value": '新品套餐'
        },
        {
            "image": require('../../static/image/会员.png'),
            "value": '领会员'
        }
        ],
    "SwiperItem":[
        {
            "className":"demo-text-1",
            "Image":"http://cnhls.com/Upload/img/banner/banner4-16541426734.jpg"
        },
        {
            "className":"demo-text-2",
            "Image":"http://cnhls.com/Upload/img/banner/banner4-16541426734.jpg"
        },
        {
            "className":"demo-text-3",
            "Image":"http://cnhls.com/Upload/img/banner/banner4-16541426734.jpg"
        },
        {
            "className":"demo-text-4",
            "Image":"http://cnhls.com/Upload/img/banner/banner4-16541426734.jpg"
        }
    ]
}


//调整滑动空间范围
const scrollStyle={
    height:'250px'
}
export  {food,scrollStyle,indexdata,Order,ImgUrl}