
export default {
    state: {
        // 全局属性
        // 如果没有模块化，很多模块的属性，都在这里声明
        //声明一个数组navTree
        navTree: []
    },
    // 同步操作赋值
    mutations: {
        // 赋值操作
        setNavTree(state, navTree) {
            state.navTree = navTree;
            console.log("给navTree赋值：", navTree)
        }
    },
    actions: {
        /*
        如果有异步操作，网络请求某个数据后，再进行赋值
        同步：一件事完了才能做下一件事
        异步：同时进行，不用等待；
         */
        //payload 就是数据,对象
        setNavTree(ctx, payload) {
            console.log('异步赋值->载荷：', payload.data);
            console.log('type: ', payload.type)
            ctx.commit(payload.type, payload.data);//调用mutations的setNavTree，payload是一个对象，传过来的
        }
    },
    getters: {
        getNavTree(state) {
            return state.navTree
        }
    }
}