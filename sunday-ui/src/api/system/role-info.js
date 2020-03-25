import request from '@/utils/request'

const roleInfo = {
  listByTable(data) {
    return request.post('/system/roleInfo/listByTable', data)
  },
  save(data) {
    return request.post('/system/roleInfo/save', data)
  },
  update(data) {
    return request.post('/system/roleInfo/update', data)
  },
  deleteById(data) {
    return request.post('/system/roleInfo/deleteById', data)
  }
}

export default roleInfo
