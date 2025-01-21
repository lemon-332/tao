import { Service } from '@/utils/request'

export function login(userName: any) {
  return Service({
    url: 'user/login',
    data: JSON.stringify(userName)
  })
}