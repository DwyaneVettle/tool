import log from './log'
import CommonUtil from '@/common/js/util'
import store from '@/store'

const isPrimitiveType = (val, type) => Object.prototype.toString.call(val) === type

export function isFunction (val) {
  return isPrimitiveType(val, '[object Function]')
}

export function isNumber (val) {
  return isPrimitiveType(val, '[object Number]')
}

export function isObject (val) {
  return isPrimitiveType(val, '[object Object]')
}

export function isArray (val) {
  return Array.isArray(val)
}

export function isBoolean (val) {
  return isPrimitiveType(val, '[object Boolean]')
}

// 云店
export const VERSION_STANDARD = store.getters.versionMode == 'standard'
// ecshopx
export const VERSION_PLATFORM = store.getters.versionMode == 'platform'
// 官方商城
export const VERSION_B2C = store.getters.versionMode == 'b2c'
// 内购
export const VERSION_IN_PURCHASE = store.getters.versionMode == 'in_purchase'

export function isInSalesCenter () {
  if (window.self != window.top && window.self.location.href.indexOf('iframeLogin') < 0) {
    return true
  } else {
    false
  }
}

export function isInMerchant () {
  return /\/merchant/.test(window.location.pathname)
}

export function importAll (r, fn = (key, r) => r(key)) {
  r.keys().forEach((key) => fn(key, r))
}

export function rand (max) {
  return Math.floor(Math.random() * max)
}
//getRandwords 获取随机字符串
export function getRandwords (ls = 8) {
  var chars = ''
  var passwords = []
  var passwordUnique = true
  var quantity = 1
  chars += '0123456789'
  chars += 'abcdefghijklmnopqrstuvwxyz'
  chars += 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
  while (passwords.length < quantity) {
    var _chars = chars.split('')
    var password = ''
    for (var i = 0, l = ls; i < l; i++) {
      if (_chars.length < 1) break

      var idx = rand(_chars.length)
      password += _chars[idx]
      if (passwordUnique) _chars.splice(idx, 1)
    }
    if (passwords.indexOf(password) === -1) passwords.push(password)
  }
  return passwords.join('\n')
}

export function dateFilter (time, pattern = 'yyyy-MM-dd hh:mm:ss') {
  console.log('dateFilter', time)
  if (time !== -1) {
    return CommonUtil.formatDate.format(new Date(time * 1000), pattern)
  } else {
    let timestamp = Date.parse(new Date()) / 1000
    return CommonUtil.formatDate.format(new Date(timestamp * 1000), pattern)
  }
}

// 时间戳转日期格式
export function timestampToTime (timestamp) {
  var date = new Date(timestamp * 1000) //时间戳为10位需*1000，时间戳为13位的话不需乘1000
  var YY = date.getFullYear() + '-'
  var MM = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
  var DD = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
  var hh = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':'
  var mm = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':'
  var ss = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
  return YY + MM + DD + ' ' + hh + mm + ss
}

export function goLink () {
  window.parent.postMessage(
    {
      cmd: 'notExistAuth'
    },
    '*'
  )
}

export function getPropByPath (obj, path, strict) {
  let tempObj = obj
  path = path.replace(/\[(\w+)\]/g, '.$1')
  path = path.replace(/^\./, '')

  let keyArr = path.split('.')
  let i = 0
  for (let len = keyArr.length; i < len - 1; ++i) {
    if (!tempObj && !strict) break
    let key = keyArr[i]
    if (key in tempObj) {
      tempObj = tempObj[key]
    } else {
      if (strict) {
        throw new Error('please transfer a valid prop path to form item!')
      }
      break
    }
  }
  return {
    o: tempObj,
    k: keyArr[i],
    v: tempObj ? tempObj[keyArr[i]] : null
  }
}

function export_open (tab) {
  setTimeout(() => {
    const login_type = store.getters.login_type
    if (login_type == 'distributor') {
      window.open(`/shopadmin/shopsetting/baseexport?tab=${tab}`)
    } else {
      window.open(`/setting/baseexport?tab=${tab}`)
    }
  }, 1000)
}

export function unescape (html) {
  /* eslint-disable */
  return html
    .replace(html ? /&(?!#?\w+;)/g : /&/g, '&amp;')
    .replace(/&lt;/g, '<')
    .replace(/&gt;/g, '>')
    .replace(/&quot;/g, '"')
    .replace(/&#39;/g, "'")
    .replace(/&amp;/g, '&')
    .replace(/&nbsp;/g, ' ')
    .replace(/&#8230;/g, '…')
  /* eslint-enable */
}

export { log, export_open }

export default {}
