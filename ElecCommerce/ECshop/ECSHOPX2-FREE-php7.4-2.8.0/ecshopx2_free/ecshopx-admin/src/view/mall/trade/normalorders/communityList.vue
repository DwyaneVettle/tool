<template>
  <div class="page-body">
    <div v-if="$route.path.indexOf('detail') === -1 && $route.path.indexOf('process') === -1">
      <SpFilterForm
        :model="params"
        @onSearch="onSearch"
        @onReset="onSearch"
      >
        <SpFilterFormItem
          prop="mobile"
          label="手机号:"
        >
          <el-input
            v-model="params.mobile"
            placeholder="请输入客户手机号码"
          />
        </SpFilterFormItem>
        <SpFilterFormItem
          prop="order_id"
          label="订单号:"
        >
          <el-input
            v-model="params.order_id"
            placeholder="请输入订单号"
          />
        </SpFilterFormItem>
        <SpFilterFormItem
          v-if="login_type != 'merchant' && !VERSION_B2C && !VERSION_IN_PURCHASE"
          prop="salesman_mobile"
          label="导购手机号:"
        >
          <el-input
            v-model="params.salesman_mobile"
            placeholder="请输入导购手机号码"
          />
        </SpFilterFormItem>
        <SpFilterFormItem
          v-if="!isMicorMall"
          prop="receipt_type"
          label="配送类型:"
        >
          <el-select
            v-model="params.receipt_type"
            clearable
            placeholder="请选择"
          >
            <el-option
              v-for="item in distributionType"
              :key="item.value"
              size="mini"
              :label="item.title"
              :value="item.value"
            />
          </el-select>
        </SpFilterFormItem>
        <SpFilterFormItem
          prop="source"
          label="订单来源:"
        >
          <el-select
            v-model="params.source"
            clearable
            placeholder="请选择"
          >
            <el-option
              v-for="item in orderSourceList"
              :key="item.value"
              size="mini"
              :label="item.title"
              :value="item.value"
            />
          </el-select>
        </SpFilterFormItem>
        <SpFilterFormItem
          prop="order_class"
          label="订单类型:"
        >
          <el-select
            v-model="params.order_class"
            clearable
            placeholder="请选择"
          >
            <el-option
              v-for="item in orderType"
              :key="item.value"
              size="mini"
              :label="item.title"
              :value="item.value"
            />
          </el-select>
        </SpFilterFormItem>
        <SpFilterFormItem
          prop="create_time"
          label="下单时间:"
          size="max"
        >
          <el-date-picker
            v-model="params.create_time"
            clearable
            type="datetimerange"
            align="right"
            format="yyyy-MM-dd HH:mm:ss"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            prefix-icon="null"
            :default-time="defaultTime"
            :picker-options="pickerOptions"
          />
        </SpFilterFormItem>
        <SpFilterFormItem
          v-if="!isMicorMall && !VERSION_IN_PURCHASE"
          prop="is_invoiced"
          label="开票状态:"
        >
          <el-select
            v-model="params.is_invoiced"
            clearable
            placeholder="请选择"
          >
            <el-option
              v-for="item in invoiceStatus"
              :key="item.value"
              size="mini"
              :label="item.title"
              :value="item.value"
            />
          </el-select>
        </SpFilterFormItem>
        <SpFilterFormItem
          prop="delivery_time"
          label="发货时间:"
          size="max"
        >
          <el-date-picker
            v-model="params.delivery_time"
            clearable
            type="datetimerange"
            align="right"
            format="yyyy-MM-dd HH:mm:ss"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            prefix-icon="null"
            :default-time="defaultTime"
            :picker-options="pickerOptions"
          />
        </SpFilterFormItem>
        <SpFilterFormItem
          v-if="!VERSION_STANDARD"
          prop="distributor_type"
          label="订单分类:"
        >
          <el-select
            v-model="params.distributor_type"
            clearable
            placeholder="请选择"
          >
            <el-option
              v-for="item in orderCategory"
              :key="item.value"
              size="mini"
              :label="item.title"
              :value="item.value"
            />
          </el-select>
        </SpFilterFormItem>
        <SpFilterFormItem
          v-if="(!isMicorMall || login_type != 'distributor') && !VERSION_B2C"
          prop="distributor_id"
          label="店铺:"
        >
          <SpSelectShop
            v-model="params.distributor_id"
            clearable
            placeholder="请选择"
          />
        </SpFilterFormItem>
        <SpFilterFormItem
          prop="subDistrict"
          label="选择街道:"
        >
          <el-cascader
            v-model="params.subDistrict"
            clearable
            :props="{
              value: 'id',
              checkStrictly: true
            }"
            :options="subDistrictList"
          />
        </SpFilterFormItem>
        <SpFilterFormItem
          prop="params.activity_name"
          label="社团名称:"
        >
          <el-input
            v-model="params.activity_name"
            placeholder="请输入社团名称"
          />
        </SpFilterFormItem>
      </SpFilterForm>

      <div class="action-container">
        <el-dropdown>
          <el-button
            type="primary"
            plain
          >
            导出<i class="el-icon-arrow-down el-icon--right" />
          </el-button>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <export-tip @exportHandle="exportInvoice">
                未开票订单
              </export-tip>
            </el-dropdown-item>
            <el-dropdown-item>
              <export-tip @exportHandle="exportDataMaster">
                主订单
              </export-tip>
            </el-dropdown-item>
            <el-dropdown-item>
              <export-tip @exportHandle="exportDataNormal">
                子订单
              </export-tip>
            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <el-tooltip
          effect="light"
          content="请将从订单列表导出的主订单文件，删除不想批量发货的订单号，修改物流公司，物流单号后上传即可。"
          placement="top-start"
        >
          <el-upload
            action=""
            class="btn-upload"
            :on-change="uploadHandleChange"
            :auto-upload="false"
            :show-file-list="false"
          >
            <el-button
              type="primary"
              plain
            >
              批量发货
            </el-button>
          </el-upload>
        </el-tooltip>
        <el-upload
          action=""
          class="btn-upload"
          :on-change="uploadHandlePatchCancel"
          :auto-upload="false"
          :show-file-list="false"
        >
          <el-button
            type="primary"
            plain
          >
            批量取消
          </el-button>
        </el-upload>
      </div>

      <el-tabs
        v-model="params.order_status"
        type="card"
        @tab-click="onSearch"
      >
        <el-tab-pane
          v-for="item in orderStatus"
          :key="item.value"
          :label="item.title"
          :name="item.value"
        />
        <el-table
          v-loading="loading"
          border
          :data="tableList"
        >
          <el-table-column
            width="220"
            prop="order_id"
            label="订单信息"
          >
            <template slot-scope="scope">
              <div class="order-num">
                {{ scope.row.order_id }}
                <el-tooltip
                  effect="dark"
                  content="复制"
                  placement="top-start"
                >
                  <i
                    v-clipboard:copy="scope.row.order_id"
                    v-clipboard:success="onCopySuccess"
                    class="el-icon-document-copy"
                  />
                </el-tooltip>
              </div>
              <div
                v-if="scope.row.distributor_id !== '0'"
                class="order-store"
              >
                <el-tooltip
                  effect="dark"
                  content="店铺名"
                  placement="top-start"
                >
                  <i class="el-icon-office-building" />
                </el-tooltip>
                {{ scope.row.distributor_name }}
              </div>
              <div class="order-time">
                <el-tooltip
                  effect="dark"
                  content="下单时间"
                  placement="top-start"
                >
                  <i class="el-icon-time" />
                </el-tooltip>
                {{ scope.row.create_time | datetime('YYYY-MM-DD HH:mm:ss') }}
              </div>
            </template>
          </el-table-column>
          <el-table-column
            width="220"
            prop="order_id"
            label="跟团信息"
          >
            <template slot-scope="scope">
              <div>所属团长：{{ scope.row.community_info.chief_name }}</div>
              <div>团名称：{{ scope.row.community_info.activity_name }}</div>
              <div>跟团号：{{ scope.row.community_info.activity_trade_no }}</div>
            </template>
          </el-table-column>
          <el-table-column
            prop="mobile"
            width="150"
            label="收件人信息"
          >
            <template slot-scope="scope">
              <template v-if="!scope.row.user_delete && login_type !== 'merchant'">
                <div>收件人：{{ scope.row.community_info.ziti_contact_user }}</div>
                <router-link
                  target="_blank"
                  :to="{
                    path: `${
                      login_type != 'distributor'
                        ? '/member/member/detail'
                        : '/shopadmin/member/member/detail'
                    }`,
                    query: { user_id: scope.row.user_id }
                  }"
                >
                  {{ scope.row.mobile }}
                </router-link>
                <el-tooltip
                  v-if="datapass_block == 0"
                  effect="dark"
                  content="复制"
                  placement="top-start"
                >
                  <i
                    v-clipboard:copy="scope.row.mobile"
                    v-clipboard:success="onCopySuccess"
                    class="el-icon-document-copy"
                  />
                </el-tooltip>
              </template>
              <template
                v-else
                slot-scope="scope"
              >
                <span>{{ scope.row.mobile }}</span>
                <el-tooltip
                  v-if="datapass_block == 0"
                  effect="dark"
                  content="复制"
                  placement="top-start"
                >
                  <i
                    v-clipboard:copy="scope.row.mobile"
                    v-clipboard:success="onCopySuccess"
                    class="el-icon-document-copy"
                  />
                </el-tooltip>
              </template>
            </template>
          </el-table-column>
          <el-table-column
            prop="total_fee"
            width="120"
            label="订单金额（¥）"
          >
            <template slot-scope="scope">
              {{ (scope.row.total_fee / 100).toFixed(2) }}
            </template>
          </el-table-column>
          <el-table-column
            width="100"
            label="运费（¥）"
          >
            <template slot-scope="scope">
              {{ (scope.row.freight_fee || 0) / 100 }}
            </template>
          </el-table-column>
          <template v-if="login_type != 'merchant'">
            <el-table-column
              v-if="!isMicorMall"
              label="订单类型"
            >
              <template slot-scope="scope">
                {{ getOrderType(scope.row) }}
              </template>
            </el-table-column>
          </template>
          <el-table-column
            prop="order_status"
            label="订单状态"
          >
            <template slot-scope="scope">
              {{ scope.row.order_status_msg }}
            </template>
          </el-table-column>

          <el-table-column label="配送方式">
            <template slot-scope="scope">
              {{ getDistributionType(scope.row) }}
            </template>
          </el-table-column>

          <!-- <el-table-column prop="source_name" label="来源"></el-table-column> -->
          <el-table-column
            label="操作"
            fixed="left"
          >
            <template slot-scope="scope">
              <el-button
                type="text"
                style="margin-right: 8px"
              >
                <router-link
                  :to="`${$route.path}/detail?orderId=${scope.row.order_id}&resource=${$route.path}`"
                >
                  详情
                </router-link>
              </el-button>
              <el-popover
                placement="right"
                trigger="hover"
              >
                <div class="operating-icons">
                  <el-button type="text">
                    <router-link
                      :to="`${$route.path}/process?orderId=${scope.row.order_id}&resource=${$route.path}`"
                    >
                      日志
                    </router-link>
                  </el-button>
                  <template v-for="(btn, index) in scope.row.actionBtns">
                    <el-button
                      :key="`btn-item__${index}`"
                      type="text"
                      @click="handleAction(scope.row, btn)"
                    >
                      {{ btn.name }}
                    </el-button>
                  </template>
                </div>
                <el-button
                  slot="reference"
                  type="text"
                >
                  更多<i class="iconfont icon-angle-double-right" />
                </el-button>
              </el-popover>
            </template>
          </el-table-column>
        </el-table>
        <div class="content-padded content-center">
          <el-pagination
            background
            layout="total, sizes, prev, pager, next, jumper"
            :current-page.sync="page.pageIndex"
            :page-sizes="[10, 20, 50]"
            :total="page.total"
            :page-size="page.pageSize"
            @current-change="onCurrentChange"
            @size-change="onSizeChange"
          />
        </div>
      </el-tabs>

      <!-- 备注 -->
      <SpDialog
        ref="remarkDialogRef"
        v-model="remarkDialog"
        :title="`修改备注【订单:${remarkForm.orderId}】`"
        :form="remarkForm"
        :form-list="remarkFormList"
        @onSubmit="onRemarkSubmit"
      />

      <!-- 取消订单 -->
      <SpDialog
        ref="cancelOrderDialogRef"
        v-model="cancelOrderDialog"
        :title="`取消订单【订单:${cancelOrderForm.order_id}】`"
        :form="cancelOrderForm"
        :form-list="cancelOrderFormList"
        @onSubmit="onCancelOrderSubmit"
      />

      <!-- 发货 -->
      <SpDialog
        ref="deliverGoodsDialogRef"
        v-model="deliverGoodsDialog"
        width="1000px"
        :title="`发货【订单:${deliverGoodsForm.order_id}】`"
        :form="deliverGoodsForm"
        :form-list="deliverGoodsFormList"
        @onSubmit="deliverGoodsSubmit"
      />

      <!-- 核销 -->
      <SpDialog
        ref="writeOffDialogRef"
        v-model="writeOffDialog"
        :title="`核销【订单:${writeOffForm.order_id}】`"
        :form="writeOffForm"
        :form-list="writeOffFormList"
        @onSubmit="writeOffSubmit"
      />

      <!-- 退款审核 -->
      <SpDialog
        ref="refundRef"
        v-model="refundDialog"
        :title="`退款【订单:${refundForm.order_id}】`"
        :form="refundForm"
        :form-list="refundFormList"
        @onSubmit="refundSubmit"
      />
    </div>
    <router-view />
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
import mixin from '@/mixins'
import { pageMixin } from '@/mixins'
import { VERSION_STANDARD, isArray, VERSION_B2C, VERSION_IN_PURCHASE } from '@/utils'
import { exportInvoice, orderExport } from '@/api/trade'
import moment from 'moment'
import {
  DISTRIBUTION_TYPE,
  ORDER_STATUS,
  ORDER_B2C_STATUS,
  IN_PURCHASE_STATUS,
  INVOICE_STATUS,
  ORDER_CATEGORY,
  PICKER_DATE_OPTIONS,
  REFUND_STATUS,
  REFUND_PROCESS,
  PAY_TYPE
} from '@/consts'

