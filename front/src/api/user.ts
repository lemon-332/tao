import { Service } from '@/utils/request'
import { IUserLogin } from '@/types/user'
import { IResponse } from '@/types/response'

const BASE_URL = '/user'

export function login(data: IUserLogin): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/login`,
    data
  })
}

export function userList(params): Promise<IResponse> {
  return Service({
    url: `${BASE_URL}/userList`,
    params
  })
}
