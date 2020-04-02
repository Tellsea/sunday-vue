import request from '@/utils/request'

const loginLog = {
  listByTable(data) {
    return request.post('/system/loginLog/listByTable', data)
  },
  deleteByIds(data) {
    return request.post('/system/loginLog/deleteByIds', data)
  },
}

export default loginLog
