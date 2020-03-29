import request from '@/utils/request'

const resourceInfo = {
  listByTree(data) {
    return request.get('/system/resourceInfo/listByTree', data)
  },
  listByTable(data) {
    return request.post('/system/resourceInfo/listByTable', data)
  },
  save(data) {
    return request.post('/system/resourceInfo/save', data)
  },
  update(data) {
    return request.post('/system/resourceInfo/update', data)
  },
  deleteById(data) {
    return request.post('/system/resourceInfo/deleteById', data)
  }
}

export default resourceInfo
