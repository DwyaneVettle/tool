import React, { Component } from 'react'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { View, Text, Button } from '@tarojs/components'
import { CouponModal, SpImage } from '@/components'
import api from '@/api'
import S from '@/spx'
import { classNames, showToast } from '@/utils'
// import { Tracker } from '@/service'

import './coupon.scss'

export default class WgtCoupon extends Component {
  static options = {
    addGlobalClass: true
  }

  static defaultProps = {
    info: null,
    hasToast: true
  }

  state = {
    all_card_list: [],
    visible: false
  }

  constructor(props) {
    super(props)
  }

  handleClickNews = (card_item) => {
    if (!S.getAuthToken()) {
      showToast('请先登录再领取')

      setTimeout(() => {
        S.login(this)
      }, 2000)

      return
    }

    let templeparams = {
      temp_name: 'yykweishop',
      source_type: 'coupon'
    }

    let _this = this
    api.user.newWxaMsgTmpl(templeparams).then(
      (tmlres) => {
        if (tmlres.template_id && tmlres.template_id.length > 0) {
          wx.requestSubscribeMessage({
            tmplIds: tmlres.template_id,
            success() {
              _this.handleGetCard(card_item)
            },
            fail() {
              _this.handleGetCard(card_item)
            }
          })
        } else {
          _this.handleGetCard(card_item)
        }
      },
      () => {
        _this.handleGetCard(card_item)
      }
    )
  }

  handleGetCard = async (card_item) => {
    const query = {
      card_id: card_item.id
    }
    try {
      await api.member.homeCouponGet(query)

      showToast('优惠券领取成功')
      Tracker.dispatch('GET_COUPON', card_item)
    } catch (e) {}
  }

  navigateTo(url) {
    Taro.navigateTo({ url })
  }

  /** 券包领取 */
  handleCouponClick = (card_item) => {
    if (!S.getAuthToken()) {
      showToast('请先登录再领取')

      setTimeout(() => {
        S.login(this)
      }, 2000)

      return
    }
    api.vip.getReceiveCardPackage({ package_id: card_item.package_id }).then(() => {
      this.fetchCouponCardList()
    })
  }

  fetchCouponCardList() {
    api.vip.getShowCardPackage({ receive_type: 'template' }).then(({ all_card_list }) => {
      if (all_card_list && all_card_list.length > 0) {
        this.setState({ visible: true })
      } else {
        showToast('领取失败')
      }
      this.setState({ all_card_list })
    })
  }

  handleCouponChange = (visible, type) => {
    if (type === 'jump') {
      Taro.navigateTo({
        url: `/marketing/pages/member/coupon`
      })
    }
    this.setState({ visible })
  }

  render() {
    const { info, dis_id = '' } = this.props
    const { visible, all_card_list } = this.state
    if (!info) {
      return null
    }

    const { base, data, voucher_package } = info

    return (
      <View
        className={classNames('wgt wgt-coupon', {
          wgt__padded: base.padded
        })}
      >
        {base.title && (
          <View className='wgt-head'>
            <View className='wgt-hd'>
              <Text className='wgt-title'>{base.title}</Text>
              <Text className='wgt-subtitle'>{base.subtitle}</Text>
            </View>
          </View>
        )}
        <View className='wgt__body with-padding'>
          {data.map((item, idx) => {
            return (
              <View className={classNames('coupon-wgt', item.imgUrl && 'with-img')} key={`${idx}1`}>
                {' '}
                {item.imgUrl ? (
                  <View onClick={this.handleClickNews.bind(this, item)}>
                    <SpImage img-class='coupon_img' src={item.imgUrl} mode='widthFix' width='750' />
                  </View>
                ) : (
                  <View className='coupon-body'>
                    <View className='coupon-box'>
                      <View className='coupon__amount'>
                        <Text className='price'>{item.amount}</Text>
                        <View className='coupon__amount-cur'>
                          {item.type === 'cash' ? '元' : ''}
                          {item.type === 'discount' ? '折' : ''}
                        </View>
                      </View>
                      <View className='discount_type'>
                        {(item.type == 'discount' && '折扣券') ||
                          (item.type == 'cash' && '满减券') ||
                          (item.type == 'new_gift' && '兑换券')}
                      </View>
                    </View>
                    <View className='coupon-caption'>
                      <View className='coupon-content'>
                        <View className='coupon-content__brand-name'>{item.title}</View>
                        <View className='coupon-content__coupon-desc'>{item.desc}</View>
                      </View>
                    </View>
                  </View>
                )}
                {!item.imgUrl && (
                  <Button
                    className='coupon-btn__getted'
                    onClick={this.handleClickNews.bind(this, item)}
                  >
                    领取
                  </Button>
                )}
                {/*<View className='coupon-brand'>
                  <Image
                    className='brand-img'
                    mode='aspectFill'
                    src={item.imgUrl}
                  />
                </View>
                <View className='coupon-caption'>
                  <View className='coupon-amount'>
                    <Text>{item.amount}</Text>
                    {item.type === 'cash' && (<Text className='amount-cur'>RMB</Text>)}
                    {item.type === 'discount' && (<Text className='amount-cur'>折</Text>)}
                  </View>
                  <View className='coupon-content'>
                    <Text className='brand-name'>{item.title}</Text>
                    <Text className='coupon-desc'>{item.desc}</Text>
                  </View>
                </View>
                <Button
                  className='coupon-getted-btn'
                  onClick={this.handleGetCard.bind(this, item.id)}
                >领取</Button>*/}
              </View>
            )
          })}
          {voucher_package &&
            voucher_package.map((item, idx) => {
              return (
                <View
                  className={classNames('coupon-wgt', item.imgUrl && 'with-img')}
                  key={`${idx}1`}
                >
                  {' '}
                  {item.imgUrl ? (
                    <SpImage img-class='coupon_img' src={item.imgUrl} mode='widthFix' width='750' />
                  ) : (
                    <View className='coupon-body'>
                      <View className='coupon-box'>
                        <View className='coupon__amount'>
                          <Text className='price'>{item.amount}</Text>
                          <View className='coupon__amount-cur'>
                            {item.type === 'cash' ? '元' : ''}
                            {item.type === 'discount' ? '折' : ''}
                          </View>
                        </View>
                        <View className='discount_type'>
                          {(item.type == 'discount' && '折扣券') ||
                            (item.type == 'cash' && '满减券') ||
                            (item.type == 'new_gift' && '兑换券')}
                        </View>
                      </View>
                      <View className='coupon-caption'>
                        <View className='coupon-content'>
                          <View className='coupon-content__brand-name'>{item.title}</View>
                          <View className='coupon-content__coupon-desc'>{item.desc}</View>
                        </View>
                      </View>
                    </View>
                  )}
                  <Button
                    className='coupon-btn__getted'
                    onClick={this.handleCouponClick.bind(this, item)}
                  >
                    领取
                  </Button>
                </View>
              )
            })}
        </View>
        <CouponModal visible={visible} list={all_card_list} onChange={this.handleCouponChange} />
      </View>
    )
  }
}
