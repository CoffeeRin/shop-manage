import axios from 'axios';
import { useUserStore } from '@/stores/user'
import { showDialog } from "vant"

const userStore = useUserStore()

// 创建一个 axios 实例
const service = axios.create({
    baseURL: 'http://localhost:8089', // 所有的请求地址前缀部分
    timeout: 6000, // 请求超时时间毫秒
})

service.interceptors.request.use(
    config => {

        if (userStore.token != "")
            config.headers["token"] = userStore.token
        return config
    })

// 响应拦截器
service.interceptors.response.use(
    response => {
        if (response.data.code == -2) {
            showDialog({
                message: response.data.msg,
                theme: "round-button",
            }).then(() => {
                location.href = "/login"
            });

        } 
        return response.data
    })

export default service