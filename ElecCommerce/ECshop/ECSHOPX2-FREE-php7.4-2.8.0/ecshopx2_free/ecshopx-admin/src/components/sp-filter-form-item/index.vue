<style lang="scss" scope>
.sp-filter-form-item {
  margin: 0 8px 16px 0;
  &.label {
    .form-item__label {
      display: block;
    }
    .form-item__content {
      margin-left: 90px;
    }
  }
}
.form-item {
  &__label {
    float: left;
    width: 90px;
    line-height: 36px;
    padding-right: 8px;
    text-align: right;
    color: #666;
    display: none;
  }
  &__content {
    margin-left: 0;
    &.mini {
      width: 214px;
    }
    &.max {
      width: 526px;
    }
    .el-select,
    .el-cascader,
    .el-autocomplete {
      width: 100%;
    }
    .el-radio-group {
      line-height: 36px;
      height: 36px;
      display: flex;
      align-items: center;
    }
    .el-date-editor {
      width: 100%;
      padding: 0 9px !important;
      .el-range-separator {
        width: 30px;
        color: #999;
        position: relative;
        top: 1px;
      }
      .el-range__icon {
        display: none !important;
      }
      .el-range-input {
        width: 82px;
      }
    }
    .el-date-editor--datetimerange {
      .el-range-input {
        width: 140px;
      }
    }
  }
}
</style>
<template>
  <div
    class="sp-filter-form-item"
    :class="{ 'label': label }"
  >
    <div class="form-item__label">
      {{ label }}
    </div>
    <div
      class="form-item__content"
      :class="size"
    >
      <slot />
    </div>
  </div>
</template>

<script>
import emitter from '@/utils/emitter'
import { getPropByPath } from '@/utils'
export default {
  name: 'SpFilterFormItem',
  mixins: [emitter],
  props: {
    label: String,
    prop: String,
    size: {
      type: String,
      default: 'mini'
    }
  },
  inject: ['filterForm'],
  data () {
    return {}
  },
  computed: {
    fieldValue () {
      const model = this.filterForm.model
      if (!model || !this.prop) {
        return
      }

      let path = this.prop
      if (path.indexOf(':') !== -1) {
        path = path.replace(/:/, '.')
      }
      return getPropByPath(model, path, true).v
    }
  },
  created () {},
  mounted () {
    this.dispatch('SpFilterForm', 'sp.filterForm.addField', [this])
    let initialValue = this.fieldValue
    if (Array.isArray(initialValue)) {
      initialValue = [].concat(initialValue)
    }
    Object.defineProperty(this, 'initialValue', {
      value: initialValue
    })
  },
  methods: {
    resetField () {
      let model = this.filterForm.model
      let value = this.fieldValue
      let path = this.prop
      if (path.indexOf(':') !== -1) {
        path = path.replace(/:/, '.')
      }

      let prop = getPropByPath(model, path, true)

      if (Array.isArray(value)) {
        prop.o[prop.k] = [].concat(this.initialValue)
      } else {
        prop.o[prop.k] = this.initialValue
      }
    }
  }
}
</script>
