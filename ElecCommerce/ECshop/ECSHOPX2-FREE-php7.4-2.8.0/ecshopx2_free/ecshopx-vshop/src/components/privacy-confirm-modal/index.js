import React, { Component } from 'react'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { View, Image, Button, Text } from '@tarojs/components'
import api from '@/api'
import { connect } from 'react-redux'

import './index.scss'

@connect(({ colors }) => ({
  colors: colors.current
}))
export default class PrivacyConfirmModal extends Component {
  static defaultProps = {}
  constructor (props) {
    super(props)
    this.state = {
      info: null
    }
  }

  componentDidMount () {
    this.fetch()
  }

  handleClickAgreement = (type) => {
    Taro.navigateTo({
      url: '/subpages/auth/reg-rule?type=' + type
    })
  }

  async fetch () {
    const data = await api.shop.getStoreBaseInfo()
    this.setState({
      info: data
    })
  }

  wexinBindPhone = async (e) => {
    const { encryptedData, iv } = e.detail
    const { onChange } = this.props
    if (encryptedData && iv) {
      const { update_time } = await api.wx.getPrivacyTime()
      Taro.setStorageSync('policy_updatetime', update_time)
    }
    onChange && onChange('agree', e)
  }

  render () {
    const { info } = this.state
    const { visible, onChange, isPhone, colors } = this.props

    return (
      <View>
        {visible && (
          <View className='privacy-confirm-modal'>
            <View className='block'>
              <Image
                src='https://b-img-cdn.yuanyuanke.cn/image/21/2021/11/11/ceb224c25e89e4960dd85e30c82983f3oF9GVfNTQKu3n0hXhf2774ZxYlF1Yhgx'
                className='background'
              />
              <View className='container'>
                <View className='top'>
                  <Image src={`${process.env.APP_IMAGE_CDN}/privacy_tips.png`} className='tips' />
                  <View className='texts'>个人隐私保护指引</View>
                </View>
                <View className='content'>
                  <Text>
                    请您务必谨慎阅读，充分理解“用户协议”和“隐私政策”各条款。包括但不限于：为了向您提供更好的服务，我们须向您收集相关的个人信息。您可以在“个人信息”中查看、变更、删除、个人授权信息。您可阅读
                  </Text>
                  <Text
                    style={`color: ${colors.data[0].primary}`}
                    onClick={this.handleClickAgreement.bind(this, 'member_register')}
                  >
                    《{(info || { protocol: {} }).protocol.member_register}》
                  </Text>
                  <Text>、</Text>
                  <Text
                    style={`color: ${colors.data[0].primary}`}
                    onClick={this.handleClickAgreement.bind(this, 'privacy')}
                  >
                    《{(info || { protocol: {} }).protocol.privacy}》
                  </Text>
                  <Text>了解详细信息。如您同意，请点击”同意“开始接受我们的服务。</Text>
                </View>
                <View className='bottom-box'>
                  <Button className='cancel' onClick={() => onChange('reject')}>
                    拒绝
                  </Button>
                  {isPhone ? (
                    <Button
                      style={`background: ${colors.data[0].primary}`}
                      className='agree'
                      openType='getPhoneNumber'
                      onGetPhoneNumber={this.wexinBindPhone}
                    >
                      同意
                    </Button>
                  ) : (
                    <Button
                      style={`background: ${colors.data[0].primary}`}
                      onClick={() => onChange('agree')}
                      className='agree'
                    >
                      同意
                    </Button>
                  )}

                  {/* <Button onClick={() => onChange('agree')}>
                    <View className='agree'>同意</View>
                  </Button> */}
                </View>
              </View>
            </View>
          </View>
        )}
      </View>
    )
  }
}
