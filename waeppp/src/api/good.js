// 引用网络请求中间件
import request from '../utils/request'
/**
 *    登陆请求
 */
const base = '/god'
export function goodList(data) {
  return request({
    url: `${base}/godList`,
    method: 'POST',
    data
  })
}
