//plusValue 代表正序 minusValue代表倒序
export const TIME_SORT = 0
export const SALE_PLUS_SORT = 4
export const SALE_MINUS_SORT = 3
export const DISTANCE_PLUS_SORT = 1
export const DISTANCE_MINUS_SORT = 2

export const FILTER_DATA = [
  { value: TIME_SORT, tag_name: '综合排序' },
  { tag_name: '销量', plusValue: SALE_PLUS_SORT, minusValue: SALE_MINUS_SORT },
  { tag_name: '距离', plusValue: DISTANCE_PLUS_SORT, minusValue: DISTANCE_MINUS_SORT }
]

export const DEFAULT_SORT_VALUE = DISTANCE_PLUS_SORT

export const FILTER_DRAWER_DATA = [
  {
    value: 'tag',
    label: '商家标签',
    children: []
  },
  {
    value: 'logistics',
    label: '商家服务',
    children: [
      {
        value: 'ziti',
        label: '自提'
      },
      {
        value: 'delivery',
        label: '快递'
      },
      {
        value: 'dada',
        label: '同城配'
      }
    ]
  }
]

//填充标签
export function fillFilterTag (tagList) {
  FILTER_DRAWER_DATA[0].children = tagList.map((item) => ({
    value: item.tag_id,
    label: item.tag_name
  }))
}

export const SEARCH_DATA = ['可乐', '香氛蜡烛', '收纳', '坚果零食', '酸奶', '外框左右空24px']

export const BUSINESS_LIST_SERVICES = [
  {
    id: 'ziti',
    name: '自提'
  },
  {
    id: 'delivery',
    name: '快递'
  },
  {
    id: 'dada',
    name: '同城配'
  }
]
