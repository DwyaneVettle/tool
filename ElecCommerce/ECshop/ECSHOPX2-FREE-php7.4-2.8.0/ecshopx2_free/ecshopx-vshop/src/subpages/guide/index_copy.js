import React, { Component } from 'react'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { View, ScrollView } from '@tarojs/components'
import { connect } from 'react-redux'
import { Loading } from '@/components'
import api from '@/api'
import { styleNames, platformTemplateName } from '@/utils'
import { withPager, withBackToTop } from '@/hocs'
import S from '@/subpages/guide/lib/Spx.js'
import entry from '@/utils/entry'
import { WgtSearchHome } from './components/wgts'
import { BaHomeWgts, BaStoreList, BaStore, BaGoodsBuyPanel, BaTabBar, BaNavBar } from './components'

import '@/pages/home/index.scss'

@connect(
  ({ guide }) => ({
    storeInfo: guide.storeInfo
  }),
  (dispatch) => ({
    updateStoreInfo: (info) => dispatch({ type: 'guide/updateStoreInfo', payload: { info } })
  })
)
@withPager
@withBackToTop
export default class BaGuideHomeIndex extends Component {
  config = {
    navigationStyle: 'custom',
    onReachBottomDistance: 50
  }
  constructor(props) {
    super(props)

    this.state = {
      ...this.state,
      wgts: null,
      goodsFavWgt: null,
      authStatus: false,

      isShowAddTip: false,
      background: null,
      homepopupHostInfo: null,
      scrollPosition: null,
      scrollInputConfig: {},
      isinputfix: false,
      homepopuptimer: null,
      storeCurIndex: 0,
      showStore: false,
      ba_info: null,
      shopList: [],
      defaultStore: null,
      currentIndex: 0,
      guideInfo: null
    }
  }
  componentDidShow() {}
  async componentWillMount() {
    const options = getCurrentInstance().router.params
    const res = await entry.entryLaunch(options, false) // 不能开启定位，直接读取导购带过来的店铺信息
    console.log('[entry.entryLaunch]', res)
  }
  async componentDidMount() {
    await S.autoLogin(this)
    const { version } = getCurrentInstance().router.params
    console.log('[挂件包] this.state.wgts', this.state.wgts)
    //设置导购信息
    this.guideInit(version)
  }

  guideInit(version) {
    const guideInfo = S.get('GUIDE_INFO', true)
    console.log('导购 - guideInit - guideInfo ', guideInfo)
    this.setState({ guideInfo }, () => {
      console.log('导购 - guideInit - fetchInfo ', version)
      this.fetchInfo(version)
      this.getStoreList()
    })
  }

  //初始化首页模版
  async fetchInfo(version = 'v1.0.1') {
    console.log('初始化首页模版-fetchInfo-version', version)

    let params = {
      template_name: platformTemplateName,
      version: version,
      page_name: 'custom_salesperson',
      company_id: 1
    }
    const info = await api.guide.getHomeTmps(params)

    const bkg_item = info.config.find(
      (item) => item.name === 'background' && item.config.background
    )

    const sliderIndex = info.config.findIndex(
      (item) => item.name === 'slider' || item.name === 'slider-hotzone'
    )

    let tagnavIndex = -1
    info.config.forEach((wgt_item) => {
      if (wgt_item.name === 'tagNavigation') {
        tagnavIndex++
        wgt_item.tagnavIndex = tagnavIndex
      }
    })
    console.log('info.config', info.config)
    this.setState({
      wgts: info.config,
      background: (bkg_item && bkg_item.config.background) || null
      // scrollInputConfig
    })
  }

  //获取门店列表
  async getStoreList(params = {}) {
    const { list } = await api.guide.distributorlist({
      page: 1,
      pageSize: 10000,
      store_type: 'distributor'
    })
    const { guideInfo } = this.state
    const fd = list.find((item) => item.distributor_id == guideInfo.distributor_id)
    this.setState({
      shopList: list
    })
    console.log('[获取门店列表]-getStoreList', this.props.storeInfo)
    if (!this.props.storeInfo) this.props.updateStoreInfo(fd)
  }

  handleCloseCart = () => {
    setTimeout(() => {
      const { onCloseCart } = this.props
      onCloseCart(false)
    }, 0)
  }

  handlePageScroll = (top) => {
    const { scrollPageHeight, scrollTop } = this.state
    let offsetTop = scrollPageHeight + top //滑动距离+导航元素距离scrollview元素的距离=导航标签距离页面的距离

    if (offsetTop === scrollTop) {
      offsetTop = offsetTop - 1
    }
    this.setState({
      scrollTop: offsetTop
    })
  }

  //点击Store
  handleOpenStore = (val) => {
    this.setState({
      showStore: val
    })
  }

  handleStoreConfirm = (index) => {
    const { shopList } = this.state
    this.setState(
      {
        currentIndex: index,
        showStore: false
      },
      () => {
        this.props.updateStoreInfo(shopList[index])
      }
    )
  }

  render() {
    const { wgts, scrollTop, showStore, shopList, currentIndex, guideInfo } = this.state
    const isLoading = !wgts
    const { homesearchfocus, showBuyPanel, goodsSkuInfo, storeInfo } = this.props
    const n_ht = S.get('navbar_height')
    console.log('guideInfo0------->', guideInfo)
    return (
      <View style='padding-top:70rpx' className={!isLoading ? 'page-index' : ''}>
        <BaNavBar title='导购商城' fixed icon='in-icon in-icon-backhome' />
        <View>
          {guideInfo && (
            <BaStore
              onClick={this.handleOpenStore}
              guideInfo={guideInfo}
              defaultStore={storeInfo}
            />
          )}
        </View>
        {isLoading ? (
          <Loading></Loading>
        ) : (
          <ScrollView
            className={`wgts-wrap wgts-wrap__fixed `}
            scrollTop={scrollTop}
            onScroll={this.handleHomeScroll}
            scrollY
            style={styleNames({ top: n_ht + 'PX' })}
          >
            <View className='wgts-wrap__cont'>
              <BaHomeWgts wgts={wgts} source='bahome' onChangPageScroll={this.handlePageScroll} />
            </View>
          </ScrollView>
        )}

        {homesearchfocus && <WgtSearchHome isShow={homesearchfocus} />}

        <BaTabBar />
        {showBuyPanel && (
          <BaGoodsBuyPanel
            info={goodsSkuInfo}
            type='cart'
            isOpened={showBuyPanel}
            onClose={this.handleCloseCart}
            onAddCart={this.handleCloseCart}
          />
        )}

        {showStore && (
          <BaStoreList
            shopList={shopList}
            currentIndex={currentIndex}
            onStoreConfirm={this.handleStoreConfirm}
            onClose={this.handleOpenStore}
          />
        )}
      </View>
    )
  }
}
