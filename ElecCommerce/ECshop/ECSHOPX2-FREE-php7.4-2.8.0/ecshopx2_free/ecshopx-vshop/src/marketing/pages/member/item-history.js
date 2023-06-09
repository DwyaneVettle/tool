import React, { Component } from 'react'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { View, ScrollView } from '@tarojs/components'
import { withPager, withBackToTop } from '@/hocs'
import { BackToTop, Loading, GoodsItem, SpNavBar, SpNote } from '@/components'
import api from '@/api'
import { pickBy } from '@/utils'

import './item-history.scss'

@withPager
@withBackToTop
export default class ItemHistory extends Component {
  constructor (props) {
    super(props)

    this.state = {
      ...this.state,
      list: []
    }
  }

  componentDidMount () {
    this.nextPage()
  }

  async fetch (params) {
    const { page_no: page, page_size: pageSize } = params
    const query = {
      page,
      pageSize
    }

    const { list, total_count: total } = await api.member.itemHistory(query)

    const nList = pickBy(list, {
      img: 'itemData.pics[0]',
      item_id: 'itemData.item_id',
      title: 'itemData.itemName',
      desc: 'itemData.brief',
      distributor_id: 'distributor_id',
      price: ({ itemData }) => (itemData.price / 100).toFixed(2),
      market_price: ({ itemData }) => (itemData.market_price / 100).toFixed(2)
    })

    this.setState({
      list: [...this.state.list, ...nList],
      query
    })

    return {
      total
    }
  }

  handleClickItem = (item) => {
    const url = `/pages/item/espier-detail?id=${item.item_id}&dtid=${item.distributor_id}`
    Taro.navigateTo({
      url
    })
  }

  render () {
    const { list, showBackToTop, scrollTop, page } = this.state

    return (
      <View className='page-goods-list page-goods-history'>
        <View className='goods-list__toolbar'>
          <SpNavBar leftIconType='chevron-left' fixed='true' />
        </View>

        <ScrollView
          className='goods-list__scroll'
          scrollY
          scrollTop={scrollTop}
          scrollWithAnimation
          onScroll={this.handleScroll}
          onScrollToLower={this.nextPage}
        >
          <View className='goods-list goods-list__type-grid'>
            {list.map((item) => {
              return (
                <GoodsItem
                  key={item.item_id}
                  info={item}
                  onClick={() => this.handleClickItem(item)}
                />
              )
            })}
          </View>
          {page.isLoading ? <Loading>正在加载...</Loading> : null}
          {!page.isLoading && !page.hasNext && !list.length && (
            <SpNote img='trades_empty.png'>暂无数据~</SpNote>
          )}
        </ScrollView>

        <BackToTop show={showBackToTop} onClick={this.scrollBackToTop} />
      </View>
    )
  }
}
