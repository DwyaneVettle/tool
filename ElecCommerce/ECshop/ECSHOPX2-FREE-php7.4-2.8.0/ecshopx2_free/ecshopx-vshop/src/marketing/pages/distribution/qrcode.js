import React, { Component } from 'react'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { View, Image } from '@tarojs/components'
import { connect } from 'react-redux'
import { SpNavBar } from '@/components'
import api from '@/api'
import userIcon from '@/assets/imgs/user-icon.png'

import './qrcode.scss'

@connect(({ colors }) => ({
  colors: colors.current
}))
export default class DistributionQrcode extends Component {
  $instance = getCurrentInstance()
  constructor (props) {
    super(props)

    this.state = {
      info: {}
    }
  }

  componentDidMount () {
    const { colors } = this.props
    Taro.setNavigationBarColor({
      frontColor: '#ffffff',
      backgroundColor: colors.data[0].marketing
    })
    this.fetch()
  }

  async fetch () {
    const { username, avatar, userId } = Taro.getStorageSync('userinfo')
    let { isOpenShop, status } = this.$instance.router.params
    isOpenShop = JSON.parse(isOpenShop)
    status = JSON.parse(status)
    const url = isOpenShop && status ? `marketing/pages/distribution/shop-home` : `pages/index`
    const res = await api.distribution.qrcode({ path: url })
    const { qrcode } = res

    this.setState({
      info: {
        username,
        avatar,
        qrcode
      }
    })
  }

  render () {
    const { colors } = this.props
    const { info } = this.state

    return (
      <View className='page-distribution-qrcode' style={'background: ' + colors.data[0].marketing}>
        <SpNavBar title='二维码' leftIconType='chevron-left' />
        <View className='qrcode-bg'>
          <View className='title'>邀请卡</View>
          <Image className='avatar' src={info.avatar || userIcon} mode='aspectFit' />
          <View className='name'>{info.username}</View>
          <View className='welcome-words'>邀你一起加入，推广赢奖励</View>
          <View className='qrcode'>
            <Image src={info.qrcode} mode='aspectFit' />
          </View>
          <View className='tips'>微信扫一扫或长按识别</View>
        </View>
      </View>
    )
  }
}
