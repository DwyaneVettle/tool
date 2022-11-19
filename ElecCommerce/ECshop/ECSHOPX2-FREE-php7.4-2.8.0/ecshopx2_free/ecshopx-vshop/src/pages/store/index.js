import { Component } from 'react'
import { connect } from 'react-redux'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { View, Text, ScrollView } from '@tarojs/components'
import {
  SpToast,
  Loading,
  BackToTop,
  SpRecommend,
  SpCellCoupon,
  SpPage,
  SpFloatMenuItem
} from '@/components'
import { AtTabBar } from 'taro-ui'
import req from '@/api/req'
import api from '@/api'
import {
  pickBy,
  normalizeQuerys,
  getCurrentRoute,
  classNames,
  merchantIsvaild,
  showToast
} from '@/utils'
import { platformTemplateName } from '@/utils/platform'
import { withPager, withBackToTop } from '@/hocs'
import qs from 'qs'
import S from '@/spx'
import {
  WgtSlider,
  WgtImgHotZone,
  WgtMarquees,
  WgtNavigation,
  WgtCoupon,
  WgtGoodsScroll,
  WgtGoodsGrid,
  WgtShowcase,
  WgtSearchHome,
  WgtFilm,
  WgtNearbyShop,
  WgtStore,
  WgtGoodsGridTab,
  WgtHeadline,
  WgtFloorImg,
  WgtHotTopic
} from '../home/wgts'
import CompHeader from './comps/comp-header'

import './index.scss'

@connect((store) => ({
  store,
  openRecommend: store.sys.openRecommend
}))
@withPager
@withBackToTop
export default class StoreIndex extends Component {
  constructor(props) {
    super(props)

    this.state = {
      wgts: null,
      authStatus: false,
      isShowAddTip: false,
      localCurrent: 0,
      storeInfo: null,
      tabList: [
        {
          title: '店铺首页',
          iconPrefixClass: 'iconfont icon',
          iconType: 'home',
          url: '/pages/store/index'
          //iconfont icon-home"
        },
        {
          title: '商品列表',
          iconType: 'list',
          iconPrefixClass: 'iconfont icon',
          url: '/others/pages/store/list'
        },
        {
          title: '商品分类',
          iconType: 'category',
          iconPrefixClass: 'iconfont icon',
          url: '/others/pages/store/category'
        }
      ],
      couponList: [],
      fixedSearch: false,
      likeList: [],
      storeIsVaild: false,
      fav: undefined
    }
    this.$instance = getCurrentInstance()
  }

  async componentDidMount() {
    const options = await normalizeQuerys(this.$instance.router.params)
    this.setState(
      {
        dtid: options.id || options.dtid
      },
      () => {
        this.fetchInfo()
        this.fetchCouponList()
        this.fetchIsValid()
      }
    )
  }

  storeFav = async (id) => {
    const { is_fav } = await api.member.storeIsFav(id)

    this.setState({
      fav: is_fav
    })
  }

  componentDidShow = () => {
    Taro.getStorage({ key: 'addTipIsShow' })
      .then(() => {})
      .catch((error) => {
        console.log(error)
        this.setState({
          isShowAddTip: true
        })
      })
    const id = this.state.dtid
    if (id) {
      this.storeFav(id)
    }
  }

  onShareAppMessage(res) {
    if (res.from === 'button') {
      console.log(res.target)
    }
    return {
      title: this.state.storeInfo ? this.state.storeInfo.name : '店铺商品',
      path: `/pages/store/index?id=${this.state.dtid}`
    }
  }

  async fetchIsValid() {
    let isVaild = await merchantIsvaild({ distributor_id: this.state.dtid }) // 判断当前店铺关联商户是否被禁用 isVaild：true有效
    // console.log('isVaild=========',isVaild);
    this.setState({
      storeIsVaild: !isVaild
    })
  }

  async fetchCouponList() {
    const params = {
      page_no: 1,
      page_size: 5,
      end_date: 1,
      distributor_id: this.state.dtid
    }
    const { list } = await api.member.homeCouponList(params)
    this.setState({
      couponList: list
    })
  }

  async fetchCouponList() {
    const params = {
      page_no: 1,
      page_size: 5,
      end_date: 1,
      distributor_id: this.state.dtid
    }
    const { list } = await api.member.homeCouponList(params)
    this.setState({
      couponList: list
    })
  }

  async fetchInfo() {
    const distributorId = this.state.dtid
    let id = ''
    let storeInfo = null
    if (distributorId) {
      id = distributorId
    } else {
      id = await Taro.getStorageSync('curStore').distributor_id
    }
    const { name, logo, scoreList, distributor_id, marketingActivityList } = await api.shop.getShop(
      {
        distributor_id: id,
        show_score: 1,
        show_marketing_activity: 1
      }
    )
    storeInfo = {
      name,
      brand: logo,
      scoreList,
      distributor_id,
      marketingActivityList
    }
    const pathparams = qs.stringify({
      template_name: platformTemplateName,
      weapp_pages: 'index',
      distributor_id: id
    })

    const url = `pagestemplate/shopDetail?${pathparams}`
    try {
      const info = await req.get(url)
      if (!S.getAuthToken()) {
        this.setState({
          authStatus: true
        })
      }

      // if (!info.length) {
      //   return showToast('当前暂未配置模板')
      // }
      //是否有search
      let search = info.config.find((item) => item.name === 'search')

      let fixedSearch = !!search.config.fixTop

      this.setState(
        {
          wgts: info.config,
          storeInfo: storeInfo,
          fixedSearch
        },
        () => {
          this.resetPage(() => {
            this.setState(
              {
                likeList: []
              },
              () => {
                this.nextPage()
              }
            )
          })

          // this.nextPage()
          // if (info.config) {
          //   info.config.map((item) => {
          //     if (item.name === 'setting' && item.config.faverite) {

          //     }
          //   })
          // }
        }
      )
    } catch (e) {
      // console.error(e)
      setTimeout(() => {
        Taro.navigateBack()
      }, 1500)
    }
  }

