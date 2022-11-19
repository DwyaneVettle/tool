import React, { Component } from 'react'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { AtNavBar } from 'taro-ui'
import { connect } from 'react-redux'
import { View } from '@tarojs/components'
import api from '@/api'
import { Loading, SpNavBar, SpToast } from '@/components'
import { pickBy, browser, getDistributorId } from '@/utils'
import { withLogin } from '@/hocs'
import { AlipayPay, WeH5Pay, WePay } from './comps'
import getPaymentList from '@/utils/payment'
import { PAYTYPE } from '@/consts'
import { deleteForm } from './util'

import './index.scss'

@connect(({ sys }) => ({
  pointName: sys.pointName
}))
@withLogin()
export default class Cashier extends Component {
  $instance = getCurrentInstance()
  state = {
    info: null,
    env: '',
    isHasAlipay: true,
    payType: PAYTYPE.WXH5
  }

  componentDidShow() {
    this.fetch()
    deleteForm()
  }

  async componentDidMount() {
    const { isHasAlipay } = await getPaymentList(getDistributorId())
    // const isHasAlipay = []
    this.setState({
      isHasAlipay
    })
  }

  isPointitemGood() {
    const options = this.$instance.router.params
    return options.type === 'pointitem'
  }

  async fetch() {
    const { order_id, pay_type = PAYTYPE.WXH5, id } = this.$instance.router.params

    let env = ''
    if (browser.weixin) {
      debugger
      env = 'WX'
    }

    Taro.showLoading()
    const orderInfo = await api.cashier.getOrderDetail(order_id || id)

    const info = pickBy(orderInfo.orderInfo, {
      order_id: 'order_id',
      order_type: 'order_type',
      pay_type: 'pay_type',
      point: 'point',
      title: 'title',
      total_fee: ({ total_fee }) => (total_fee / 100).toFixed(2)
    })

    this.setState({
      info,
      env,
      payType: pay_type
    })
    Taro.hideLoading()
  }

  handleClickBack = () => {
    const { order_type, order_id } = this.state.info
    const url =
      order_type === 'recharge' ? '/pages/member/pay' : `/subpage/pages/trade/detail?id=${order_id}`
    Taro.redirectTo({
      url
    })
  }

  render() {
    const { info, env, isHasAlipay, payType } = this.state

    if (!info) {
      return <Loading />
    }

    return (
      <View className='page-cashier-index'>
        <AtNavBar
          fixed
          color='#000'
          title='收银台'
          leftIconType='chevron-left'
          onClickLeftIcon={this.handleClickBack}
        />
        <View className='cashier-money'>
          {info.order_type !== 'recharge' ? (
            <View className='cashier-money__tip'>订单提交成功，请选择支付方式</View>
          ) : null}
          <View className='cashier-money__content'>
            <View className='cashier-money__content-title'>订单编号： {info.order_id}</View>
            <View className='cashier-money__content-title'>订单名称：{info.title}</View>
            <View className='cashier-money__content-title'>
              应付总额
              {info.pay_type === 'point' ? `（${this.props.pointName}）` : '（元）'}
            </View>
            <View className='cashier-money__content-number'>
              {info.pay_type === 'point' ? info.point : info.total_fee}
            </View>
          </View>
        </View>
        {!env ? (
          <View>
            {isHasAlipay && payType === PAYTYPE.ALIH5 && (
              <AlipayPay orderID={info.order_id} payType='alipayh5' orderType={info.order_type} />
            )}
            {payType === PAYTYPE.WXH5 && <WeH5Pay orderID={info.order_id} />}
          </View>
        ) : (
          <View>
            <WePay info={info} />
          </View>
        )}
        <SpToast />
      </View>
    )
  }
}
