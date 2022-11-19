import Big from 'big.js'
import { pickBy, calcTimer } from '@/utils'
import { AFTER_SALE_STATUS } from '@/consts'

export const COMMUNITY_ORDER_LIST = {
  orderId: 'order_id',
  orderStatus: 'order_status',
  orderStatusMsg: 'order_status_msg',
  items: 'items',
  remark: 'remark',
  receiver_name: 'receiver_name',
  receiver_mobile: 'receiver_mobile',
  receiver_state: 'receiver_state',
  receiver_city: 'receiver_city',
  receiver_district: 'receiver_district',
  receiver_address: 'receiver_address',
  receiver_type: 'receiver_type',
  memberInfo: 'member',
  communityInfo: 'community_info',
  createDate: 'create_date',
  totalNum: 'total_num',
  buildingNumber: 'building_number',
  houseNumber: 'house_number',
  payStatus: 'pay_status',
  isLogistics: 'is_logistics',
  canApplyCancel: 'can_apply_cancel',
  orderStatusDes: 'order_status_des',
  status: 'status',
  orderType: 'order_type',
  payType: 'pay_type',
  totalFee: 'total_fee',
  activityId: 'activity_id',
  canApplyAftersales: 'can_apply_aftersales',
  autoCancelSeconds: ({ auto_cancel_seconds }) => {
    if (auto_cancel_seconds > 0) {
      return calcTimer(auto_cancel_seconds)
    } else {
      return 0
    }
  },
  items: ({ items }) => {
    if (!items) {
      return []
    } else {
      return pickBy(items, {
        orderId: 'order_id',
        itemName: 'item_name',
        price: ({ price }) => (price / 100).toFixed(2),
        // total_fee: 'total_fee',
        itemSpecDesc: 'item_spec_desc',
        num: 'num',
        zitiName: 'ziti_name',
        pic: 'pic'
      })
    }
  }
}

export const COMMUNITY_ACTIVITY_ITEM = {
  activityIntro: ({ activity_intro }) => {
    return JSON.parse(activity_intro)
  },
  activityPics: ({ activity_pics }) => {
    return JSON.parse(activity_pics)
  },
  orders: 'orders',
  activityStatus: 'activity_status',
  showBuy: 'show_buy',
  buttons: 'buttons',
  activityName: 'activity_name',
  activityId: 'activity_id',
  chiefInfo: 'chief_info',
  chiefId: 'chief_id',
  isActivityAuthor: 'is_activity_author',
  startTime: 'start_time',
  endTime: 'end_time',
  distributorId: 'distributor_id',
  showChiefGoodsList: ({ show_chief_goodslist }) => {
    if(typeof show_chief_goodslist == 'undefined') {
      return 1
    } else {
      return show_chief_goodslist
    }
  },
  items: ({ items }) => {
    return pickBy(items, {
      itemId: 'itemId',
      pic: 'pics[0]',
      itemName: 'item_name',
      store: 'store',
      distributorId: 'distributor_id',
      price: ({ price }) => (price / 100).toFixed(2),
      num: 0,
      buyNum: 'buy_num',
      minDeliveryNum: 'min_delivery_num',
      nospec: 'nospec',
      specItems: ({ spec_items }) => {
        return pickBy(spec_items, {
          pic: 'pics[0]',
          itemId: 'item_id',
          itemName: 'item_name',
          itemSpecDesc: 'item_spec_desc',
          price: ({ price }) => (price / 100).toFixed(2),
          num: 0
        })
      },

    })
  },
  ziti: ({ ziti }) => {
    return pickBy(ziti, {
      area: ({ province, city, area }) => {
        return `${province} ${city} ${area}`
      },
      zitiName: 'ziti_name',
      province: 'province',
      city: 'city',
      country: 'area',
      address: 'address'
    })
  },
  showCondition: ({ condition_type }) => condition_type == 'money',
  progressValue: ({ condition_money, total_fee }) => {
    const totalFee = total_fee / 100
    return totalFee >= condition_money ? 100 : totalFee / condition_money * 100
  },
  diffCondition: ({ condition_money, total_fee }) => {
    const totalFee = total_fee / 100
    return totalFee >= condition_money ? 0 : new Big(condition_money).minus(totalFee).toNumber()
  },
  totalFee: 'total_fee',
  conditionMoney: 'condition_money',
  conditionType: "condition_type"
}

