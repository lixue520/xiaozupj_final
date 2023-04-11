import {createApp} from 'vue'
import App from './App.vue'
import {router} from "@/router";
import api from "@/http/index"
import store from '@/store/index'
import 'font-awesome/css/font-awesome.min.css'
import SvgIcon from '@/icons'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'




// router作为全局插件来使用

// createApp(App).use(ElementPlus).use(router).use(api).use(store).mount('#app')


const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
// const app = createApp(App)

SvgIcon(app)
app.use(ElementPlus).use(router).use(api).use(store).mount('#app')
// app.use(store).use(router).use(i18n).mount('#app')
