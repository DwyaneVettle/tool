import React, { Component } from 'react'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { View, Text } from '@tarojs/components'
import { connect } from 'react-redux'
import { isObjectsValue } from '@/utils'

import './address.scss'

@connect(
  ({ user }) => ({
    address: user.address
  }),
  (dispatch) => ({
    updateChooseAddress: (address) =>
      dispatch({ type: 'user/updateChooseAddress', payload: address })
  })
)
export default class AddressChoose extends Component {
  static defaultProps = {
    onClickBack: () => {}
  }

  constructor (props) {
    super(props)

    this.state = {}
  }

  static options = {
    addGlobalClass: true
  }

  clickTo = (choose) => {
    if (this.props.onCustomChosse) {
      this.props.onCustomChosse(choose)
    } else {
      Taro.navigateTo({
        url: `/marketing/pages/member/address?isPicker=${choose}`
      })
    }
  }

  render () {
    const { isAddress } = this.props

    return (
      <View className='address-picker'>
        <View className='address' onClick={this.clickTo.bind(this, 'choose')}>
          {isObjectsValue(isAddress) ? (
            <View className='address-picker__bd'>
              <View className='address-receive'>
                <View className='info-trade'>
                  <View className='address-area'>
                    {isAddress.is_def && <View className='def'>默认</View>}
                    {isAddress.province}
                    {isAddress.city}
                    {isAddress.county}
                  </View>
                  <View className='address-detail'>{isAddress.adrdetail}</View>
                  <View className='user-info-trade'>
                    <Text className='name'>{isAddress.username}</Text>
                    <Text>{isAddress.telephone}</Text>
                  </View>
                </View>
              </View>
              <View className='sp-cell__ft-icon iconfont icon-arrowRight'></View>
            </View>
          ) : (
            <View className='address-info__bd'>+请选择收货地址</View>
          )}
        </View>
      </View>
    )
  }
}
