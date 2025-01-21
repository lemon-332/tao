// store/indx.ts
import { createStore } from 'vuex'
import { IUserState } from './modules/user'

export interface IRootState {
  user: IUserState
}

const store = createStore<IRootState>({})

export default store
