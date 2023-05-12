import request from '@/utils/request'

export default {
  getCustomerList(searchModel) {
    return request({
      url: '/customer/list',
      method: 'get',
      params: {
        page: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        name: searchModel.name
      }
    })
  },
  saveCustomer(customer) {
    return request({
      url: '/customer',
      method: 'post',
      data: customer
    })
  },
  deleteCustomerById(id) {
    return request({
      url: '/customer/' + id,
      method: 'delete'
    })
  },
  getCustomerById(id) {
    return request({
      url: '/customer/' + id,
      method: 'get'
    })
  }
}
