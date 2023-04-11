// 导入所有接口
import api from './api'

/*全局属性配置方法
const app = createApp(App);
app.config.globalProperties.$user = {
    name: '梅长苏',
    weapons: '长剑',
    title: '刺客'
}*/

export default {
    install: (app) => {
        app.config.globalProperties.$api = api;
    }
}