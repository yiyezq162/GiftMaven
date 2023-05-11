import request from '@/utils/request'

export default {
  getGiftsList(searchModel) {
    return request({
      url: '/gifts/list',
      method: 'get',
      params: {
        page: searchModel.pageNo,
        pageSize: searchModel.pageSize,
        username: searchModel.username
      }
    })
  },
  savaGift(gift) {
    return request({
      url: '/gifts',
      method: 'post',
      data: gift
    })
  },
  deleteGiftById(id) {
    return request({
      url: '/gifts/' + id,
      method: 'delete'
    })
  },
  getGiftById(id) {
    return request({
      url: '/gifts/' + id,
      method: 'get'
    })
  }
}
