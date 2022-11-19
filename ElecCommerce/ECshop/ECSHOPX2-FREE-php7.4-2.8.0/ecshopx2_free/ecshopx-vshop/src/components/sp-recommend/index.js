import React, { Component } from 'react'
import { View, Image, Text } from '@tarojs/components'
import { SpGoodsItem, SpImage } from '@/components'
import { classNames, pickBy } from '@/utils'
import doc from '@/doc'
import './index.scss'

export default class SpRecommend extends Component {
  static options = {
    addGlobalClass: true
  }

  static defaultProps = {
    info: []
  }

  render() {
    const { info, className } = this.props
    if (info.length === 0) {
      return null
    }
    // const goods = pickBy(info, {
    //   cross: {
    //     key: 'origincountry_img_url',
    //     default: []
    //   },
    //   pic: 'pics[0]',
    //   itemId: 'item_id',
    //   itemName: 'itemName',
    //   brief: 'brief',
    //   promotion: 'promotion_activity',
    //   distributorId: 'distributor_id',
    //   isPoint: 'is_point',
    //   price: ({ act_price, member_price, price }) => {
    //     if (act_price > 0) {
    //       return act_price
    //     } else if (member_price > 0) {
    //       return member_price
    //     } else {
    //       return price
    //     }
    //   },
    //   marketPrice: 'market_price'
    // })
    const goods = pickBy(info, doc.goods.ITEM_LIST_GOODS)
    const leftList = goods.filter((item, index) => {
      if (index % 2 == 0) {
        return item
      }
    })
    const rightList = goods.filter((item, index) => {
      if (index % 2 == 1) {
        return item
      }
    })

    return (
      <View className={classNames('sp-recommend', className)}>
        <View className='sp-recommend-hd'>
          <Text className='sp-recommend-title'>热门推荐</Text>
        </View>
        <View className='sp-recommend-bd'>
          <View className='left-container'>
            {leftList.map((goods, index) => (
              <View className='goods-item-wrap' key={`goods-item-wrap__${index}`}>
                <SpGoodsItem info={goods} />
              </View>
            ))}
          </View>
          <View className='right-container'>
            {rightList.map((goods, index) => (
              <View className='goods-item-wrap' key={`goods-item-wrap__${index}`}>
                <SpGoodsItem info={goods} />
              </View>
            ))}
          </View>
        </View>
      </View>
    )
  }
}
