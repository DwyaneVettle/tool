import req from './req'

//获取我的信息
export function getMyinfo(params) {
  return req.get('/operator/getinfo', params)
}

// 更改用户名
export function updateInfo(params) {
  return req.put('/operator/updatedata', params)
}
