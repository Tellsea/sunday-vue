import request from '@/utils/request'

const systemLog = {
  listByTable(data) {
    return request.post('/system/systemLog/listByTable', data)
  },
  delete(data) {
    return request.post('/system/systemLog/delete', data)
  },
}

export default systemLog