  // 获取猜你喜欢
  fetch = async (params) => {
    const { page_no: page, page_size: pageSize } = params
    const query = {
      page,
      pageSize
    }
    let total = 0
    if (this.props.openRecommend == 1) {
      const { list, total_count } = await api.cart.likeList(query)
      total = total_count
      this.setState({
        likeList: [...this.state.likeList, ...list]
      })
    }

    return {
      total
    }
  }

  handleClickLicense = () => {
    Taro.navigateTo({
      url: '/pages/home/license'
    })
  }

  handleClickCloseAddTip = () => {
    Taro.setStorage({ key: 'addTipIsShow', data: { isShowAddTip: false } })
    this.setState({
      isShowAddTip: false
    })
  }

  handleClick = async (current) => {
    const cur = this.state.localCurrent
    if (cur !== current) {
      const curTab = this.state.tabList[current]
      const { url } = curTab
      // const options = await normalizeQuerys(this.$router.params);
      const id = this.state.dtid
      const param = current === 1 ? `?dis_id=${id}` : `?id=${id}`
      const fullPath = getCurrentRoute(this.$router).fullPath.split('?')[0]
      if (url && fullPath !== url) {
        Taro.redirectTo({ url: `${url}${param}` })
      }
    }
  }

  handleBrandInfo = () => {
    const {
      storeInfo: { distributor_id }
    } = this.state
    Taro.navigateTo({
      url: `/pages/store/brand-info?distributor_id=${distributor_id}`
    })
  }

  render() {
    const {
      wgts,
      storeInfo,
      showBackToTop,
      scrollTop,
      tabList,
      localCurrent,
      couponList,
      fixedSearch,
      likeList,
      storeIsVaild,
      fav
    } = this.state
    const user = Taro.getStorageSync('userinfo')
    if (!wgts || !this.props.store) {
      return <Loading />
    }

    console.log('===likeList==>', likeList)
    const id = this.state.dtid
    return (
      <SpPage
        className={classNames('page-store-index', {
          fixedSearch,
          'page-store-height': storeIsVaild
        })}
        isDefault={storeIsVaild}
        defaultMsg='该店铺已注销，在别的店铺看看吧'
        renderFloat={
          <View>
            <SpFloatMenuItem
              onClick={() => {
                Taro.navigateTo({ url: '/subpages/member/index' })
              }}
            >
              <Text className='iconfont icon-huiyuanzhongxin'></Text>
            </SpFloatMenuItem>
            <SpFloatMenuItem
              onClick={() => {
                Taro.navigateTo({ url: '/pages/cart/espier-index' })
              }}
            >
              <Text className='iconfont icon-gouwuche'></Text>
            </SpFloatMenuItem>
          </View>
        }
      >
        <ScrollView
          className='wgts-wrap wgts-wrap__fixed__page'
          scrollTop={scrollTop}
          onScroll={this.handleScroll}
          onScrollToLower={this.nextPage}
          scrollY
        >
          <View className='wgts-wrap__cont'>
            <CompHeader
              inStore
              info={storeInfo}
              couponList={couponList}
              brandInfo={this.handleBrandInfo}
              fav={fav}
            />
            {wgts.map((item, idx) => {
              return (
                <View className='wgt-wrap' key={`${item.name}${idx}`}>
                  {item.name === 'search' && <WgtSearchHome info={item} dis_id={id} />}
                  {item.name === 'nearbyShop' && <WgtNearbyShop info={item} />}
                  {item.name === 'slider' && <WgtSlider info={item} />}
                  {item.name === 'film' && <WgtFilm info={item} />}
                  {item.name === 'marquees' && <WgtMarquees info={item} />}
                  {item.name === 'navigation' && <WgtNavigation info={item} />}
                  {item.name === 'coupon' && <WgtCoupon info={item} dis_id={id} />}
                  {item.name === 'imgHotzone' && <WgtImgHotZone info={item} />}
                  {item.name === 'goodsScroll' && <WgtGoodsScroll info={item} dis_id={id} />}
                  {item.name === 'goodsGrid' && (
                    <WgtGoodsGrid info={item} dis_id={id} index={idx} type='good-grid' />
                  )}
                  {item.name === 'goodsGridTab' && (
                    <WgtGoodsGridTab info={item} index={idx} type='good-grid-tab' />
                  )}
                  {item.name === 'showcase' && <WgtShowcase info={item} />}
                  {item.name === 'store' && <WgtStore info={item} />}
                  {item.name === 'headline' && <WgtHeadline info={item} />}
                </View>
              )
            })}

            <SpRecommend className='recommend-block' info={likeList} />
          </View>
        </ScrollView>

        {/* <BackToTop show={showBackToTop} onClick={this.scrollBackToTop} /> */}

        {/* <SpToast /> */}
        <AtTabBar fixed tabList={tabList} onClick={this.handleClick} current={localCurrent} />
      </SpPage>
    )
  }
}
