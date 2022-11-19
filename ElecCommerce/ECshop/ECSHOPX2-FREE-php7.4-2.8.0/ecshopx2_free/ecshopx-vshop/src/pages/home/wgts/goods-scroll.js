import React, { Component } from 'react'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { View, Text, Image, ScrollView } from '@tarojs/components'
import { AtCountdown } from 'taro-ui'
import { SpGoodsItem, SpImage } from '@/components'
import { calcTimer, classNames, isWeb, linkPage, getDistributorId, pickBy } from '@/utils'
import doc from '@/doc'

import './goods-scroll.scss'

export default class WgtGoodsScroll extends Component {
  static options = {
    addGlobalClass: true,
    info: null
  }

  constructor(props) {
    super(props)

    this.state = {
      timer: null
      // boxHeight: null
    }
  }

  setTimer() {
    const { info } = this.props
    const { config } = info
    if (config.lastSeconds) {
      const timer = calcTimer(config.lastSeconds)
      this.setState({
        timer
      })
    }
  }

  componentDidMount() {
    this.setTimer()
    // if (this.props.info.data.length > 0) this.getDom()
  }

  navigateTo(url) {
    Taro.navigateTo({ url })
  }

  handleClickItem(item) {
    const { distributor_id, goodsId } = item
    // const dtid = distributor_id ? distributor_id : getDistributorId()
    Taro.navigateTo({
      url: `/pages/item/espier-detail?id=${goodsId}&dtid=${distributor_id || 0}`
    })
  }

  navigateToList = (type, seckillId) => {
    if (type === 'goods') {
      this.navigateTo(`/pages/item/list?dis_id=${this.props.dis_id || ''}`)
    } else if (type === 'limitTimeSale') {
      Taro.navigateTo({
        url: `/marketing/pages/item/seckill-goods-list?seckill_type=limited_time_sale&seckill_id=${seckillId}&dis_id=${
          this.props.dis_id || ''
        }`
      })
    } else {
      Taro.navigateTo({
        url: `/marketing/pages/item/seckill-goods-list?seckill_type=normal&seckill_id=${seckillId}&dis_id=${
          this.props.dis_id || ''
        }`
      })
    }
  }

  handleClickMore = () => {
    const { config } = this.props.info
    const { moreLink = {} } = config
    if (moreLink) {
      linkPage(moreLink)
    } else {
      this.navigateToList(config.type, config.seckillId)
    }
  }

  render() {
    const { info } = this.props
    if (!info) {
      return null
    }

    const { base, data, config, more } = info
    const { timer, boxHeight } = this.state
    const goods = pickBy(data, doc.goods.WGT_GOODS_GRID)

    return (
      <View className={`wgt wgt-goods-scroll ${base.padded ? 'wgt__padded' : null}`}>
        {base.title && (
          <View className='wgt-head'>
            <View className='wgt-hd'>
              <Text className='wgt-title'>{base.title}</Text>
              {config.type === 'goods' ? (
                <Text className='wgt-subtitle'>{base.subtitle}</Text>
              ) : (
                <View className='wgt-timer'>
                  {timer && config.lastSeconds != 0 ? (
                    <View>
                      <AtCountdown
                        className='countdown__time'
                        isShowDay
                        day={timer.dd}
                        hours={timer.hh}
                        minutes={timer.mm}
                        seconds={timer.ss}
                      />
                      <Text className='time-fonts'>
                        {config.status === 'in_the_notice' ? '后开始' : '后结束'}
                      </Text>
                    </View>
                  ) : (
                    <View className='countdown__time'>活动已结束</View>
                  )}
                </View>
              )}
            </View>
            {config.moreLink.linkPage && (
              <View className='wgt-more' onClick={this.handleClickMore}>
                <View className='three-dot'></View>
              </View>
            )}
          </View>
        )}
        <View className='wgt-body'>
          <ScrollView className='scroll-goods' scrollX>
            {goods.map((item, idx) => (
              <View
                className={classNames('scroll-item', {
                  'lastItem': idx === goods.length - 1
                })}
              >
                {config.leaderboard && (
                  <View className='subscript'>
                    <View className='subscript-text'>NO.{idx + 1}</View>
                    <SpImage className='subscript-img' src='paihang.png' />
                  </View>
                )}
                <SpGoodsItem
                  showPrice={config.showPrice}
                  info={item}
                  key={`scroll-goods-item__${idx}`}
                />
              </View>
            ))}
            {/* {data.map((item, idx) => {
              const price = (
                (item.act_price
                  ? item.act_price
                  : item.member_price
                  ? item.member_price
                  : item.price) / 100
              ).toFixed(2)
              const marketPrice = (
                (item.act_price ? item.price : item.member_price ? item.price : item.market_price) /
                100
              ).toFixed(2)
              return (
                <View
                  key={`${idx}1`}
                  className={classNames('scroll-item', {
                    'lastItem': idx === data.length - 1
                  })}
                  onClick={this.navigateTo.bind(
                    this,
                    `/pages/item/espier-detail?id=${item.goodsId}&dtid=${item.distributor_id}`
                  )}
                >
                  {config.leaderboard && (
                    <View className='subscript'>
                      <View className='subscript-text'>NO.{idx + 1}</View>
                      <Image className='subscript-img' lazyLoad src='/assets/imgs/paihang.png' />
                    </View>
                  )}
                  <View className='thumbnail'>
                    <Image src={item.imgUrl} className='goods-img' lazyLoad />
                  </View>
                  {item.title && <View className='subscript-title'>{item.title}</View>}
                  {item.type === '1' && (
                    <View className='nationalInfo'>
                      <Image
                        className='nationalFlag'
                        src={item.origincountry_img_url}
                        mode='aspectFill'
                        lazyLoad
                      />
                      <Text className='nationalTitle'>{item.origincountry_name}</Text>
                    </View>
                  )}
                  {config.showPrice && (
                    <View className='subscript-price'>
                      <View className='goods-price'>
                        <Text className='cur'>¥</Text>
                        {price}
                      </View>
                      {marketPrice != 0 && (
                        <View className='market-price'>
                          <Text className='cur'>¥</Text>
                          {marketPrice}
                        </View>
                      )}
                    </View>
                  )}
                </View>
              )
            })} */}

            {config.moreLink.linkPage && (
              <View className='more_img' onClick={this.handleClickMore}>
                <View className={`img ${isWeb ? 'h5-img' : ''}`}>
                  <Image src={base.backgroundImg} className='goods-img' lazyLoad />
                </View>
                <View className='text'>查看更多</View>
              </View>
            )}
          </ScrollView>
        </View>
      </View>
    )
  }
}
