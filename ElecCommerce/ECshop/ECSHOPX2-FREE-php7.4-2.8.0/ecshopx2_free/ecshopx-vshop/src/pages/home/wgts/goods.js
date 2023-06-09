import React, { Component } from 'react'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { View, Text } from '@tarojs/components'
import { SpImg } from '@/components'
import { connect } from 'react-redux'
// import { Tracker } from '@/service'
import S from '@/spx'
import entry from '@/utils/entry'
import { getDistributorId, VERSION_STANDARD } from '@/utils'
import api from '@/api'

import './goods.scss'

@connect(
  ({ cart, user }) => ({
    cart,
    favs: user.favs
  }),
  (dispatch) => ({
    onFastbuy: (item) => dispatch({ type: 'cart/fastbuy', payload: { item } }),
    onAddCart: (item) => dispatch({ type: 'cart/add', payload: { item } }),
    onAddFav: ({ item_id }) => dispatch({ type: 'member/addFav', payload: { item_id } }),
    onDelFav: ({ item_id }) => dispatch({ type: 'member/delFav', payload: { item_id } }),
    onUpdateCartCount: (count) => dispatch({ type: 'cart/updateCartNum', payload: count })
  })
)
export default class WgtGoods extends Component {
  static options = {
    addGlobalClass: true
  }

  static defaultProps = {
    info: null
  }

  constructor(props) {
    super(props)

    this.state = {
      curIdx: 0,
      is_fav: false,
      count: 0
    }
  }

  handleClickItem = (item) => {
    // const { info } = this.props

    /*if(info.data) {
      let onsale = true
      info.data.map(item => {
        if(id === item.item_id){
          if(!item.isOnsale){
            onsale = false
          }
        }
      })
      if(!onsale){
        return false
      }
    }*/
    // try {
    const { distributor_id, item_id } = item
    // const dtid = distributor_id ? distributor_id : getDistributorId()
    Taro.navigateTo({
      url: `/pages/item/espier-detail?id=${item_id}&dtid=${distributor_id || 0}`
    })
    // }
    // catch (error) {
    //   console.log(error)
    //   Taro.navigateTo({
    //     url: `/pages/iwp/item-detail?id=${id}`
    //   })
    // }
  }

  handleSwiperChange = (e) => {
    const { current } = e.detail

    this.setState({
      curIdx: current
    })
  }

  handleClickOperate = async (item_data, type, e) => {
    const { info } = this.props
    e.stopPropagation()

    if (!S.getAuthToken()) {
      Taro.showToast({
        icon: 'none',
        title: '请登录'
      })
      return
    }

    /*if(info.data) {
      let onsale = true
      info.data.map(item => {
        if(item_data.item_id === item.item_id){
          if(!item.isOnsale){
            onsale = false
          }
        }
      })
      if(!onsale){
        return false
      }
    }*/
    if (type === 'collect') {
      /*if(this.state.count === 0) {
        let is_fav = Boolean(this.props.favs[item_data.item_id])
        this.setState({
          count: 1,
          is_fav
        })
      }
      if(!this.state.is_fav) {
        await api.member.addFav(item_data.item_id)
        this.props.onAddFav(item_data)
        Taro.showToast({
          title: '已加入收藏',
          icon: 'none'
        })
      } else {
        await api.member.delFav(item_data.item_id)
        this.props.onDelFav(item_data)
        Taro.showToast({
          title: '已移出收藏',
          icon: 'none'
        })
      }
      this.setState({
        is_fav: !this.state.is_fav
      })*/
      if (!item_data.favStatus) {
        await api.member.addFav(item_data.item_id)

        Tracker.dispatch('GOODS_COLLECT', info)

        this.props.onAddFav(item_data)
        Taro.showToast({
          title: '已加入收藏',
          icon: 'none'
        })
      } else {
        await api.member.delFav(item_data.item_id)
        this.props.onDelFav(item_data)
        Taro.showToast({
          title: '已移出收藏',
          icon: 'none'
        })
      }
      this.props.onClick()
    }

    if (type === 'buy') {
      try {
        const isOpenStore = await entry.getStoreStatus()
        const { distributor_id, store_id } = Taro.getStorageSync('curStore')
        let id = Number(item_data.distributor_id) || 0
        // if (VERSION_STANDARD && !id) {
        //   id = isOpenStore ? store_id : distributor_id
        // }

        await api.cart.add({
          item_id: item_data.item_id,
          distributor_id: id,
          num: 1,
          shop_type: 'distributor'
        })
        Taro.showToast({
          title: '成功加入购物车',
          icon: 'success'
        })
        this.fetchCartcount()
      } catch (error) {
        console.log(error)
      }
    }
  }
  async fetchCartcount() {
    try {
      const { item_count } = await api.cart.count({ shop_type: 'distributor' })
      this.props.onUpdateCartCount(item_count)
    } catch (e) {
      console.error(e)
    }
  }

  render() {
    const { info } = this.props
    const { curIdx, is_fav } = this.state
    if (!info) {
      return null
    }

    const { config, base, data } = info
    const curContent = (data[curIdx] || {}).content

    return (
      <View className={`wgt ${base.padded ? 'wgt__padded' : null}`}>
        {base.title && (
          <View className='wgt__header'>
            <View className='wgt__title'>{base.title}</View>
            <View className='wgt__subtitle'>{base.subtitle}</View>
          </View>
        )}
        <View className='slider-wrap'>
          {data.map((item) => {
            return (
              <View
                className='goods-content'
                key={item.item_id}
                onClick={this.handleClickItem.bind(this, item)}
              >
                <View className='goods-content__info'>
                  <View className='goods-content__info_img'>
                    <SpImg
                      img-class='img-style'
                      src={item.img_url}
                      mode='aspectFill'
                      width='240'
                      lazyLoad
                    />
                  </View>
                  <View className='goods-content__info_text'>
                    <Text>{item.item_name}</Text>
                    <Text>{item.itemStatus ? '点击查看产品详情' : '该商品已下架'}</Text>
                  </View>
                </View>
                <View className='goods-content__operate'>
                  <View
                    className={`goods-content__operate_btn ${
                      item.itemStatus ? '' : 'disabled__operate'
                    }`}
                    onClick={this.handleClickOperate.bind(this, item, 'collect')}
                  >
                    {item.favStatus ? '移除心愿' : '加入心愿'}
                  </View>
                  <Text>|</Text>
                  <View
                    className={`goods-content__operate_btn ${
                      item.itemStatus ? '' : 'disabled__operate'
                    }`}
                    onClick={this.handleClickOperate.bind(this, item, 'buy')}
                  >
                    加入购买
                  </View>
                </View>
              </View>
            )
          })}
        </View>
      </View>
    )
  }
}
