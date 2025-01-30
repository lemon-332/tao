import { Service } from '@/utils/request'
import { IResponse } from '@/types/response'

const BASE_URL = '/seller'

export function sellerList(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/sellerList`,
    data
  })
}

export function sellerInfo(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/sellerInfo`,
    data
  })
}

// 导出一个函数，用于添加用户
export function SellerAdd(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/sellerAdd`,
    data
  })
}

export function sellerRegisterList(): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/sellerRegisterList`
  })
}

export function sellerDelete(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/sellerDelete`,
    data
  })
}
