<template>
  <div class="section-white content-padded">
    <el-row :gutter="20">
      <el-col :span="6">
        <el-date-picker
          v-model="created"
          value-format="yyyy/MM/dd"
          type="daterange"
          placeholder="选择日期范围"
          style="width: 100%"
          @change="dateChange"
        />
      </el-col>
      <el-col :span="6">
        <el-input
          v-model="mobile"
          placeholder="手机号"
        >
          <el-button
            slot="append"
            icon="el-icon-search"
            @click="numberSearch"
          />
        </el-input>
      </el-col>
    </el-row>
    <div class="record-list">
      <el-table
        v-loading="loading"
        :data="recordList"
        :height="wheight - 250"
        @filter-change="filterTag"
      >
        <el-table-column
          prop="timeStart"
          label="创建时间"
        >
          <template slot-scope="scope">
            <span>{{ scope.row.created | datetime('YYYY-MM-DD HH:mm:ss') }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="point"
          label="积分变动"
        >
          <template slot-scope="scope">
            <span v-if="scope.row.point == 0">{{ scope.row.point }}</span>
            <span
              v-else-if="scope.row.point > 0 && scope.row.outin_type == 'in'"
            >+{{ scope.row.point }}</span>
            <span
              v-else-if="scope.row.point > 0 && scope.row.outin_type == 'out'"
            >-{{ scope.row.point }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="point_desc"
          label="记录"
        />
        <el-table-column
          prop="order_id"
          label="订单号"
        />
      </el-table>
    </div>
    <div
      v-if="total_count > pageSize"
      class="tc"
      style="margin-top: 20px"
    >
      <el-pagination
        layout="prev, pager, next"
        :current-page.sync="params.page"
        :total="total_count"
        :page-size="pageSize"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { getMemberPoint } from '../../../api/point'
export default {
  props: ['getStatus'],
  data () {
    return {
      loading: false,
      created: '',
      mobile: '',
      total_count: 0,
      pageSize: 20,
      recordList: [],
      params: {
        page: 1
      },
      date_begin: '',
      date_end: ''
    }
  },
  computed: {
    ...mapGetters(['wheight'])
  },
  watch: {
    getStatus (newVal, oldVal) {
      if (newVal) {
        let query = { pageSize: this.pageSize, page: 1 }
        this.getList(query)
      }
    }
  },
  methods: {
    filterTag (val) {
      this.params.page = 1
      this.getParams()
      this.getList(this.params)
    },
    numberSearch (e) {
      this.params.page = 1
      this.getParams()
      this.getList(this.params)
    },
    dateChange (val) {
      if (val.length > 0) {
        this.date_begin = this.dateStrToTimeStamp(val[0] + ' 00:00:00')
        this.date_end = this.dateStrToTimeStamp(val[1] + ' 23:59:59')
      } else {
        this.date_begin = ''
        this.date_end = ''
      }
      this.params.page = 1
      this.getParams()
      this.getList(this.params)
    },
    handleCurrentChange (val) {
      this.params.page = val
      this.params.pageSize = this.pageSize
      this.getList(this.params)
    },
    getList (query) {
      this.loading = true
      getMemberPoint(query).then((res) => {
        this.recordList = res.data.data.list
        this.total_count = res.data.data.total_count
        this.loading = false
      })
    },
    getParams () {
      this.params.date_begin = this.date_begin
      this.params.date_end = this.date_end
      this.params.mobile = this.mobile
    },
    dateStrToTimeStamp (str) {
      return Date.parse(new Date(str)) / 1000
    }
  }
}
</script>
<style type="text/css">
.record-list .el-table .cell,
.record-list .el-table th > div {
  padding-left: 10px;
  padding-right: 10px;
}
</style>
