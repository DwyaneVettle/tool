<template>
  <div
    class="section-white"
    style="padding-bottom: 70px"
  >
    <h3 class="title">
      售后详情
    </h3>
    <div class="section-header with-border flex">
      <h3>售后信息</h3>
      <el-button
        type="text"
        size="large"
        style="
          margin-left: 10px;
          line-height: 1.5;
          color: #5e6d82;
          font-weight: 500;
          font-size: 16px;
          color: #459ae9;
        "
        @click="clickShowRemark(aftersalesInfo, 'afterDetail')"
      >
        备注
      </el-button>
    </div>
    <div class="section-body">
      <el-row>
        <el-col
          :span="3"
          class="col-3 content-right"
        >
          售后单号:
        </el-col>
        <el-col :span="20">
          {{ aftersalesInfo.aftersales_bn }}
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="3"
          class="col-3 content-right"
        >
          订单号:
        </el-col>
        <el-col :span="20">
          {{ aftersalesInfo.order_id }}
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="3"
          class="col-3 content-right"
        >
          售后类型:
        </el-col>
        <el-col :span="20">
          <span v-if="aftersalesInfo.aftersales_type == 'ONLY_REFUND'">仅退款 </span>
          <span v-if="aftersalesInfo.aftersales_type == 'REFUND_GOODS'">退货退款 </span>
          <span v-if="aftersalesInfo.aftersales_type == 'EXCHANGING_GOODS'">换货 </span>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="3"
          class="col-3 content-right"
        >
          应退总金额:
        </el-col>
        <el-col :span="20">
          ￥{{ aftersalesInfo.refund_fee / 100 }}
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="3"
          class="col-3 content-right"
        >
          应退总积分:
        </el-col>
        <el-col :span="20">
          {{ aftersalesInfo.refund_point }}
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="3"
          class="col-3 content-right"
        >
          申请时间:
        </el-col>
        <el-col :span="20">
          {{ aftersalesInfo.create_time | datetime('YYYY-MM-DD HH:mm:ss') }}
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="3"
          class="col-3 content-right"
        >
          售后状态:
        </el-col>
        <el-col :span="20">
          <span v-if="aftersalesInfo.aftersales_status == '0'"> 等待商家处理</span>
          <span v-else-if="aftersalesInfo.aftersales_status == '1'"> 商家处理中</span>
          <span v-else-if="aftersalesInfo.aftersales_status == '2'"> 已处理</span>
          <span v-else-if="aftersalesInfo.aftersales_status == '3'"> 已驳回</span>
          <span v-else-if="aftersalesInfo.aftersales_status == '4'"> 已撤销</span>
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="3"
          class="col-3 content-right"
        >
          处理进度:
        </el-col>
        <el-col :span="20">
          <span v-if="aftersalesInfo.progress == '0'"> 等待商家处理</span>
          <span v-else-if="aftersalesInfo.progress == '1'">商家接受申请，等待消费者回寄</span>
          <span v-else-if="aftersalesInfo.progress == '2'">消费者回寄，等待商家收货确认</span>
          <span v-else-if="aftersalesInfo.progress == '3'">售后已驳回</span>
          <span v-else-if="aftersalesInfo.progress == '4'">售后已处理</span>
          <span v-else-if="aftersalesInfo.progress == '5'">退款驳回</span>
          <span v-else-if="aftersalesInfo.progress == '6'">退款已处理</span>
          <span v-else-if="aftersalesInfo.progress == '7'">售后关闭</span>
          <span v-else-if="aftersalesInfo.progress == '8'">商家确认收货</span>
          <span v-else-if="aftersalesInfo.progress == '9'">退款处理中</span>
        </el-col>
      </el-row>
      <el-row v-if="aftersalesInfo.refuse_reason">
        <el-col
          :span="3"
          class="col-3 content-right"
        >
          商家处理申请说明:
        </el-col>
        <el-col :span="20">
          {{ aftersalesInfo.refuse_reason }}
        </el-col>
      </el-row>
    </div>
    <div class="remark_footer">
      <el-row style="width: 100%">
        <el-col
          :span="3"
          class="remark_footer_title col-3 content-right"
        >
          商家备注:
        </el-col>
        <el-col :span="20">
          <div class="remark_footer_content">
            {{ aftersalesInfo.distributor_remark }}
          </div>
        </el-col>
      </el-row>
    </div>
    <div class="section-header with-border">
      <h3>售后商品信息</h3>
    </div>
    <div class="section-body">
      <el-row>
        <template>
          <el-table
            :data="aftersalesInfo.detail"
            style="width: 100%"
          >
            <el-table-column
              class="goods-img"
              prop="pic"
              label="商品图片"
              width="180"
            >
              <template slot-scope="scope">
                <span><img
                  :src="scope.row.item_pic"
                  :alt="scope.row.item_name"
                ></span>
              </template>
            </el-table-column>
            <el-table-column
              prop="item_name"
              label="商品名称"
              width="180"
            />
            <el-table-column
              prop="item_spec_desc"
              label="规格"
              width="180"
            />
            <el-table-column
              prop="num"
              label="申请数量"
              width="180"
            />
            <el-table-column label="应退总金额(元)">
              <template slot-scope="scope">
                <span>￥{{ scope.row.refund_fee / 100 }}</span>
              </template>
            </el-table-column>
            <el-table-column label="应退总积分">
              <template slot-scope="scope">
                <span>{{ scope.row.refund_point }}</span>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-row>
    </div>

    <div class="section-header with-border">
      <h3>售后申请信息</h3>
    </div>
    <div class="section-body">
      <el-row>
        <el-col
          :span="3"
          class="col-3 content-right"
        >
          退货理由:
        </el-col>
        <el-col :span="20">
          {{ aftersalesInfo.reason }}
        </el-col>
      </el-row>
      <el-row>
        <el-col
          :span="3"
          class="col-3 content-right"
        >
          问题描述:
        </el-col>
        <el-col :span="20">
          {{ aftersalesInfo.description }}
        </el-col>
      </el-row>
      <el-row v-if="aftersalesInfo.evidence_pic">
        <el-col
          :span="3"
          class="col-3 content-right"
        >
          图片信息:
        </el-col>
        <el-col :span="20">
          <ul v-if="aftersalesInfo.evidence_pic[0]">
            <li
              v-for="(pic, index) in aftersalesInfo.evidence_pic"
              :key="index"
              class="artical-item"
            >
              <el-image
                style="width: 200px"
                :src="pic"
                :preview-src-list="aftersalesInfo.evidence_pic"
              />
            </li>
          </ul>
          <span v-else>无图片信息</span>
        </el-col>
      </el-row>
    </div>

    <!-- 退款信息展示 -->
    <template v-if="refundInfo.refund_fee">
      <div class="section-header with-border">
        <h3>退款处理</h3>
      </div>
      <div class="section-body">
        <el-row>
          <el-col
            :span="3"
            class="col-3 content-right"
          >
            退款金额:
          </el-col>
          <el-col :span="20">
            {{ refundInfo.refund_fee }}
          </el-col>
        </el-row>
        <el-row>
          <el-col
            :span="3"
            class="col-3 content-right"
          >
            退款状态:
          </el-col>
          <el-col :span="20">
            <span v-if="refundInfo.refund_status == 'SUCCESS'"> 退款成功 </span>
            <span v-else-if="refundInfo.refund_status == 'REFUNDCLOSE'"> 退款关闭。 </span>
            <span v-else-if="refundInfo.refund_status == 'PROCESSING'"> 退款处理中 </span>
            <span v-else-if="refundInfo.refund_status == 'CHANGE'"> 退款异常 </span>
          </el-col>
        </el-row>
        <el-row v-if="refundInfo.refunds_memo">
          <el-col
            :span="3"
            class="col-3 content-right"
          >
            退款备注:
          </el-col>
          <el-col :span="20">
            {{ refundInfo.refunds_memo }}
          </el-col>
        </el-row>
      </div>
    </template>

    <!-- 用户回寄物流信息 -->
    <template v-if="aftersalesInfo.sendback_data">
      <div class="section-header with-border">
        <h3>用户回寄物流信息</h3>
      </div>
      <div class="section-body">
        <el-row>
          <el-col
            :span="3"
            class="col-3 content-right"
          >
            物流公司:
          </el-col>
          <el-col :span="20">
            {{ dlycorpskn[aftersalesInfo.sendback_data.corp_code] }}
          </el-col>
        </el-row>
        <el-row>
          <el-col
            :span="3"
            class="col-3 content-right"
          >
            物流单号:
          </el-col>
          <el-col :span="20">
            {{ aftersalesInfo.sendback_data.logi_no }}
          </el-col>
        </el-row>
      </div>
    </template>

    <!-- 商家发货物流信息 -->
    <template v-if="aftersalesInfo.sendconfirm_data">
      <div class="section-header with-border">
        <h3>商家发货物流信息</h3>
      </div>
      <div class="section-body">
        <el-row>
          <el-col
            :span="3"
            class="col-3 content-right"
          >
            物流公司:
          </el-col>
          <el-col :span="20">
            {{ dlycorpskn[aftersalesInfo.sendconfirm_data.corp_code] }}
          </el-col>
        </el-row>
        <el-row>
          <el-col
            :span="3"
            class="col-3 content-right"
          >
            物流单号:
          </el-col>
          <el-col :span="20">
            {{ aftersalesInfo.sendconfirm_data.logi_no }}
          </el-col>
        </el-row>
      </div>
    </template>

    <!-- 申请通过 -->
    <template v-if="aftersalesInfo.progress == '2'">
      <!-- 换货商家发货信息填写 -->
      <template v-if="aftersalesInfo.aftersales_type == 'EXCHANGING_GOODS'">
        <div class="section-header with-border">
          <h3>商家发货物流</h3>
        </div>
        <div>
          <el-row class="agreen-info">
            <el-col
              :span="2"
              class="col-3 agreen-right"
            >
              物流公司:
            </el-col>
            <el-col :span="8">
              <el-input
                v-model="corp_code"
                type="text"
                placeholder="请输入物流公司"
                clearable
              />
            </el-col>
          </el-row>
          <el-row>
            <el-col
              :span="2"
              class="col-3 agreen-right"
            >
              物流单号:
            </el-col>
            <el-col :span="8">
              <el-input
                v-model="logi_no"
                type="text"
                placeholder="请输入物流单号"
                clearable
              />
            </el-col>
          </el-row>
        </div>
        <div class="section-footer with-border content-center">
          <el-button
            type="primary"
            @click="sendBackAction"
          >
            确认发货
          </el-button>
        </div>
      </template>

      <!-- 退货退款审核 -->
      <template
        v-else-if="
          (aftersalesInfo.aftersales_type == 'REFUND_GOODS' &&
            aftersalesInfo.distributor_id == '0' &&
            VERSION_PLATFORM) ||
            (aftersalesInfo.aftersales_type == 'REFUND_GOODS' &&
              $store.getters.login_type == 'distributor' &&
              VERSION_PLATFORM) ||
            (aftersalesInfo.aftersales_type == 'REFUND_GOODS' && !VERSION_PLATFORM)
        "
      >
        <div class="section-header with-border">
          <h3>商家处理信息</h3>
        </div>
        <div class="section-body">
          <template>
            <el-row>
              <el-col
                :span="3"
                class="col-3 content-right"
              >
                处理结果:
              </el-col>
              <el-col :span="20">
                <template v-if="IsBind && login_type != 'distributor'">
                  <el-radio
                    v-model="check_refund"
                    label="0"
                    disabled
                  >
                    不同意
                  </el-radio>
                  <el-radio
                    v-model="check_refund"
                    label="1"
                    disabled
                  >
                    同意
                  </el-radio>
                </template>
                <template v-else>
                  <el-radio
                    v-model="check_refund"
                    label="0"
                  >
                    不同意
                  </el-radio>
                  <el-radio
                    v-model="check_refund"
                    label="1"
                  >
                    同意
                  </el-radio>
                </template>
              </el-col>
            </el-row>
            <template
              v-if="aftersalesInfo.aftersales_type != 'EXCHANGING_GOODS' && check_refund == '1'"
            >
              <el-row v-if="'point' != orderInfo.pay_type">
                <el-col
                  :span="3"
                  class="col-3 content-right"
                >
                  退款金额:
                </el-col>
                <el-col :span="8">
                  <el-input
                    v-model="refund_fee"
                    class="input-m"
                    type="number"
                    min="0"
                    :max="orderInfo.total_fee / 100"
                  />&nbsp;&nbsp;&nbsp;&nbsp;<span
                    v-if="aftersalesInfo.share_points > 0"
                  >返还积分：{{ aftersalesInfo.share_points }}</span>
                </el-col>
              </el-row>
              <el-row v-else>
                <el-col
                  :span="3"
                  class="col-3 content-right"
                >
                  退款积分:
                </el-col>
                <el-col :span="8">
                  <el-input
                    v-model="refund_point"
                    type="number"
                    min="0"
                    :max="orderInfo.point"
                  />
                </el-col>
              </el-row>
            </template>
            <el-row v-if="this.check_refund == '0'">
              <el-col
                :span="2"
                class="col-3 agreen-right"
              >
                拒绝原因:
              </el-col>
              <el-col :span="12">
                <el-input
                  v-model="refuse_reason"
                  type="textarea"
                  :rows="3"
                  placeholder="请输入拒绝原因"
                />
              </el-col>
            </el-row>
            <div class="section-footer with-border content-center">
              <el-button
                v-if="IsBind && login_type != 'distributor'"
                type="primary"
                disabled
                @click="refundAction"
              >
                确认
              </el-button>
              <el-button
                v-else
                type="primary"
                @click="refundAction"
              >
                确认
              </el-button>
            </div>
          </template>
        </div>
      </template>
    </template>
    <!-- 退货 -->
    <template
      v-if="
        (aftersalesInfo.distributor_id == '0' &&
          aftersalesInfo.progress == '0' &&
          VERSION_PLATFORM) ||
          ($store.getters.login_type == 'distributor' &&
            aftersalesInfo.progress == '0' &&
            VERSION_PLATFORM) ||
          (aftersalesInfo.progress == '0' && !VERSION_PLATFORM)
      "
    >
      <div class="section-header with-border">
        <h3>商家处理信息</h3>
      </div>
      <div class="section-body">
        <template>
          <el-row>
            <el-col
              :span="3"
              class="col-3 content-right"
            >
              处理结果:
            </el-col>
            <el-col :span="20">
              <template v-if="IsBind && login_type != 'distributor'">
                <el-radio
                  v-model="is_approved"
                  label="0"
                  disabled
                >
                  不同意
                </el-radio>
                <el-radio
                  v-model="is_approved"
                  label="1"
                  disabled
                >
                  同意
                </el-radio>
              </template>
              <template v-else>
                <el-radio
                  v-model="is_approved"
                  label="0"
                >
                  不同意
                </el-radio>
                <el-radio
                  v-model="is_approved"
                  label="1"
                >
                  同意
                </el-radio>
              </template>
            </el-col>
          </el-row>
          <template v-if="aftersalesInfo.aftersales_type == 'ONLY_REFUND' && is_approved == '1'">
            <el-row>
              <el-col
                :span="3"
                class="col-3 content-right"
              >
                退款金额:
              </el-col>
              <el-col :span="8">
                <el-input
                  v-model="refund_fee"
                  type="number"
                  min="0"
                  step="0.01"
                  :max="aftersalesInfo.refund_fee / 100"
                />
              </el-col>
            </el-row>
            <el-row>
              <el-col
                :span="3"
                class="col-3 content-right"
              >
                退款积分:
              </el-col>
              <el-col :span="8">
                <el-input
                  v-model="refund_point"
                  type="number"
                  min="0"
                  :max="aftersalesInfo.refund_point"
                />
              </el-col>
            </el-row>
          </template>
          <el-row v-if="this.is_approved == '0'">
            <el-col
              :span="2"
              class="col-3 agreen-right"
            >
              拒绝原因:
            </el-col>
            <el-col :span="12">
              <el-input
                v-model="refuse_reason"
                type="textarea"
                :rows="3"
                placeholder="请输入拒绝原因"
              />
            </el-col>
          </el-row>
        </template>
      </div>
    </template>

    <template v-if="aftersalesInfo.aftersales_type == 'REFUND_GOODS' && this.is_approved == '1'">
      <div class="section-header with-border">
        <h3>回寄信息</h3>
        <div class="section-body">
          <template>
            <el-row>
              <el-col
                :span="2"
                class="col-3 agreen-right"
              >
                售后地址:
              </el-col>
              <el-col :span="12">
                <el-button
                  icon="el-icon-circle"
                  @click="handleChangeAddress"
                >
                  选择售后地址
                </el-button>
                &nbsp;&nbsp;&nbsp;&nbsp;
                <el-button
                  icon="el-icon-circle"
                  @click="dialogVisible = true"
                >
                  新增售后地址
                </el-button>
              </el-col>
            </el-row>
            <el-row>
              <el-col
                :span="3"
                class="col-3 content-right"
              >
                联系人:
              </el-col>
              <el-col :span="8">
                <el-input
                  v-model="aftersales_contact"
                  type="text"
                  disabled
                />
              </el-col>
            </el-row>
            <el-row>
              <el-col
                :span="3"
                class="col-3 content-right"
              >
                电话:
              </el-col>
              <el-col :span="8">
                <el-input
                  v-model="aftersales_mobile"
                  type="text"
                  disabled
                />
              </el-col>
            </el-row>
            <el-row>
              <el-col
                :span="3"
                class="col-3 content-right"
              >
                回寄地址:
              </el-col>
              <el-col :span="8">
                <el-input
                  v-model="aftersales_address"
                  type="text"
                  disabled
                />
              </el-col>
            </el-row>
          </template>
        </div>
      </div>
    </template>

    <div
      v-if="
        (aftersalesInfo.distributor_id == '0' &&
          aftersalesInfo.progress == '0' &&
          VERSION_PLATFORM) ||
          ($store.getters.login_type == 'distributor' &&
            aftersalesInfo.progress == '0' &&
            VERSION_PLATFORM) ||
          (aftersalesInfo.progress == '0' && !VERSION_PLATFORM)
      "
      class="section-footer with-border content-center"
    >
      <el-button
        v-if="IsBind"
        type="primary"
        disabled
        @click="reviewSubmit"
      >
        提交审核
      </el-button>
      <el-button
        v-else
        type="primary"
        :disabled="submitDisabled"
        @click="reviewSubmit"
      >
        提交审核
      </el-button>
    </div>

    <el-dialog
      title="店铺售后地址列表"
      :visible.sync="relShop.relShopVisible"
      :before-close="handleCancel"
      width="50%"
    >
      <template>
        <el-table
          v-loading="loading"
          :data="relShop.list"
          @row-click="handleRowClick"
        >
          <el-table-column
            prop="address"
            label="售后地址"
            width="300"
          >
            <template slot-scope="scope">
              <i class="el-icon-place" />
              {{
                scope.row.province +
                  ' ' +
                  scope.row.city +
                  ' ' +
                  scope.row.area +
                  ' ' +
                  scope.row.address
              }}
            </template>
          </el-table-column>
          <el-table-column
            prop="mobile"
            label="联系人"
          >
            <template slot-scope="scope">
              <i class="el-icon-place" />
              {{ scope.row.contact + ' ' + scope.row.mobile }}
            </template>
          </el-table-column>
          <el-table-column
            prop="address_id"
            label="选择"
          >
            <template slot-scope="scope">
              <el-radio
                v-model="aftersales_select"
                :label="scope.row.address_id"
              />
            </template>
          </el-table-column>
        </el-table>
        <div
          v-if="relShop.total_count > relShop.params.page_size"
          class="content-center content-top-padded"
        >
          <el-pagination
            layout="prev, pager, next"
            :current-page.sync="relShop.params.page"
            :total="relShop.total_count"
            :page-size="relShop.params.page_size"
            @current-change="handleCurrentChange"
          />
        </div>
        <span
          slot="footer"
          class="dialog-footer"
        >
          <el-button @click="handleCancel">取 消</el-button>
          <!-- <el-button @click="relShop.relShopVisible = false">取 消</el-button> -->
          <el-button
            type="primary"
            @click="handleSelectAftersalesAddress()"
          >确 定</el-button>
        </span>
      </template>
    </el-dialog>
    <!-- 新增售后地址 -->
    <el-dialog
      title="新增售后地址"
      :visible.sync="dialogVisible"
      :before-close="handleClose"
      width="45%"
    >
      <el-form
        ref="dataForm"
        :model="dataForm"
        label-width="100px"
      >
        <el-form-item label="店铺">
          <template>
            <span>{{ aftersalesInfo.distributor_id }}</span>
          </template>
        </el-form-item>
        <el-form-item label="售后地址">
          <el-cascader
            v-model="form.regions_id"
            class="w-100"
            :options="regions"
            @change="handleRegionChange"
          />
        </el-form-item>
        <el-form-item label="详细地址">
          <el-input
            v-model="aftersales_address"
            placeholder=""
          />
        </el-form-item>
        <el-form-item label="联系人">
          <el-input
            v-model="aftersales_contact"
            placeholder=""
          />
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input
            v-model="aftersales_mobile"
            placeholder=""
          />
        </el-form-item>
      </el-form>
      <span
        slot="footer"
        class="dialog-footer"
      >
        <el-button @click="handleClose">取 消</el-button>
        <el-button
          type="primary"
          @click="createAddress"
        >确 定</el-button>
      </span>
    </el-dialog>
    <RemarkModal
      ref="modalRef"
      @onDone="onRemarksDone"
    />
  </div>
