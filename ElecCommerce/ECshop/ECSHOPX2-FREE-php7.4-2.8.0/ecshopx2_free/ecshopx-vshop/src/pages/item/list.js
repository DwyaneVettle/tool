import React, { useRef, useEffect, useState } from 'react'
import { View, Text, ScrollView } from '@tarojs/components'
import Taro, { getCurrentInstance } from '@tarojs/taro'
import { connect } from 'react-redux'
import { useImmer } from 'use-immer'
import { withPager, withBackToTop } from '@/hocs'
import { AtDrawer, AtTabs } from 'taro-ui'
import {
  BackToTop,
  Loading,
  TagsBar,
  SpFilterBar,
  SearchBar,
  GoodsItem,
  SpTagBar,
  SpGoodsItem,
  SpSearchBar,
  SpNote,
  SpNavBar,
  SpLoadMore,
  TabBar,
  SpPage,
  SpScrollView,
  SpDrawer,
  SpSelect
} from '@/components'
import doc from '@/doc'
import api from '@/api'
import { pickBy, classNames, isWeixin, getDistributorId } from '@/utils'
import { Tracker } from '@/service'

import './list.scss'

const initialState = {
  leftList: [],
  rightList: [],
  brandList: [],
  brandSelect: [],
  filterList: [
    { title: '综合' },
    { title: '销量' },
    { title: '价格', icon: 'icon-shengxu-01' },
    { title: '价格', icon: 'icon-jiangxu-01' }
  ],
  curFilterIdx: 0,
  tagList: [],
  curTagIdx: 0,
  keywords: '',
  show: false
}

