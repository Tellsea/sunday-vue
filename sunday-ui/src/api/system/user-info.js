import request from '@/utils/request'

const roleInfo = {
  listByTable(data) {
    return request.post('/system/userInfo/listByTable', data)
  },
  save(data) {
    return request.post('/system/userInfo/save', data)
  },
  getById(data) {
    return request.post('/system/userInfo/getById', data)
  },
  update(data) {
    return request.post('/system/userInfo/update', data)
  },
  updateStatus(data) {
    return request.post('/system/userInfo/updateStatus', data)
  },
  testException() {
    return request.get('/system/userInfo/testException')
  }
}

export default roleInfo
