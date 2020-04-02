import request from '@/utils/request'

const resourceInfo = {
  listByTree(data) {
    return request.post('/system/resourceInfo/listByTree', data)
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