function ItemList(props) {
  const $instance = getCurrentInstance()
  const [state, setState] = useImmer(initialState)
  const {
    keywords,
    leftList,
    rightList,
    brandList,
    brandSelect,
    curFilterIdx,
    filterList,
    tagList,
    curTagIdx,
    show
  } = state
  const [isShowSearch, setIsShowSearch] = useState(false)
  const goodsRef = useRef()

  useEffect(() => {}, [])

  const fetch = async ({ pageIndex, pageSize }) => {
    const { cat_id, main_cat_id } = $instance.router.params
    let params = {
      page: pageIndex,
      pageSize,
      brand_id: brandSelect,
      keywords: keywords,
      approve_status: 'onsale,only_show',
      item_type: 'normal',
      is_point: 'false'
    }

    if (curFilterIdx == 1) {
      // 销量
      params['goodsSort'] = 1
    } else if (curFilterIdx == 2) {
      // 价格升序
      params['goodsSort'] = 3
    } else if (curFilterIdx == 3) {
      // 价格降序
      params['goodsSort'] = 2
    }

    if (curTagIdx) {
      params['tag_id'] = curTagIdx
    }

    if (cat_id) {
      params['category'] = cat_id
    }

    params.distributor_id = getDistributorId()

    const {
      list,
      total_count,
      item_params_list = [],
      select_tags_list = [],
      brand_list
    } = await api.item.search(params)
    const n_list = pickBy(list, doc.goods.ITEM_LIST_GOODS)
    const resLeftList = n_list.filter((item, index) => {
      if (index % 2 == 0) {
        return item
      }
    })
    const resRightList = n_list.filter((item, index) => {
      if (index % 2 == 1) {
        return item
      }
    })

    setState((v) => {
      v.leftList = [...v.leftList, ...resLeftList]
      v.rightList = [...v.rightList, ...resRightList]
      v.brandList = pickBy(brand_list?.list, doc.goods.WGT_GOODS_BRAND)
      if (select_tags_list.length > 0) {
        v.tagList = [
          {
            tag_name: '全部',
            tag_id: 0
          }
        ].concat(select_tags_list)
      }
    })

    return { total: total_count }
  }

  const handleOnFocus = () => {
    setIsShowSearch(true)
  }

  const handleOnChange = (val) => {
    setState((v) => {
      v.keywords = val
    })
  }

  const handleOnClear = async () => {
    await setState((draft) => {
      draft.leftList = []
      draft.rightList = []
      draft.keywords = ''
    })
    setIsShowSearch(false)
    goodsRef.current.reset()
  }

  const handleSearchOff = async () => {
    setIsShowSearch(false)
    await setState((v) => {
      v.keywords = ''
    })
  }

  const handleConfirm = async (val) => {
    Tracker.dispatch('SEARCH_RESULT', {
      keywords: val
    })
    setIsShowSearch(false)
    await setState((draft) => {
      draft.leftList = []
      draft.rightList = []
      draft.keywords = val
    })
    goodsRef.current.reset()
  }

  const onChangeTag = async (index, item) => {
    await setState((draft) => {
      draft.leftList = []
      draft.rightList = []
      draft.curTagIdx = item.tag_id
    })
    goodsRef.current.reset()
  }

  const handleFilterChange = async (e) => {
    await setState((draft) => {
      draft.leftList = []
      draft.rightList = []
      draft.curFilterIdx = e.current || 0
    })
    goodsRef.current.reset()
  }

  const onChangeBrand = (val) => {
    setState((draft) => {
      draft.brandSelect = val
    })
  }

  const onConfirmBrand = async () => {
    await setState((draft) => {
      draft.leftList = []
      draft.rightList = []
      draft.show = false
    })
    goodsRef.current.reset()
  }

  const onResetBrand = async () => {
    await setState((draft) => {
      draft.brandSelect = []
      draft.leftList = []
      draft.rightList = []
      draft.show = false
    })
    goodsRef.current.reset()
  }

  const handleClickStore = (item) => {
    const url = `/pages/store/index?id=${item.distributor_info.distributor_id}`
    Taro.navigateTo({
      url
    })
  }

  return (
    <SpPage
      scrollToTopBtn
      className={classNames('page-item-list', {
        'has-tagbar': tagList.length > 0
      })}
    >
      <View className='item-list-head'>
        <View className='search-wrap'>
          <SpSearchBar
            keyword={keywords}
            placeholder='搜索'
            onFocus={handleOnFocus}
            onChange={handleOnChange}
            onClear={handleOnClear}
            onCancel={handleSearchOff}
            onConfirm={handleConfirm}
          />
        </View>
        {tagList.length > 0 && (
          <SpTagBar className='tag-list' list={tagList} value={curTagIdx} onChange={onChangeTag}>
            {/* <View
            className="filter-btn"
            onClick={() => {
              setState(v => {
                v.show = true;
              });
            }}
          >
            筛选<Text className="iconfont icon-filter"></Text>
          </View> */}
          </SpTagBar>
        )}

        <SpFilterBar
          custom
          current={curFilterIdx}
          list={filterList}
          onChange={handleFilterChange}
        />
      </View>
      <SpScrollView className='item-list-scroll' ref={goodsRef} fetch={fetch}>
        <View className='goods-list'>
          <View className='left-container'>
            {leftList.map((item, index) => (
              <View className='goods-item-wrap' key={`goods-item-l__${index}`}>
                <SpGoodsItem showFav onStoreClick={handleClickStore} info={item} />
              </View>
            ))}
          </View>
          <View className='right-container'>
            {rightList.map((item, index) => (
              <View className='goods-item-wrap' key={`goods-item-r__${index}`}>
                <SpGoodsItem showFav onStoreClick={handleClickStore} info={item} />
              </View>
            ))}
          </View>
        </View>
      </SpScrollView>

      {/* <SpDrawer
        show={show}
        onClose={() => {
          setState(v => {
            v.show = false;
          });
        }}
        onConfirm={onConfirmBrand}
        onReset={onResetBrand}
      >
        <View className="brand-title">品牌</View>
        <SpSelect
          multiple
          info={brandList}
          value={brandSelect}
          onChange={onChangeBrand}
        />
      </SpDrawer> */}
    </SpPage>
  )
}

export default ItemList
