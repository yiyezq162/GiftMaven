import request from '@/utils/request'

export default {
  getOrderList(searchModel) {
    return request({
      url: '/order/list',
      method: 'get',
      params: {
        page: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        id: searchModel.id
      }
    })
  },
  saveOrder(admin) {
    return request({
      url: '/order',
      method: 'post',
      data: admin
    })
  },
  deleteOrderById(id) {
    return request({
      url: '/order/' + id,
      method: 'delete'
    })
  },
  getOrderById(id) {
    return request({
      url: '/order/' + id,
      method: 'get'
    })
  },
  getOrderProduct(id) {
    return request({
      url: '/product/order/' + id,
      method: 'get'
    })
  },
  saveOrderProduct(orderProductList) {
    return request({
      url: '/product',
      method: 'post',
      data: orderProductList
    })
  },
  deleteOrderProduct(orderProductId) {
    return request({
      url: '/product/' + orderProductId,
      method: 'delete'
    })
  }
}
