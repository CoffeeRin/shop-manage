import { createApp } from 'vue'
import { createPinia } from 'pinia'

import 'vant/lib/index.css';
import Vant from "vant"
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import piniaPersist from 'pinia-plugin-persist'

const app = createApp(App)
const pinia = createPinia()
pinia.use(piniaPersist)

app.use(ElementPlus)
app.use(pinia)
app.use(Vant)
app.use(router)

app.mount('#app')//app对象挂载到#app
