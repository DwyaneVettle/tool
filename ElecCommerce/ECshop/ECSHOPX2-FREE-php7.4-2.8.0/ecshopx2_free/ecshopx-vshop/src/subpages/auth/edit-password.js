import React, { useEffect } from 'react'
import { View, Text, Image } from '@tarojs/components'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { SpPage } from '@/components'
import { classNames, validate, showToast } from '@/utils'
import { AtForm, AtInput, AtButton } from 'taro-ui'
import api from '@/api'
import { setTokenAndRedirect, getToken, pushHistory, clearHistory } from './util'
import { useLogin } from '@/hooks'
import { useImmer } from 'use-immer'
import { PASSWORD_TIP } from './const'
import './edit-password.scss'

const SYMBOL = 'login'

const initialValue = {
  password: '',
  repassword: ''
}

const PageEditPassword = () => {
  const $instance = getCurrentInstance()

  const {
    params: { phone, unionid, vcode }
  } = $instance.router

  const [state, setState] = useImmer(initialValue)

  const { password, repassword } = state

  const handleInputChange = (name) => (val) => {
    setState((state) => {
      state[name] = val
    })
  }

  const { getUserInfo } = useLogin()

  const handleSubmit = async () => {
    if (!validate.isPassword(password) || !validate.isPassword(repassword)) {
      return showToast('密码格式不正确')
    }

    if (password !== repassword) {
      return showToast('2次输入密码不一致!')
    }

    const { user_id } = await api.user.forgotPwd({
      mobile: phone,
      password
    })
    if (user_id) {
      await setTokenAndRedirect(getToken(), async () => {
        await getUserInfo()
      })
    }
  }

  const loginSuccess = async () => {
    await setTokenAndRedirect(getToken(), async () => {
      await getUserInfo()
    })
  }

  //全填写完
  const isFull = phone && password && repassword && password.length >= 6 && repassword.length >= 6

  return (
    <SpPage
      className={classNames('page-auth-edit-password', {
        'is-full': isFull
      })}
    >
      <View className='auth-hd'>
        <View className='title'>设置密码</View>
        <View className='desc'>请设置密码完成注册</View>
      </View>
      <View className='auth-bd'>
        <AtForm className='form'>
          <View className='form-field'>
            <AtInput
              clear
              type='password'
              name='mobile'
              maxLength={11}
              type='tel'
              value={password}
              placeholder='请输入密码'
              onChange={handleInputChange('password')}
            />
          </View>
          <View className='form-field'>
            <AtInput
              clear
              name='mobile'
              maxLength={11}
              type='tel'
              value={repassword}
              placeholder='再次输入密码'
              onChange={handleInputChange('repassword')}
            />
          </View>
          <View className='form-tip'>{PASSWORD_TIP}</View>

          <View className='form-submit'>
            <AtButton
              disabled={!isFull}
              circle
              type='primary'
              className='login-button'
              onClick={handleSubmit}
            >
              完成
            </AtButton>
          </View>
        </AtForm>
      </View>
    </SpPage>
  )
}

export default PageEditPassword
