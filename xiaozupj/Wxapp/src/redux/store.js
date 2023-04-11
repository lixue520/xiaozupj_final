// 从redux中引入createStore
/*
  createStore 接受reducer作为参数,生成新的Store.
以后每当store.dispatch发送过来一个新的Action就会自动
调用reducer, 得到一个新的State
*/
import { createStore } from 'redux'
import reducer from './reducer' // reducer组件
let Store = createStore(reducer) // 生成一个新的Store
export default Store
