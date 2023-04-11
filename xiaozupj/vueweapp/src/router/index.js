import Login from "@/views/login/Login";
//这里是全局加载
//import Index from "@/views/Index";
import User from "@/views/Table";
import FoodMange from "@/views/foodMange/FoodMange";
import FoodMange1 from "@/views/Menu";
import Role from "@/views/Role";
import MainContent from "@/views/MainContent";
import IndexHello from "@/views/IndexHello";
import Evaluate from "@/views/Evaluate";
import SlideShow from "@/views/SlideShow";
import ProfitStatistics from "@/views/ProfitStatistics";
import OrderManager from "@/views/OrderManager/OrderManager";
import UserAccess from "@/views/OrderManager/UserAccess";
import  UserPlease from "@/views/OrderManager/UserPlease";
import {createRouter, createWebHistory} from "vue-router/dist/vue-router";
//懒加载
const Index = () => import('../views/Index')//用到了才导入
/*
创建路由表
 */

const routes = [
    {
        path: '/',
        name: 'Login',
        component: Login
    },
    {
        path: '/index',
        name: 'Index',
        meta: {
            name: '首页'
        },
        component: Index,
        redirect: '/index/mainContent',
        children: [
            {
                path: 'mainContent',
                name: 'MainContent',
                meta: {
                    name: 'MainContent'
                },
                component: MainContent,
                redirect: {path: '/index/mainContent/indexHello'},
                children: [
                    {
                        path: 'foodMange',
                        name: 'FoodMange',
                        meta: {
                            name: '餐品列表'
                        },
                        component: FoodMange
                    },
                    {
                        path: 'user',
                        name: 'User',
                        meta: {
                            name: '用户管理'
                        },
                        component: User
                    },
                    {
                        path: 'indexHello',
                        name: 'IndexHello',
                        meta: {
                            name: '欢迎'
                        },
                        component: IndexHello
                    },
                    {
                        path: 'slideShow',
                        name: 'SlideShow',
                        meta: {
                            name: '轮播图'
                        },
                        component: SlideShow
                    },
                    {
                        path: '/index/mainContent/foodMange1',
                        name: 'FoodMange1',
                        meta:{
                            name: '菜单管理'
                        },
                        component:FoodMange1
                    },
                    {
                        path: '/index/mainContent/evaluate',
                        name: 'Evaluate',
                        meta:{
                            name: '评价管理'
                        },
                        component:Evaluate
                    },
                    {
                        path: '/index/mainContent/profitStatistics',
                        name: 'ProfitStatistics',
                        meta:{
                            name: '评价管理'
                        },
                        component:ProfitStatistics
                    },{
                        path: 'UserAccess',
                        name: 'UserAccess',
                        meta: {
                            name:'用户访问'
                        },
                        component: UserAccess
                    },{
                        path: 'AllOrder',
                        name: 'OrderManager',
                        meta: {
                            name:'全部订单'
                        },
                        component: OrderManager
                    },{
                        path: 'UserPlease',
                        name: 'UserPlease',
                        meta: {
                            name:'用户申请'
                        },
                        component: UserPlease
                    }

                ]
            }
        ]

    }
]

/*
创建路由实例
 */

const router = createRouter({
    history: createWebHistory(),
    routes
})

export {router}