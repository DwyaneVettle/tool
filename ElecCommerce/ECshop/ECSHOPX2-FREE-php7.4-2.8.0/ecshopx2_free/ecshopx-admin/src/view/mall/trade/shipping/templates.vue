<template>
  <div>
    <div v-if="$route.path.indexOf('editor') === -1">
      <div class="action-container">
        <el-button
          type="primary"
          icon="plus"
          @click="addTemplates"
        >
          新增运费模板
        </el-button>
      </div>
      <el-tabs
        v-model="activeName"
        type="card"
        @tab-click="handleClick"
      >
        <el-tab-pane
          label="卖家承担运费"
          name="first"
        >
          <buyerTemplates
            ref="buyerTemplates"
            :get-status="buyerTemplates"
          />
        </el-tab-pane>
        <el-tab-pane
          label="按重量运费模板"
          name="second"
        >
          <weightTemplates :get-status="weightTemplates" />
        </el-tab-pane>
        <el-tab-pane
          label="按件数运费模板"
          name="third"
        >
          <numberTemplates :get-status="numberTemplates" />
        </el-tab-pane>
        <el-tab-pane
          label="按金额运费模板"
          name="fourth"
        >
          <priceTemplates :get-status="priceTemplates" />
        </el-tab-pane>
        <el-tab-pane
          label="按体积运费模板"
          name="fifth"
        >
          <volumeTemplates :get-status="volumeTemplates" />
        </el-tab-pane>
      </el-tabs>
    </div>
    <router-view />
  </div>
</template>

<script>
import buyerTemplates from './templates/buyerTemplates'
import weightTemplates from './templates/weightTemplates'
import numberTemplates from './templates/numberTemplates'
import priceTemplates from './templates/priceTemplates'
import volumeTemplates from './templates/volumeTemplates'

export default {
  provide () {
    return {
      refresh: this.getList
    }
  },
  components: {
    buyerTemplates,
    weightTemplates,
    numberTemplates,
    priceTemplates,
    volumeTemplates
  },
  data () {
    return {
      loading: false,
      buyerTemplates: false,
      weightTemplates: false,
      numberTemplates: false,
      priceTemplates: false,
      volumeTemplates: false,
      activeName: 'first',
      list: []
    }
  },
  mounted () {
    if ('undefined' != typeof this.$route.query.activeName) {
      this.activeName = this.$route.query.activeName
    }
  },
  methods: {
    addTemplates () {
      // 添加运费模板
      this.$router.push({ path: this.matchHidePage('editor') })
    },
    handleClick (tab, event) {
      if (this.activeName === 'first') {
        this.buyerTemplates = true
      } else if (this.activeName === 'second') {
        this.weightTemplates = true
      } else if (this.activeName === 'third') {
        this.numberTemplates = true
      } else if (this.activeName === 'fourth') {
        this.priceTemplates = true
      } else if (this.activeName === 'fifth') {
        this.volumeTemplates = true
      }
    },
    getList () {
      this.$refs.getShippingTemplatesList
    }
  }
}
</script>

<style scoped></style>
