import axios from 'axios'
const ConfigBaseURL = '/api' //默认路径，这里也可以使用env来判断环境
import { ElLoading, ElMessage } from 'element-plus'

let loadingInstance: any = null //这里是loading
//使用create方法创建axios实例
export const Service = axios.create({
  timeout: 7000, // 请求超时时间
  baseURL: ConfigBaseURL,
  method: 'post',
  headers: {
    'Content-Type': 'application/json;charset=UTF-8'
  }
})
// 添加请求拦截器
Service.interceptors.request.use((config) => {
  loadingInstance = ElLoading.service({
    lock: true,
    text: 'loading...'
  })
  return config
})
// 添加响应拦截器
Service.interceptors.response.use(
  (response) => {
    loadingInstance.close()
    return response.data
  },
  (error) => {
    console.log('TCL: error', error)
    const msg = error.Message !== undefined ? error.Message : ''
    ElMessage({
      message: '网络错误' + msg,
      type: 'error',
      duration: 3 * 1000
    })
    loadingInstance.close()
    return Promise.reject(error)
  }
)
