// 会员路由
const name = '设置'
import Layout from '@/view/layout' // 主框架

export default {
  path: '/setting',
  component: Layout,
  children: [
    {
      path: 'staff/staffleader',
      name: `账号管理-门店管理员`,
      component: () => import('@/view/base/shopstaff/salesperson/leaderlist')
    },
    {
      path: 'staff/Merchantstaff',
      name: `账号管理-店铺管理员`,
      component: () => import('@/view/base/account/storestaff')
    },
    {
      path: 'staff/storemanager',
      name: `账号管理-商户账号管理`,
      component: () => import('@/view/mall/marketing/merchantAccount')
    },
    {
      path: 'staff/Merchantstaffroles',
      name: `账号管理-店铺员工角色`,
      component: () => import('@/view/base/account/storeroles')
    },
    {
      path: 'staff/Platformstaff',
      name: `账号管理-平台管理员`,
      component: () => import('@/view/base/account/normalstaff')
    },
    {
      path: 'staff/Platformstaffroles',
      name: `账号管理-平台角色`,
      component: () => import('@/view/base/account/normalroles')
    },
    {
      path: 'assetpayment',
      name: `支付配置`,
      component: () => import('@/view/base/setting/payment')
    },
    {
      path: 'shopexerp',
      name: `shopexerp`,
      component: () => import('@/view/base/setting/shopexerp')
    },
    {
      path: 'dataAnalysis',
      name: `数据分析配置`,
      component: () => import('@/view/base/setting/dataAnalysis')
    },
    {
      path: 'mapsetting',
      name: `地图配置`,
      component: () => import('@/view/base/setting/mapsetting')
    },
    {
      path: 'domain_setting',
      name: `域名设置`,
      component: () => import('@/view/base/setting/companys/domain')
    },
    {
      path: 'openapi',
      name: `开发接口配置`,
      component: () => import('@/view/base/setting/opendev/index')
    },
    {
      path: 'Regionauth',
      name: `区域管理`,
      component: () => import('@/view/regionauth/list')
    },
    {
      path: 'workwechat',
      name: `企业微信设置`,
      component: () => import('@/view/base/setting/workwechat/workwechat')
    },
    {
      path: 'publicsetting',
      name: `通用设置`,
      component: () => import('@/view/base/setting/publicsetting')
    },
    {
      path: 'GoodsShareSetting',
      name: `商品分享设置`,
      component: () => import('@/view/base/setting/goodsShareSetting')
    },
    {
      path: 'certificate',
      name: `授权绑定`,
      component: () => import('@/view/base/setting/certificate/index')
    },
    {
      path: 'baseexport',
      name: `导出列表`,
      component: () => import('@/view/base/exportlog/index')
    },
    {
      path: 'datamessage',
      name: `短信账户`,
      component: () => import('@/view/base/shortmessage/index'),
      children: [
        {
          path: 'ali_sms',
          name: '阿里短信',
          component: () => import('@/view/base/shortmessage/ali_sms'),
          children: [
            {
              path: '/',
              name: '基础配置',
              meta: 'base_config',
              component: () => import('@/view/base/shortmessage/cpn/base_config')
            },
            {
              path: 'send_sms',
              name: '发送短信',
              meta: 'send_sms',
              component: () => import('@/view/base/shortmessage/cpn/send_sms')
            },
            {
              path: 'sms_signatures',
              name: '短信签名',
              component: () => import('@/view/base/shortmessage/cpn/sms_signatures'),
              meta: 'sms_signatures',
              children: [
                {
                  path: 'edit',
                  component: () => import('@/view/base/shortmessage/cpn/sms_signatures_edit'),
                  meta: 'sms_signatures'
                }
              ]
            },
            {
              path: 'sms_template',
              name: '短信模板',
              component: () => import('@/view/base/shortmessage/cpn/sms_template'),
              meta: 'sms_template',
              children: [
                {
                  path: 'edit',
                  component: () => import('@/view/base/shortmessage/cpn/sms_template_edit'),
                  meta: 'sms_template'
                }
              ]
            },
            {
              path: 'sms_sendLog',
              name: '短信发送记录',
              component: () => import('@/view/base/shortmessage/cpn/sms_sendLog'),
              meta: 'sms_sendLog'
            },
            {
              path: 'sms_MassLog',
              name: '短信群发送记录',
              component: () => import('@/view/base/shortmessage/cpn/sms_MassLog'),
              meta: 'sms_MassLog',
              children: [
                {
                  path: 'edit',
                  component: () => import('@/view/base/shortmessage/cpn/sms_MassLog_edit'),
                  meta: 'sms_MassLog'
                }
              ]
            }
          ]
        },
        {
          path: 'shopex_sms',
          name: '商派短信',
          component: () => import('@/view/base/shortmessage/shopex_sms')
        }
      ]
    },
    {
      path: 'basecurrency',
      name: `货币管理`,
      component: () => import('@/view/base/currency/index')
    },
    {
      path: 'assetaccountactivation',
      name: `续费激活`,
      component: () => import('@/view/base/asset/accountactivation/index')
    },
    {
      path: 'operatorlogs',
      name: `操作日志`,
      component: () => import('@/view/base/setting/companys/logs')
    },
    {
      path: 'omsqueuelog',
      name: `oms接口日志`,
      component: () => import('@/view/mall/trade/omsqueuelog')
    },
    {
      path: 'menumanage/shopmenus',
      name: `商城菜单管理`,
      component: () => import('@/view/menus/shopmenu')
    },
    {
      path: 'menumanage/distributormenu',
      name: `店铺菜单管理`,
      component: () => import('@/view/menus/distributormenu')
    },
    {
      path: 'openscreenad',
      name: `开屏广告`,
      component: () => import('@/view/base/screen/openScreenAD')
    },
    {
      path: 'adapay_merchant',
      name: '商户设置',
      component: () => import('@/view/base/setting/dealer'),
      children: [
        {
          path: '/',
          component: () => import('@/view/base/setting/dealer/account_info')
        },
        {
          path: 'picture',
          component: () => import('@/view/base/setting/dealer/upload_picture')
        },
        {
          path: 'pay_setting',
          component: () => import('@/view/base/setting/dealer/pay_setting')
        },
        {
          path: 'netin',
          component: () => import('@/view/base/setting/dealer/netin')
        }
      ]
    },
    {
      path: 'adapay_cash_setting',
      name: '提现设置',
      component: () => import('@/view/mall/withdraw/withdraw_setting.vue')
    },
    {
      path: 'datapass/approve',
      name: '权限申请',
      component: () => import('@/view/base/encrypt/authAdmin.vue')
    },
    {
      path: 'datapass/apply',
      name: `权限审批`,
      component: () => import('@/view/base/setting/authorityapproval'),
      children: [
        {
          path: 'approve/:id?',
          component: () => import('@/view/base/setting/authorityapproval/approve')
        }
      ]
    },
    {
      path: 'agreement',
      name: '软件使用协议',
      component: () => import('@/view/base/setting/agreement')
    }
  ]
}
