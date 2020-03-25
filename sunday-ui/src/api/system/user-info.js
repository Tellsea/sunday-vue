import request from '@/utils/request'

const roleInfo = {
  listByTable(data) {
    return request.post('/system/userInfo/listByTable', data)
  },
  save(data) {
    return request.post('/system/userInfo/save', data)
  },
  update(data) {
    return request.post('/system/userInfo/update', data)
  },
  updateStatus(data) {
    return request.post('/system/userInfo/updateStatus', data)
  }
}

export default roleInfo