const ORDER_TYPE = [{ title: '社区团购订单', value: 'community' }]

export default {
  mixins: [mixin, pageMixin],
  data () {
    return {
      loading: false,
      defaultTime: ['00:00:00', '23:59:59'],
      params: {
        mobile: '',
        order_id: '',

        salesman_mobile: '',
        receipt_type: '', // 配送类型
        source: '', // 订单来源
        order_status: '', // 订单状态
        order_class: 'community', // 订单类型
        is_invoiced: '', // 开票状态
        time_start_begin: '', //
        time_start_end: '',
        distributor_type: '', // 订单分类
        distributor_id: '', // 店铺
        subDistrict: []
      },
      datapass_block: 1, // 是否为数据脱敏
      subDistrictList: [],
      distributionType: DISTRIBUTION_TYPE,
      orderStatus: VERSION_B2C
        ? ORDER_B2C_STATUS
        : VERSION_IN_PURCHASE
        ? IN_PURCHASE_STATUS
        : ORDER_STATUS,
      orderType: ORDER_TYPE,
      invoiceStatus: INVOICE_STATUS,
      orderCategory: ORDER_CATEGORY,
      pickerOptions: PICKER_DATE_OPTIONS,
      orderSourceList: [],
      remarkDialog: false,
      remarkFormList: [
        {
          label: '备注信息:',
          key: 'remark',
          type: 'textarea',
          maxlength: 150,
          placeholder: '请输入对此订单需要备注的内容',
          required: true,
          message: '不能为空'
        }
      ],
      remarkForm: {
        orderId: '',
        is_distribution: 1,
        remark: ''
      },
      cancelOrderDialog: false,
      cancelOrderFormList: [
        {
          label: '取消原因:',
          key: 'cancel_reason',
          placeholder: '请选择取消订单原因',
          type: 'select',
          options: [
            { title: '客户现在不想购买', value: 1 },
            { title: '客户商品价格较贵', value: 2 },
            { title: '客户价格波动', value: 3 },
            { title: '客户商品缺货', value: 4 },
            { title: '客户重复下单', value: 5 },
            { title: '客户订单商品选择有误', value: 6 },
            { title: '客户支付方式选择有误', value: 7 },
            { title: '客户收货信息填写有误', value: 8 },
            { title: '客户发票信息填写有误', value: 9 },
            { title: '客户无法支付订单', value: 10 },
            { title: '客户长时间未付款', value: 11 },
            { title: '客户其他原因', value: 12 }
          ],
          required: true,
          message: '不能为空',
          onChange: (e) => {
            if (e == 12) {
              this.cancelOrderFormList[1].isShow = true
            } else {
              this.cancelOrderFormList[1].isShow = false
            }
          }
        },
        {
          label: '其他原因:',
          key: 'other_reason',
          type: 'input',
          placeholder: '请填写取消订单原因',
          isShow: false,
          validator: (rule, value, callback) => {
            if (this.cancelOrderForm.cancel_reason == 12 && !value) {
              callback(new Error('不能为空'))
            } else {
              callback()
            }
          }
        }
      ],
      cancelOrderForm: {
        order_id: '',
        cancel_reason: '',
        other_reason: ''
      },
      deliverGoodsDialog: false,
      deliverGoodsFormList: [
        {
          label: '发货类型:',
          key: 'delivery_type',
          type: 'radio',
          disabled: false,
          options: [
            { label: 'batch', name: '整单发货' },
            { label: 'sep', name: '拆分发货' }
          ],
          onChange: (e) => {
            if (e == 'sep') {
              this.deliverGoodsFormList[1].options[4].isShow = true
            } else {
              this.deliverGoodsFormList[1].options[4].isShow = false
            }
          }
        },
        {
          label: '',
          key: 'items',
          type: 'table',
          options: [
            { title: '商品名', key: 'item_name' },
            { title: '数量', key: 'num', width: 60 },
            { title: '已发货数量', key: 'delivery_item_num', width: 100 },
            { title: '总支付价（¥）', key: 'price', width: 120 },
            {
              title: '发货数量',
              key: 'item_num',
              width: 160,
              render: (row, column, cell) => {
                if (row.num - row.delivery_item_num == 0) {
                  return '已完成'
                } else {
                  return (
                    <el-input-number
                      size='mini'
                      v-model={row.delivery_num}
                      min={1}
                      max={row.num - row.delivery_item_num}
                    ></el-input-number>
                  )
                }
              },
              isShow: false
            }
          ]
        },
        {
          label: '快递公司:',
          key: 'delivery_corp',
          placeholder: '请选择快递公司',
          type: 'select',
          options: [],
          required: true,
          message: '不能为空'
        },
        {
          label: '物流单号:',
          key: 'delivery_code',
          type: 'input',
          placeholder: '物流公司单号',
          required: true,
          message: '不能为空'
        }
      ],
      deliverGoodsForm: {
        order_id: '',
        delivery_type: 'batch',
        delivery_corp: '',
        delivery_code: '',
        type: '',
        items: []
      },
      writeOffDialog: false,
      writeOffFormList: [
        {
          label: '',
          key: 'items',
          type: 'table',
          options: [
            { title: '商品名', key: 'item_name' },
            { title: '数量', key: 'num', width: 60 },
            { title: '已发货数量', key: 'delivery_item_num', width: 100 },
            { title: '总支付价（¥）', key: 'price', width: 120 }
          ]
        },
        {
          label: '提货码:',
          key: 'pickupcode',
          type: 'input',
          placeholder: '请输入提货码',
          required: true,
          message: '不能为空'
        }
      ],
      writeOffForm: {
        order_id: '',
        pickupcode: '',
        items: []
      },
      refundDialog: false,
      refundFormList: [
        {
          label: '取消来源:',
          key: 'source',
          type: 'text'
        },
        {
          label: '申请时间:',
          key: 'applyTime',
          type: 'text'
        },
        {
          label: '退款状态:',
          key: 'refundStatus',
          type: 'text'
        },
        {
          label: '处理进度:',
          key: 'process',
          type: 'text'
        },
        {
          label: '退款金额:',
          key: 'refundPrice',
          type: 'text'
        },
        {
          label: '支付方式:',
          key: 'payType',
          type: 'text'
        },
        {
          label: '取消原因:',
          key: 'reason',
          type: 'text'
        },
        {
          label: '处理结果:',
          key: 'check_cancel',
          type: 'radio',
          options: [
            { label: '0', name: '不同意' },
            { label: '1', name: '同意' }
          ],
          onChange: (e) => {
            if (e == '0') {
              this.refundFormList[8].isShow = true
            } else {
              this.refundFormList[8].isShow = false
            }
          }
        },
        {
          label: '拒绝原因:',
          key: 'shop_reject_reason',
          type: 'input',
          placeholder: '请输入拒绝原因',
          isShow: false,
          validator: (rule, value, callback) => {
            if (this.refundForm.check_cancel == '0' && !value) {
              callback(new Error('不能为空'))
            } else {
              callback()
            }
          }
        }
      ],
      refundForm: {
        order_id: '',
        source: '',
        applyTime: '',
        refundStatus: '',
        process: '',
        refundPrice: '',
        payType: '',
        reason: '',
        check_cancel: '1',
        shop_reject_reason: ''
      },
      origin: ''
    }
  },
  computed: {
    ...mapGetters(['login_type', 'isMicorMall'])
  },
  mounted () {
    this.origin = window.location.origin
    const { tab } = this.$route.query
    if (tab) {
      this.params.order_status = tab
    }
    this.fetchList()
    this.getOrderSourceList()
    this.getLogisticsList()
    this.getSubDistrictList()
  },
  methods: {
    async fetchList () {
      this.loading = true
      const { pageIndex: page, pageSize } = this.page
      let params = {
        page,
        pageSize,
        order_type: 'normal',
        ...this.params,
        subdistrict_parent_id: this.params.subDistrict[0], // 街道id
        subdistrict_id: this.params.subDistrict[1] // 居委id
      }
      delete params.subDistrict

      if (isArray(this.params.create_time) && this.params.create_time.length >= 2) {
        params.time_start_begin = moment(this.params.create_time[0]).unix()
        params.time_start_end = moment(this.params.create_time[1]).unix()
      }

      if (isArray(this.params.delivery_time) && this.params.delivery_time.length >= 2) {
        params.delivery_time_begin = moment(this.params.delivery_time[0]).unix()
        params.delivery_time_end = moment(this.params.delivery_time[1]).unix()
      }

      delete params.create_time
      delete params.delivery_time

      const { list, pager, datapass_block } = await this.$api.community.getCommunityOrderList(
        params
      )

      this.tableList = list.map((item) => {
        const actionBtns = []
        const {
          distributor_id,
          receipt_type,
          cancel_status,
          is_logistics,
          is_invoiced,
          invoice,
          order_status,
          ziti_status,
          dada,
          delivery_status,
          pay_status
        } = item
        const isDada = receipt_type == 'dada'
        const isLogistics = receipt_type == 'logistics'
        if (VERSION_STANDARD || distributor_id == 0 || this.login_type == 'distributor') {
          if (
            !isDada &&
            cancel_status == 'NO_APPLY_CANCEL' &&
            ['NOTPAY', 'PAYED'].includes(order_status) &&
            ziti_status != 'DONE'
          ) {
            // 非同城配的取消订单按钮
            if (!isDada || (isDada && ['0', '1'].includes(dada.data_status))) {
              actionBtns.push({ name: '取消订单', key: 'cancel' })
            }
          }

          if (order_status == 'PAYED' && receipt_type == 'ziti' && ziti_status == 'PENDING') {
            actionBtns.push({ name: '核销', key: 'writeOff' })
          }

          if (
            isDada &&
            ['NO_APPLY_CANCEL', 'FAILS'].includes(cancel_status) &&
            order_status == 'PAYED' &&
            delivery_status != 'DONE' &&
            dada.dada_status == '0'
          ) {
            actionBtns.push({ name: '接单', key: 'takeOrder' })
          }

          if (isDada && pay_status == 'PAYED' && dada.dada_status == '9') {
            actionBtns.push({ name: '确认退回', key: 'orderSendBack' })
          }

          if (
            (isLogistics || is_logistics) &&
            !isDada &&
            order_status == 'PAYED' &&
            delivery_status != 'DONE' &&
            receipt_type != 'ziti'
          ) {
            actionBtns.push({ name: '发货', key: 'deliverGoods' })
          }

          if (cancel_status == 'WAIT_PROCESS' && order_status == 'PAYED') {
            actionBtns.push({ name: '退款', key: 'refund' })
          }

          if (is_invoiced == '0' && invoice) {
            actionBtns.push({ name: '待开票', key: 'waitInvoice' })
          }

          actionBtns.push({ name: '备注', key: 'remark' })
        }
        return {
          ...item,
          actionBtns
        }
      })
      this.page.total = pager ? pager.count : 0
      this.datapass_block = datapass_block
      this.loading = false
    },
    async getSubDistrictList () {
      const res = await this.$api.subdistrict.getSubDistrictList()
      console.log(`getSubDistrictList:`, res)
      this.subDistrictList = res
    },
    getOrderType ({ order_class, type }) {
      if (order_class == 'normal') {
        return type == '1' ? '跨境订单' : '普通订单'
      }
      const fd = ORDER_TYPE.find((item) => item.value == order_class)
      if (fd) {
        return fd.title
      }
    },
    getDistributionType ({ receipt_type }) {
      const fd = DISTRIBUTION_TYPE.find((item) => item.value == receipt_type)
      if (fd) {
        return fd.title
      }
    },
    async getOrderSourceList () {
      const { list } = await this.$api.datacube.getSourcesList({
        page: 1,
        pageSize: 1000
      })

      this.orderSourceList = list.map(({ sourceName, sourceId }) => {
        return {
          title: sourceName,
          value: sourceId
        }
      })
    },
    async getLogisticsList () {
      const { list } = await this.$api.trade.getLogisticsList()
      this.deliverGoodsFormList[2].options = list.map((item) => {
        return {
          title: item.name,
          value: item.value
        }
      })
    },
    async handleAction (
      { order_id, distributor_remark, items, delivery_type, delivery_status },
      { key }
    ) {
      if (key == 'remark') {
        this.$refs['remarkDialogRef'].resetForm()
        this.remarkForm.orderId = order_id
        this.remarkForm.remark = distributor_remark
        this.remarkDialog = true
      } else if (key == 'cancel') {
        this.$refs['cancelOrderDialogRef'].resetForm()
        this.cancelOrderForm.order_id = order_id
        this.cancelOrderDialog = true
      } else if (key == 'deliverGoods') {
        this.$refs['deliverGoodsDialogRef'].resetForm()
        this.deliverGoodsForm.order_id = order_id
        this.deliverGoodsForm.items = items.map((item) => {
          return {
            ...item,
            price: item.price / 100
          }
        })
        this.deliverGoodsForm.type = delivery_type
        // 部分发货
        if (delivery_status == 'PARTAIL') {
          this.deliverGoodsForm.delivery_type = 'sep'
          this.deliverGoodsFormList[0].disabled = true
          this.deliverGoodsFormList[1].options[4].isShow = true
        } else {
          this.deliverGoodsFormList[0].disabled = false
          this.deliverGoodsFormList[1].options[4].isShow = false
        }
        this.deliverGoodsDialog = true
      } else if (key == 'writeOff') {
        this.$refs['writeOffDialogRef'].resetForm()
        this.writeOffForm.order_id = order_id
        this.writeOffForm.items = items.map((item) => {
          return {
            ...item,
            price: item.price / 100
          }
        })
        this.writeOffDialog = true
      } else if (key == 'takeOrder') {
        this.$confirm('请在接单前确认商品当前库存', '接单提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          await this.$api.trade.doBusinessReceipt(order_id)
          this.$message.success('接单成功')
        })
      } else if (key == 'orderSendBack') {
        this.$confirm('确认退回', '确认退回提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(async () => {
          await this.$api.trade.confirmBack(order_id)
          this.$message.success('退回成功')
        })
      } else if (key == 'refund') {
        this.$refs['refundRef'].resetForm()
        this.refundDialog = true
        const {
          cancel_from,
          create_time,
          refund_status,
          progress,
          fee_symbol,
          total_fee,
          pay_type,
          cancel_reason
        } = await this.$api.trade.getCancelOrderInfo(order_id, { order_type: 'normal' })
        this.refundForm = {
          ...this.refundForm,
          order_id,
          source: cancel_from == 'buyer' ? '消费者申请' : '系统自动取消',
          applyTime: moment(create_time * 1000).format('YYYY-MM-DD HH:mm:ss'),
          refundStatus: REFUND_STATUS[refund_status],
          process: REFUND_PROCESS[progress],
          refundPrice: `${fee_symbol}${total_fee / 100}`,
          payType: PAY_TYPE[pay_type],
          reason: cancel_reason
        }
      } else if (key == 'waitInvoice') {
        this.$confirm('确定更新该订单开票状态？', '提示', {
          cancelButtonText: '取消',
          confirmButtonText: '确定',
          type: 'warning'
        }).then(async () => {
          const { success } = await this.$api.trade.isInvoiced({
            order_id: order_id,
            status: 1
          })
          this.fetchList()
          if (success) {
            this.$message.success('已更新开票状态')
          } else {
            this.$message.error('更新开票状态失败')
          }
        })
      }
    },
    async onRemarkSubmit () {
      await this.$api.order.remarks(this.remarkForm)
      this.$message.success('订单备注修改成功!')
      this.remarkDialog = false
      this.fetchList()
    },
    async onCancelOrderSubmit () {
      const { order_id } = this.cancelOrderForm
      console.log(this.cancelOrderForm)
      await this.$api.trade.cancelOrderConfirm(order_id, this.cancelOrderForm)
      this.$message.success('订单取消成功!')
      this.cancelOrderDialog = false
      this.fetchList()
    },
    async deliverGoodsSubmit () {
      const { order_id, delivery_type, delivery_corp, delivery_code, type, items } =
        this.deliverGoodsForm
      let params = {
        order_id,
        delivery_type,
        delivery_corp,
        delivery_code,
        type
      }
      // 拆单发货
      if (delivery_type == 'sep') {
        params['sepInfo'] = JSON.stringify(items.filter((item) => item.delivery_num))
      }
      const { delivery_status } = await this.$api.trade.delivery(params)
      this.deliverGoodsDialog = false
      this.fetchList()
      if (delivery_status && delivery_status != 'PENDING') {
        this.$message.success('发货成功!')
      } else {
        this.$message.error('发货失败!')
      }
    },
    async writeOffSubmit () {
      const { order_id } = this.writeOffForm
      const { ziti_status } = await this.$api.trade.doWriteoff(order_id, this.writeOffForm)
      if (ziti_status == 'DONE') {
        this.$message.success('自提订单核销成功!')
      } else {
        this.$message.error('自提订单核销失败!')
      }
      this.writeOffDialog = false
    },
    async refundSubmit () {
      const { order_id, check_cancel, shop_reject_reason } = this.refundForm
      const { refund_status } = await this.$api.trade.cancelConfirm(order_id, {
        order_id,
        check_cancel,
        shop_reject_reason
      })
      if (refund_status == 'AUDIT_SUCCESS') {
        this.$message.success('审核通过!')
      } else if (refund_status == 'SHOP_CHECK_FAILS') {
        this.$message.success('审核已拒绝!')
      } else {
        this.$message.error('审核失败!')
      }
      this.refundDialog = false
    },
    exportInvoice () {
      let type = 'normal'
      this.$emit('onChangeData', 'params', { type })
      exportInvoice({
        ...this.params,
        type,
        order_type: 'normal'
      }).then((response) => {
        const { status, url, filename } = response.data.data
        if (status) {
          this.$message.success('已加入执行队列，请在设置-导出列表中下载')
          this.$export_open('invoice')
          return
        } else if (url) {
          window.open(url)
        } else {
          this.$message.error('无内容可导出或执行失败，请检查重试')
          return
        }
      })
    },
    exportDataNormal () {
      this.exportData('normal_order')
    },
    exportDataMaster () {
      this.exportData('normal_master_order')
    },
    exportData (type) {
      console.log('====exportData', type)
      let params = {
        ...this.params,
        order_type: 'normal',
        type,
        page: this.page.pageIndex
      }
      if (isArray(this.params.create_time) && this.params.create_time.length >= 2) {
        params.time_start_begin = moment(this.params.create_time[0]).unix()
        params.time_start_end = moment(this.params.create_time[1]).unix()
      }

      if (isArray(this.params.delivery_time) && this.params.delivery_time.length >= 2) {
        params.delivery_time_begin = moment(this.params.delivery_time[0]).unix()
        params.delivery_time_end = moment(this.params.delivery_time[1]).unix()
      }

      delete params.create_time
      delete params.delivery_time

      orderExport(params).then((response) => {
        const { status, url, filename } = response.data.data
        if (status) {
          this.$message.success('已加入执行队列，请在设置-导出列表中下载')
          this.$export_open(type)
          return
        } else if (url) {
          window.open(url)
        } else {
          this.$message.error('无内容可导出或执行失败，请检查重试')
          return
        }
      })
    },
    async uploadHandleChange (file) {
      const params = {
        isUploadFile: true,
        file_type: 'normal_orders',
        file: file.raw
      }
      await this.$api.common.handleUploadFile(params)
      this.$message.success('上传成功，等待处理')
      this.fetchList()
    },
    async uploadHandlePatchCancel (file) {
      const params = {
        isUploadFile: true,
        file_type: 'normal_orders_cancel',
        file: file.raw
      }
      await this.$api.common.handleUploadFile(params)
      this.$message.success('上传成功，等待处理')
      this.fetchList()
    }
  }
}
</script>
<style lang="scss" scope>
.sp-filter-form {
  margin-bottom: 16px;
}
</style>
