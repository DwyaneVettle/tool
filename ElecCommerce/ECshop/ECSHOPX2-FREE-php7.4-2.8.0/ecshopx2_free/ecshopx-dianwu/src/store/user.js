import { createReducer } from 'redux-create-reducer'

const INITIAL_STATE = {
  user: 'test'
}

const user = createReducer(INITIAL_STATE, {
  ['user/landing'](state, action) {
    const user = action.payload

    return {
      ...state,
      user
    }
  }
})

export default user

