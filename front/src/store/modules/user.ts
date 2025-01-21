import {
  Module,
  VuexModule,
  Action,
  Mutation,
  getModule
} from 'vuex-module-decorators'

import store from '@/store'

export interface IUserState {
  userName: string
}

@Module({ dynamic: true, store, name: 'user' })
class User extends VuexModule implements IUserState {
  public userName = '23424'

  @Mutation
  increment() {
    this.userName += 'nihao'
  }

  @Mutation
  decrement() {
    this.userName += 'nihao'
  }

  @Action
  incrementAsync() {
    setTimeout(() => {
      this.increment()
    }, 1000)
  }

  get currentCount() {
    return this.userName
  }
}

export const UserModule = getModule(User)