export const COMMUNITY_ACTIVITY_DETAIL_ITEM = {
  chiefInfo: 'chief_info',
  activityIntro: ({ activity_intro }) => {
    return JSON.parse(activity_intro)
  },
  activityPics: ({ activity_pics }) => {
    return JSON.parse(activity_pics)
  },
  activityName: 'activity_name',
  activityId: 'activity_id',
  items: ({ items }) => {
    return pickBy(items, {
      itemId: 'itemId',
      pic: 'pics[0]',
      itemName: 'item_name',
      store: 'store',
      price: ({ price }) => (price / 100).toFixed(2)
    })
  },
  ziti: ({ ziti }) => {
    return pickBy(ziti, {
      area: ({ province, city, area }) => {
        return `${province} ${city} ${area}`
      },
      zitiName: 'ziti_name',
      province: 'province',
      city: 'city',
      country: 'area',
      address: 'address'
    })
  }
}

export const COMMUNITY_ACTIVITY_LIST = {
  activityId: 'activity_id',
  chiefId: 'chief_id',
  activityName: 'activity_name',
  activityStatus: 'activity_status',
  activityStatusMsg: 'activity_status_msg',
  activityDeliveryStatusMsg: 'activity_delivery_status_msg',
  startTime: 'start_time',
  priceRange: 'price_range',
  deliveryStatus: 'delivery_status',
  saveTime: 'save_time',
  orderNum: 'order_num',
  userNum: 'user_num',
  totalFee: 'total_fee',
  canWriteoff: 'can_writeoff',
  items: ({ items }) => {
    if (!items) {
      return []
    } else {
      return pickBy(items, {
        itemId: 'item_id',
        itemName: 'item_name',
        itemPics: 'item_pics',
        pics: ({ pics }) => pics[0]
      })
    }
  }
}

export const COMMUNITY_ZITI = {
  id: 'ziti_id',
  area: ({ province, city, area }) => {
    return `${province} ${city} ${area}`
  },
  zitiName: 'ziti_name',
  province: 'province',
  city: 'city',
  country: 'area',
  address: 'address'
}

export const COMMUNITY_GOODS_ITEM = {
  itemId: 'item_id',
  pic: 'pics[0]',
  itemName: 'item_name',
  store: 'store',
  price: ({ price }) => (price / 100).toFixed(2),
  distributorId: 'distributor_id',
  itemSpecDesc: 'item_spec_desc',
  nospec: 'nospec',
  minDeliveryNum: 'min_delivery_num'
}

export const COMMUNITY_AFTER_SALE_ITEM = {
  id: 'aftersales_bn',
  status_desc: ({ aftersales_status }) => AFTER_SALE_STATUS[aftersales_status],
  totalItems: 'num',
  payment: ({ refund_fee }) => (refund_fee / 100).toFixed(2),
  pay_type: 'pay_type',
  point: 'point',
  distributor_info: 'distributor_info',
  order: ({ detail }) =>
    pickBy(detail, {
      order_id: 'order_id',
      item_id: 'item_id',
      pic_path: 'item_pic',
      title: 'item_name',
      price: ({ refund_fee }) => (+refund_fee / 100).toFixed(2),
      point: 'item_point',
      num: 'num'
    })
}

export const COMMUNITY_CHECKOUT_RES = {
  items: ({ items }) => {
    return pickBy(items, {
      pic: 'pic',
      itemName: 'item_name',
      price: ({ price }) => (price / 100).toFixed(2),
      num: 'num',
      itemSpecDesc: 'item_spec_desc'
    })
  },
  totalFee: ({ total_fee }) => (total_fee / 100).toFixed(2),
  totalItemNum: 'totalItemNum'
}

export const GOOD_LIST = {
  pic: ({ pics }) => pics[0],
  name: 'itemName',
  no: 'itemBn',
  store: 'store',
  price: 'price',
  approve_status: 'approve_status',
  goods_id: 'goods_id',
  audit_status: 'audit_status',
  item_id: 'item_id',
  itemBn: 'itemBn',
  itemBoxNum: 'item_box_num',
  itemPieceNum: 'item_piece_num',
  unitScale: 'unit_scale'
}
