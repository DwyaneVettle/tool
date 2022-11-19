import React, { PureComponent } from 'react'
import { View, ScrollView } from '@tarojs/components'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { getThemeStyle } from '@/utils'
import FilterBlock from './comps/filterblock'
import { withPager, withBackToTop } from '@/hocs'
import { SpLoading, SpNote, SpOrderItem, SpToast, SpTips } from '@/components'
import { SelectInput, Tabbar, PageActionButtons } from '@/components/sp-page-components'
import { calculateTimestamp } from '@/utils/time'
import { classNames } from '@/utils'
import api from '@/api'
import { connect } from 'react-redux'
import './index.scss'

@connect(({ planSelection }) => ({
  planSelection: planSelection.activeShop
}))
@withPager
@withBackToTop
export default class List extends PureComponent {
  constructor(props) {
    super(props)
    this.state = {
      orderStatus: '-1',
      noteVisible: false,
      actionVisible: false,
      actionType: '',
      orderList: [],
      loading: false,
      //搜索框选择的参数  类型为对象
      inputParams: null,
      //搜索框输入的值
      inputValue: '',
      //状态筛选值
      mainStatus: null,
      //筛选参数
      filterParams: {},
      //点击picker隐藏
      pickerVisible: false,
      //默认订单由近及远
      orderBy: 'desc',
      //页面类型
      pageType: 'afterSalesList',
      //按钮操作弹框显示隐藏
      buttonsActionVisible: false
    }
  }

  //获取订单筛选参数
  getOrderParams = () => {
    const { inputParams, inputValue, mainStatus, filterParams, orderBy } = this.state

    let params = {}

    if (inputParams && inputValue) {
      params[inputParams.value] = inputValue
    }

    if (mainStatus && mainStatus.value != 0) {
      params['aftersales_status'] = Number(mainStatus.value) - 1
    }

    if (orderBy) {
      params['order_by'] = orderBy
    }

    if (filterParams.aftersalesType) {
      if (filterParams.aftersalesType !== 'all') {
        params['aftersales_type'] = filterParams.aftersalesType
      }
    }

    if (filterParams.receiptType) {
      params['receipt_type'] = filterParams.receiptType
    }

    if (filterParams.createTime) {
      let timeArr = calculateTimestamp(filterParams.createTime)
      if (timeArr.length) {
        params['time_start_begin'] = timeArr[0]
        params['time_start_end'] = timeArr[1]
      }
    }

    return params
  }

  searchFilter = async ({ isCMD, isResetList, isFirst }) => {
    let query = {}

    if (isFirst) {
      return
    }

    if (isCMD) {
      //如果是初始化
      query.orderList = []
      query.inputValue = ''
    }

    if (isResetList) {
      //筛选时需要置空
      query.orderList = []
    }

    this.resetPage(() => {
      this.setState(
        {
          ...this.state,
          ...query
        },
        () => {
          this.nextPage()
        }
      )
    })
  }

  async componentDidShow() {
    const {
      router: { params }
    } = getCurrentInstance()
    await this.searchFilter({ isCMD: true, isFirst: true })
  }

  handleTabClick = (activeIndex) => {
    this.setState({
      orderStatus: activeIndex
    })
  }

  //获取订单列表
  getOrdersList = async (params) => {
    let { distributor_id } = this.props.planSelection
    const { list, total_count: total } = await api.afterSales.list({
      page: params.page_no,
      pageSize: params.page_size,
      distributor_id,
      ...this.getOrderParams()
    })
    this.setState({
      orderList: [...this.state.orderList, ...list]
    })

    return {
      total
    }
  }

  //请求
  fetch = async (params) => {
    this.setState({
      loading: true
    })

    const { total } = await this.getOrdersList(params)

    this.setState({
      loading: false
    })

    return { total }
  }

  //搜索参数变化回调
  handleParamChange = (inputParams) => {
    this.setState({
      inputParams: { ...inputParams }
    })
  }

  //值变化回调
  handleValueChange = (inputValue) => {
    this.setState({
      inputValue
    })
  }

  //状态变化的回调
  handleStatusChange = (status) => {
    this.setState({
      mainStatus: status
    })
    this.searchFilter({ isResetList: true })
  }

  //提交筛选状态
  handleSubmitParams = (params) => {
    this.setState({
      filterParams: params
    })
    this.searchFilter({ isResetList: true })
  }

  //切换时间筛选
  orderClick = () => {
    const { orderBy } = this.state
    if (orderBy === 'desc') {
      this.setState(
        {
          orderBy: 'asc'
        },
        () => {
          this.searchFilter({ isResetList: true })
        }
      )
    } else {
      this.setState(
        {
          orderBy: 'desc'
        },
        () => {
          this.searchFilter({ isResetList: true })
        }
      )
    }
  }

  //点击商品列表
  handleClickGoodItem = (goodInfo) => {
    Taro.navigateTo({ url: `/pages/afterSales/detail?aftersalesNo=${goodInfo.aftersales_bn}` })
  }

  //点击操作按钮
  handleClickActionButtons = (e) => {
    this.setState({
      buttonsActionVisible: true
    })
  }

  //关闭操作弹框
  handleCloseActionButtons = () => {
    this.setState({
      buttonsActionVisible: false
    })
  }

  handleRefresh = () => {
    this.searchFilter({ isResetList: true })
  }

  render() {
    const {
      orderList,
      page,
      loading,
      inputParams,
      inputValue,
      mainStatus,
      orderBy,
      pageType,
      buttonsActionVisible
    } = this.state

    return (
      <View className='page-order-list' style={getThemeStyle()}>
        <View className='page-order-list-input'>
          <SelectInput
            inputParam={inputParams}
            inputValue={inputValue}
            pageType={pageType}
            paramChange={this.handleParamChange}
            valueChange={this.handleValueChange}
            onInputConfirm={this.searchFilter}
          />
        </View>

        <Tabbar
          pageType={pageType}
          mainStatus={mainStatus}
          statusChange={this.handleStatusChange}
        />

        <FilterBlock
          pageType={pageType}
          orderBy={orderBy}
          onSubmitParams={this.handleSubmitParams}
          onOrderClick={this.orderClick}
        />

        <ScrollView
          scrollY
          className={classNames('page-order-list-orderList', {
            ['show-buttonsaction']: buttonsActionVisible
          })}
          scrollWithAnimation
          onScrollToLower={this.nextPage}
        >
          {orderList.map((orderItem, index) => {
            return (
              <SpOrderItem
                key={`${orderItem.order_id}_${index}`}
                pageType={pageType}
                info={orderItem}
                onGoodItemClick={this.handleClickGoodItem}
                renderFooter={
                  <PageActionButtons
                    buttons={orderItem?.app_info?.buttons}
                    pageType={pageType}
                    onClick={this.handleClickActionButtons}
                    onClose={this.handleCloseActionButtons}
                    orderInfo={orderItem?.app_info?.order_info}
                    afterSalesInfo={orderItem}
                    maxOrderInfo={orderItem}
                    onRefresh={this.handleRefresh}
                    mainStatus={mainStatus}
                  />
                }
              />
            )
          })}

          {loading && <SpLoading>正在加载...</SpLoading>}

          {!page.isLoading && !page.hasNext && !orderList.length && (
            <SpNote img='no_order.png'>暂时还没有您的售后信息</SpNote>
          )}

          {!page.hasNext && !!orderList.length && <SpTips msg={'没有更多了哦~'}></SpTips>}
        </ScrollView>

        <SpToast />
      </View>
    )
  }
}
