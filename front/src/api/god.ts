import { Service } from '@/utils/request'
import { IResponse } from '@/types/response'

const BASE_URL = '/god'

export function godList(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/godList`,
    data
  })
}

// 导出一个函数，用于添加用户
export function godAdd(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/godAdd`,
    data
  })
}

export function godUpdate(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/godUpdate`,
    data
  })
}

export function godDelete(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/godDelete`,
    data
  })
}

export function godUpload(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/godUpload`,
    data,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
}

export function godDownload(data): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/godDownload`,
    data
  })
}

export function godReport(): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/godReport`
  })
}
