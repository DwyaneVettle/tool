// 商品路由
const name = '商品'
import Layout from '@/view/layout' // 主框架

export default {
  path: '/shopadmin/entity',
  component: Layout,
  children: [
    {
      path: 'goodsphysical',
      name: `商品管理`,
      component: () => import('@/view/mall/goods/physical/list'),
      children: [
        {
          path: 'editor/:itemId?',
          component: () => import('@/view/mall/goods/physical/add')
        },
        {
          path: 'physicalupload',
          name: `商品导入`,
          component: () => import('@/view/mall/goods/physical/normalGoodsUpload')
        },
        {
          path: 'physicalstoreupload',
          name: `库存导入`,
          component: () => import('@/view/mall/goods/physical/normalGoodsStoreUpload')
        }
      ]
    },
    {
      path: 'rate',
      name: `商品评价`,
      component: () => import('@/view/mall/trade/rate/list')
    },
    {
      path: 'goodsbrand',
      name: `商品品牌`,
      component: () => import('@/view/mall/goods/attributes/brand')
    },
    {
      path: 'goodscategory',
      name: `店铺分类`,
      component: () => import('@/view/mall/goods/category')
    },
    {
      path: 'itemtags',
      name: `商品标签`,
      component: () => import('@/view/mall/goods/tags')
    },
    // {
    //   path: 'brandmaterial',
    //   name: `素材管理`,
    //   component: () => import('@/view/brand/wechat/artical/new_artical')
    // },
    {
      path: 'brandmaterial',
      name: `素材管理`,
      component: () => import('@/view/brand/wechat/aterialmanagement'),
      children: [
        {
          path: 'editor/:id?',
          component: () => import('@/view/brand/wechat/artical/new_artical')
        }
      ]
    },
    {
      path: 'physicalupload',
      name: `商品导入`,
      component: () => import('@/view/mall/goods/physical/normalGoodsUpload')
    },
    {
      path: 'popularizegoods',
      name: `商品分销池`,
      component: () => import('@/view/popularize/goods')
    }
  ]
}
