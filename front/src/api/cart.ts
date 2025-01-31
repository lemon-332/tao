import { Service } from '@/utils/request'
import { IResponse } from '@/types/response'

const BASE_URL = '/cart'



export function cartList(data?): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/cartList`,
    data
  })
}

// 导出一个函数，用于添加用户
export function addUser(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/userAdd`,
    data
  })
}

export function cartUpdate(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/cartUpdate`,
    data
  })
}

export function cartDelete(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/cartDelete`,
    data
  })
}
