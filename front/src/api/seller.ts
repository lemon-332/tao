import { Service } from '@/utils/request'
import { IResponse } from '@/types/response'

const BASE_URL = '/seller'

export function userList(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/userList`,
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

export function userUpdate(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/userUpdate`,
    data
  })
}

export function userDelete(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/userDelete`,
    data
  })
}