</template>
<style scoped lang="scss" type="text/css">
.flex {
  display: flex;
  align-items: center;
}

h3.title {
  padding: 20px;
  font-size: 18px;
  color: #ff5000;
}
.col-3 {
  width: 120px;
  margin-right: 10px;
}
.detail-info {
  margin: 0 10px;
}
.el-row {
  margin-bottom: 10px;
}
img {
  max-width: 100px;
  margin: 0 auto;
}
.agreen-info {
  margin-top: 20px;
}
.agreen-right {
  line-height: 40px;
  text-align: right;
}
.remark_footer {
  border-top: 1px solid #efefef;
  padding: 12.5px;
  display: flex;

  &_title {
    text-align: right;
  }
  &_content {
  }
}
</style>

<script>
import {
  getAftersalesDetail,
  reviewAftersales,
  refundCheck,
  sendConfirm,
  getAftersalesAddressList,
  createAftersalesAddress
} from '../../../api/aftersales'
import { isBind } from '../../../api/trade'
import hqbdlycorp_kname from '../../../common/hqbdlycorp_kname.json'
import district from '../../../common/district.json'
import RemarkModal from '@/components/remarkModal'
import remarkMixin from '@/mixins/remarkMixin'

import { mapGetters } from 'vuex'

// 取选中地区的值
function getCascaderObj (val, opt) {
  return val.map(function (value, index, array) {
    for (var itm of opt) {
      if (itm.value === value) {
        opt = itm.children
        return itm
      }
    }
    return null
  })
}

