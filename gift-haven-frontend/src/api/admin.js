import request from '@/utils/request'

export default {
  getUserList(searchModel) {
    return request({
      url: '/admin/list',
      method: 'get',
      params: {
        page: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        username: searchModel.username
      }
    })
  },
  saveUser(admin) {
    return request({
      url: '/admin',
      method: 'post',
      data: admin
    })
  },
  deleteUserById(id) {
    return request({
      url: '/admin/' + id,
      method: 'delete'
    })
  },
  getUserById(id) {
    return request({
      url: '/admin/' + id,
      method: 'get'
    })
  }
}
