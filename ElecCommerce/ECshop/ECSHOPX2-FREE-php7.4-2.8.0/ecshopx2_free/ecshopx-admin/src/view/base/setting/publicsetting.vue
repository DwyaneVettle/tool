<style lang="scss"></style>
<template>
  <div>
    <SpForm
      v-model="form"
      size="min"
      :form-list="formList"
      :submit="false"
    />
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { VERSION_STANDARD, VERSION_IN_PURCHASE } from '@/utils'
export default {
  name: '',
  data () {
    return {
      form: {
        rate_status: false,
        whitelist_status: false,
        pickupcode_status: false,
        minus_shop_gift_store: false,
        check_gift_store: false,
        ziti_send_oms: false,
        nostores_status: false,
        recharge_status: false,
        repeat_cancel: false,
        item_store_status: false,
        item_sales_status: false,
        invoice_status: false,
        distributor_param_status: false,
        dianwu_show_status: false,
        item_page: [0],
        cart_page: [0],
        order_page: [0]
      },
      formList: [
        {
          label: '分享带门店参数',
          key: 'distributor_param_status',
          type: 'switch',
          isShow: VERSION_STANDARD,
          tip: `<div><p>
              已开启：
            </p>
            <p>
              1. 从小程序分享出去的 太阳码和小程序链接
              带店铺参，其他消费者通过此链接或者太阳码进入商城会切换成参数对应的店铺，同时本地缓存也会切换成参数对应的新店铺。
            </p>
            <p>
              2.
              顾客通过「管理后台-店铺-店铺商品」生成的商品二维码或其他带店铺参数的二维码进入商城时，其所在店铺将切换为二维码所带参数对应的店铺，同时本地缓存也会切换成参数对应的新店铺。
            </p>
            <p>
              已关闭：
            </p>
            <p>
              1. 从小程序分享出去的 太阳码和小程序链接
              不带店铺参，其他消费者通过此链接或者太阳码进入商城不会切换成参数对应的店铺，本地缓存也不会切换成参数对应的新店铺，即按照商城原本的进店逻辑进入店铺。
            </p>
            <p>
              2.
              顾客通过「管理后台-店铺-店铺商品」生成的商品二维码或其他带店铺参数的二维码进入商城时，其所在店铺将切换为二维码所带参数对应的店铺，同时本地缓存也会切换成参数对应的新店铺。
            </p></div>`,
          onChange: async (e) => {
            const { distributor_param_status } = this.form
            await this.$api.system.saveShareParams({
              distributor_param_status
            })
          }
        },
        {
          label: '评论设置',
          key: 'rate_status',
          type: 'switch',
          isShow: !VERSION_IN_PURCHASE,
          onChange: async (e) => {
            const { rate_status } = this.form
            await this.$api.company.setRateSetting({
              rate_status
            })
          }
        },
        {
          label: '提货码设置',
          key: 'pickupcode_status',
          type: 'switch',
          isShow: !this.isMicorMall && !VERSION_IN_PURCHASE,
          onChange: async (e) => {
            const { pickupcode_status } = this.form
            await this.$api.company.setPickupcodeSetting({
              pickupcode_status
            })
          }
        },
        {
          label: '会员白名单',
          key: 'whitelist_status',
          type: 'switch',
          onChange: async (e) => {
            const { whitelist_status } = this.form
            await this.$api.company.setWhitelistSetting({
              whitelist_status
            })
          }
        },
        {
          label: '赠品设置',
          key: 'check_gift_store',
          type: 'switch',
          tip: '赠品缺货是否能继续下单',
          isShow: !VERSION_IN_PURCHASE,
          onChange: async (e) => {
            const { check_gift_store } = this.form
            await this.$api.company.setGiftSetting({
              check_gift_store
            })
          }
        },
        {
          label: '推单设置',
          key: 'ziti_send_oms',
          type: 'switch',
          tip: '自提订单推oms',
          isShow: !VERSION_IN_PURCHASE,
          onChange: async (e) => {
            const { ziti_send_oms } = this.form
            await this.$api.company.setSendOmsSetting({
              ziti_send_oms
            })
          }
        },
        {
          label: '前端店铺展示',
          key: 'nostores_status',
          type: 'switch',
          tip: '用于开启前端店铺切换功能',
          isShow: VERSION_STANDARD,
          onChange: async (e) => {
            const { nostores_status } = this.form
            await this.$api.company.setNoStores({
              nostores_status: !nostores_status
            })
          }
        },
        {
          label: '取消订单设置',
          key: 'repeat_cancel',
          type: 'switch',
          tip: '拒绝后是否能再次发起申请',
          onChange: async (e) => {
            const { repeat_cancel } = this.form
            await this.$api.company.setRepeatCancelSetting({
              repeat_cancel
            })
          }
        },
        {
          label: '商品库存',
          key: 'item_store_status',
          type: 'switch',
          tip: '商品详情页是否显示库存',
          onChange: async (e) => {
            const { item_store_status } = this.form
            await this.$api.company.setItemStoreSetting({
              item_store_status
            })
          }
        },
        {
          label: '商品销量显示',
          key: 'item_sales_status',
          type: 'switch',
          onChange: async (e) => {
            const { item_sales_status } = this.form
            await this.$api.company.setItemSalesSetting({
              item_sales_status
            })
          }
        },
        {
          label: '发票设置',
          key: 'invoice_status',
          type: 'switch',
          tip: '结算页是否显示发票',
          isShow: !VERSION_IN_PURCHASE,
          onChange: async (e) => {
            const { invoice_status } = this.form
            await this.$api.company.setInvoiceStatus({
              invoice_status
            })
          }
        },
        {
          label: '店务设置',
          key: 'dianwu_show_status',
          type: 'switch',
          tip: '移动端会员中心是否展示店务端入口',
          onChange: async (e) => {
            const { dianwu_show_status } = this.form
            await this.$api.company.setDianwuShowStatus({
              dianwu_show_status
            })
          }
        },
        {
          label: '商品价格展示',
          type: 'group'
        },
        {
          label: '商品列表/商详页',
          key: 'item_page',
          type: 'checkbox',
          options: [
            { label: 0, name: '销售价', disabled: true },
            { label: 1, name: '原价' },
            { label: 2, name: '会员等级价' },
            { label: 3, name: '付费会员价' }
          ],
          onChange: async (e) => {
            this.saveItemPriceSetting()
          }
        },
        {
          label: '购物车',
          key: 'cart_page',
          type: 'checkbox',
          options: [
            { label: 0, name: '销售价/会员等级价/付费会员价', disabled: true },
            { label: 1, name: '原价' }
          ],
          onChange: async (e) => {
            this.saveItemPriceSetting()
          }
        },
        {
          label: '订单详情',
          key: 'order_page',
          type: 'checkbox',
          options: [
            { label: 0, name: '销售价/会员等级价/付费会员价', disabled: true },
            { label: 1, name: '原价' }
          ],
          onChange: async (e) => {
            this.saveItemPriceSetting()
          }
        }
      ]
    }
  },
  created () {
    this.fetch()
  },
  computed: {
    ...mapGetters(['isMicorMall'])
  },
  methods: {
    async fetch () {
      const res = await this.$api.company.getGlobalSetting()
      this.form = {
        ...this.form,
        rate_status: res.traderate_setting.rate_status,
        whitelist_status: res.whitelist_setting.whitelist_status,
        pickupcode_status: res.pickupcode_setting.pickupcode_status,
        minus_shop_gift_store: res.gift_setting.minus_shop_gift_store,
        check_gift_store: res.gift_setting.check_gift_store,
        ziti_send_oms: res.sendoms_setting.ziti_send_oms,
        nostores_status: !res.nostores_setting.nostores_status,
        recharge_status: res.recharge_setting.recharge_status,
        repeat_cancel: res.cancel_setting.repeat_cancel,
        item_store_status: res.item_store_setting.item_store_status,
        item_sales_status: res.item_sales_setting.item_sales_status,
        invoice_status: res.invoice_setting.invoice_status,
        distributor_param_status: res.share_parameters_setting.distributor_param_status,
        dianwu_show_status: res.dianwu_setting.dianwu_show_status
      }
      const { cart_page, order_page, item_page } = res.item_price_setting
      if (cart_page.market_price) {
        this.form.cart_page = [0, 1]
      }
      if (order_page.market_price) {
        this.form.order_page = [0, 1]
      }
      if (item_page.market_price) {
        this.form.item_page = [0, 1]
      }
      if (item_page.member_price) {
        this.form.item_page.push(2)
      }
      if (item_page.svip_price) {
        this.form.item_page.push(3)
      }
    },
    async saveItemPriceSetting () {
      const { cart_page, order_page, item_page } = this.form
      const params = {
        cart_page: {
          market_price: cart_page.indexOf(1) > -1
        },
        order_page: {
          market_price: order_page.indexOf(1) > -1
        },
        item_page: {
          market_price: item_page.indexOf(1) > -1,
          member_price: item_page.indexOf(2) > -1,
          svip_price: item_page.indexOf(3) > -1
        }
      }
      await this.$api.company.saveItemPriceSetting(params)
    }
  }
}
</script>