export default {
  components: {
    RemarkModal
  },
  mixins: [remarkMixin],
  data () {
    return {
      aftersales_bn: '',
      aftersalesInfo: {},
      refundInfo: {},
      // orderInfo: {},
      distributorInfo: {},
      radio: '1',
      agreen: false,
      loading: false,
      IsBind: false,
      order_id: '',
      // tradeInfo: {},
      resource: '',
      user_id: 0,
      memberInfo: {
        gradeInfo: {}
      },
      dlycorpskn: hqbdlycorp_kname,
      is_approved: '0',
      check_refund: '0',
      refuse_reason: '',
      refund_fee: 0,
      refund_point: 0,
      corp_code: '', // 物流公司
      logi_no: '', // 快递单号
      reviewData: {},
      refundData: {},
      exchangeSendBack: {},
      submitDisabled: false,

      dialogVisible: false,
      relShop: {
        list: [],
        total_count: 0,
        params: {
          page: 1,
          page_size: 20
        },
        relShopVisible: false
      },
      distributor_id: 0,
      aftersales_address_id: '',
      aftersales_contact: '',
      aftersales_mobile: '',
      aftersales_address: '',
      aftersales_select: null,
      regionList: [],
      dataForm: {},
      form: {
        regions_id: []
      },
      regions: district
    }
  },
  computed: {
    ...mapGetters(['login_type'])
  },
  mounted () {
    if (this.$route.query.aftersales_bn) {
      this.aftersales_bn = this.$route.query.aftersales_bn
    }
    this.loading = true
    this.getStatus()
    this.aftersaleInfo()
  },
  methods: {
    aftersaleInfo () {
      getAftersalesDetail(this.aftersales_bn).then((response) => {
        let data = response.data.data
        this.aftersalesInfo = data
        this.orderInfo = data.order_info
        // this.distributorInfo = data.distributorInfo
        // this.tradeInfo = data.tradeInfo
        this.refund_fee = data.refund_fee / 100
        this.refund_point = data.refund_point
        // this.refund_point = this.orderInfo.items[0].point
        // if(data.refundInfo) {
        //   this.refundInfo = data.refundInfo
        // }
        if (data.sendback_data.length == 0) {
          this.aftersalesInfo.sendback_data = null
        }
        if (data.sendconfirm_data.length == 0) {
          this.aftersalesInfo.sendconfirm_data = null
        }
        this.distributor_id = data.distributor_id
        this.loading = false
      })
    },
    onRemarksDone (remark) {
      this.aftersalesInfo.distributor_remark = remark
    },
    reviewSubmit () {
      this.reviewData.aftersales_bn = this.aftersales_bn
      this.reviewData.is_approved = this.is_approved
      if (this.is_approved == 1) {
        if (this.refund_fee < 0) {
          this.$message.error('退款金额必填！')
          return false
        } else if (this.refund_fee > this.aftersalesInfo.refund_fee) {
          this.$message.error('退款金额不能大于订单金额！')
          return false
        }
        this.reviewData.refund_fee = accMul(this.refund_fee, 100)
        //parseInt(this.refund_fee * 100)
        this.reviewData.refund_point = this.refund_point
        //售后地址
        if (
          this.aftersalesInfo.aftersales_type == 'REFUND_GOODS' &&
          this.aftersales_address_id == ''
        ) {
          this.$message.error('请选择售后地址！')
          return false
        }
        this.reviewData.aftersales_address_id = this.aftersales_address_id
      } else {
        if (this.refuse_reason) {
          this.reviewData.refuse_reason = this.refuse_reason
        } else {
          this.$message.error('拒绝原因必填！')
          return false
        }
      }
      this.submitDisabled = true
      setTimeout(() => {
        this.submitDisabled = false
      }, 1000)
      reviewAftersales(this.reviewData).then((response) => {
        if (response.data.data) {
          this.$message({
            message: '提交审核成功！',
            type: 'success'
          })
          this.aftersaleInfo()
        } else {
          this.$message.error('提交审核失败')
          return false
        }
      })
    },
    sendBackAction () {
      this.exchangeSendBack.aftersales_bn = this.aftersales_bn
      this.exchangeSendBack.corp_code = this.corp_code
      this.exchangeSendBack.logi_no = this.logi_no
      sendConfirm(this.reviewData).then((response) => {
        if (response.data.data) {
          this.$message({
            message: '重新发货成功！',
            type: 'success'
          })
          this.aftersaleInfo()
        } else {
          this.$message.error('重新发货失败！')
          return false
        }
      })
    },
    refundAction () {
      this.refundData.aftersales_bn = this.aftersales_bn
      this.refundData.check_refund = this.check_refund
      this.refundData.refunds_memo = this.refuse_reason
      this.refundData.refund_fee = accMul(this.refund_fee, 100)
      this.refundData.refund_point = this.refund_point
      if (this.refundData.check_refund == '0' && !this.refundData.refund_memo) {
        this.$message.error('拒绝原因必填！')
        return false
      }
      if (this.refundData.check_refund == '1' && !this.refundData.refund_fee) {
        this.$message.error('退款金额必填！')
        return false
      }
      refundCheck(this.refundData).then((response) => {
        if (response.data.data) {
          this.$message({
            message: '操作成功！',
            type: 'success'
          })
          this.aftersaleInfo()
        } else {
          this.$message.error('退款失败')
          return false
        }
      })
    },

    getStatus () {
      isBind().then((response) => {
        this.IsBind = response.data.data.result
      })
    },

    handleChangeAddress (val) {
      this.relShop.relShopVisible = true
      const queryData = {}
      queryData['page'] = this.relShop.params.page
      queryData['page_size'] = this.relShop.params.page_size
      queryData['distributor_id'] = this.distributor_id
      this.getAftersalesaddressList(queryData)
    },
    handleCurrentChange (val) {
      const queryData = {}
      queryData['page'] = val
      queryData['page_size'] = this.relShop.params.page_size
      queryData['distributor_id'] = this.distributor_id
      this.getAftersalesaddressList(queryData)
    },
    getAftersalesaddressList (queryData) {
      getAftersalesAddressList(queryData).then((response) => {
        if (response.data.data.list) {
          this.relShop.list = response.data.data.list
          this.relShop.total_count = response.data.data.total_count
        }
      })
    },
    handleCancel () {
      this.relShop.relShopVisible = false
    },
    handleSelectAftersalesAddress () {
      const fd = this.relShop.list.find((item) => item.address_id == this.aftersales_select)
      if (fd) {
        this.aftersales_address_id = fd.address_id
        this.aftersales_contact = fd.contact
        this.aftersales_mobile = fd.mobile
        this.aftersales_address = fd.province + fd.city + fd.area + fd.address
      }
      this.relShop.relShopVisible = false
    },
    handleRowClick (row, col, event) {
      this.aftersales_select = row.address_id
    },
    handleClose () {
      this.dialogVisible = false
    },
    handleRegionChange: function (value) {
      var vals = getCascaderObj(value, this.regions)
      this.form.regions_id = []
      this.form.regions = []
      for (var i = 0; i < vals.length; i++) {
        this.form.regions_id[i] = vals[i].value
        this.form.regions[i] = vals[i].label
      }

      if (
        this.form.regions[0] == '北京市' ||
        this.form.regions[0] == '天津市' ||
        this.form.regions[0] == '上海市' ||
        this.form.regions[0] == '重庆市'
      ) {
        this.searchRegion = this.form.regions[0] + this.form.regions[2]
      } else {
        this.searchRegion = this.form.regions[0] + this.form.regions[1] + this.form.regions[2]
      }
    },
    createAddress () {
      const queryData = {}
      const ids = []
      if (this.aftersalesInfo.distributor_id) {
        ids.push(this.aftersalesInfo.distributor_id)
      }
      queryData['distributor_id'] = JSON.stringify(ids)
      queryData['regions_id'] = JSON.stringify(this.form.regions_id)
      queryData['regions'] = JSON.stringify(this.form.regions)
      queryData['province'] = this.form.regions[0]
      queryData['city'] = this.form.regions[1]
      queryData['area'] = this.form.regions[2]
      queryData['address'] = this.aftersales_address
      queryData['contact'] = this.aftersales_contact
      queryData['mobile'] = this.aftersales_mobile
      createAftersalesAddress(queryData).then((response) => {
        if (response.data.data.status) {
          this.$message({
            message: '添加成功',
            type: 'success'
          })
          const fd = response.data.data.result
          // console.log(fd)
          this.aftersales_address_id = fd.address_id
          this.aftersales_contact = fd.contact
          this.aftersales_mobile = fd.mobile
          this.aftersales_address = fd.province + fd.city + fd.area + fd.address
        } else {
          this.$message({
            message: '添加失败',
            type: 'error'
          })
        }
        this.dialogVisible = false
      })
    }
  }
}

// 两个浮点数相乘
function accMul (num1, num2) {
  var m = 0,
    s1 = num1.toString(),
    s2 = num2.toString()
  try {
    m += s1.split('.')[1].length
  } catch (e) {}
  try {
    m += s2.split('.')[1].length
  } catch (e) {}
  return (Number(s1.replace('.', '')) * Number(s2.replace('.', ''))) / Math.pow(10, m)
}
</script>
