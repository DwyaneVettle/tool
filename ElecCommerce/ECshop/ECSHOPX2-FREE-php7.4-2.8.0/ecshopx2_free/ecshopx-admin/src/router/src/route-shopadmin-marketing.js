// 商品路由
const name = '营销'
import Layout from '@/view/layout' // 主框架

export default {
  path: '/shopadmin/marketing',
  component: Layout,
  children: [
    {
      path: 'coupon/membermarketing',
      name: `优惠券管理`,
      component: () => import('@/view/member/cardticket/coupon'),
      children: [
        {
          path: 'editor',
          component: () => import('@/view/member/cardticket/edit')
        },
        {
          path: 'detail',
          component: () => import('@/view/member/cardticket/detail')
        }
      ]
    },
    {
      path: 'coupon/coupongive',
      name: `优惠券发放日志`,
      component: () => import('@/view/member/coupon/giveLog'),
      children: [
        {
          path: 'detail/:id',
          component: () => import('@/view/member/coupon/giveErrorLog')
        }
      ]
    },
    {
      path: 'marketingsfullminus',
      name: `满减管理`,
      component: () => import('@/view/member/promotions/fullminus/list'),
      children: [
        {
          path: 'editor/:marketing_id?',
          component: () => import('@/view/member/promotions/fullminus/add')
        }
      ]
    },
    {
      path: 'marketingsfulldiscount',
      name: `满折管理`,
      component: () => import('@/view/member/promotions/fulldiscount/list'),
      children: [
        {
          path: 'editor/:marketing_id?',
          component: () => import('@/view/member/promotions/fulldiscount/add')
        }
      ]
    },
    {
      path: 'marketingsfullgift',
      name: `满赠管理`,
      component: () => import('@/view/member/promotions/fullgift/list'),
      children: [
        {
          path: 'editor/:marketing_id?',
          component: () => import('@/view/member/promotions/fullgift/add')
        }
      ]
    },
    {
      path: 'marketing/limitedtimesale',
      name: `限时特惠`,
      component: () => import('@/view/member/promotions/limitedtimesale/list'),
      children: [
        {
          path: 'editor/:seckill_id?',
          component: () => import('@/view/member/promotions/limitedtimesale/add')
        }
      ]
    },
    {
      path: 'community/chief',
      name: `团长管理`,
      component: () => import('@/view/mall/community/chief'),
      children: [
        {
          path: 'detail/:apply_id',
          component: () => import('@/view/mall/community/chiefDetail')
        }
      ]
    },
    {
      path: 'community/activity',
      name: `活动管理`,
      component: () => import('@/view/mall/community/activityList')
    },
    {
      path: 'community/communitygoods',
      name: `活动商品`,
      component: () => import('@/view/mall/community/communitygoods')
    },
    {
      path: 'community/order',
      name: `订单管理`,
      component: () => import('@/view/mall/trade/normalorders/communityList'),
      children: [
        {
          path: 'detail/:itemId?',
          component: () => import('@/view/mall/trade/order/detail')
        },
        {
          path: 'process/:itemId?',
          component: () => import('@/view/mall/trade/order/process')
        }
      ]
    },
    {
      path: 'community/setting',
      name: `活动配置`,
      component: () => import('@/view/mall/community/setting')
    }
  ]
}
