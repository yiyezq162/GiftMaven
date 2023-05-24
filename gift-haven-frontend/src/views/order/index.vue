<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <el-input
            v-model="searchModel.id"
            placeholder="订单号"
            clearable
          />
          <el-button
            type="primary"
            round
            icon="el-icon-search"
            @click="getOrderList"
          >查询
          </el-button>
        </el-col>
        <el-col :span="4" align="right">
          <el-button
            type="primary"
            icon="el-icon-plus"
            circle
            @click="openEditUI(null)"
          />
        </el-col>
      </el-row>
    </el-card>

    <!-- 结果列表 -->
    <el-card>
      <el-table :data="orderList" stripe style="width: 100%">
        <el-table-column label="#" type="index" width="80" />
        <el-table-column prop="orderId" label="订单号" width="180" />
        <el-table-column prop="customerEntity.name" label="客户名" width="180" />
        <el-table-column :formatter="dateFormat" prop="createAt" label="创建时间" width="180" />
        <el-table-column :formatter="dateFormat" prop="completedAt" label="完成时间" width="180" />
        <el-table-column prop="orderStatus" label="状态" width="180" />
        <el-table-column prop="name" label="收货人" width="180" />
        <el-table-column label="收货地址" width="180">
          <template slot-scope="scope">
            <el-popover
              placement="top-start"
              width="400"
              trigger="hover"
              :content="scope.row.region"
            >
              <div slot="reference">{{ scope.row.address }}</div>
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button
              type="info"
              icon="el-icon-collection"
              size="mini"
              circle
              @click="openDetailsUI(scope.row.orderId)"
            />
            <el-button type="primary" icon="el-icon-edit" size="mini" circle @click="openEditUI(scope.row.orderId)" />
            <el-button type="danger" icon="el-icon-delete" size="mini" circle @click="deleteOrder(scope.row)" />
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-pagination
      :current-page="searchModel.pageNo"
      :page-sizes="[5, 10, 20, 50]"
      :page-size="searchModel.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />

    <!--    编辑信息弹出框-->
    <el-dialog
      :title="title"
      :visible.sync="dialogFormVisible"
      @close="clearForm"
    >
      <el-form ref="orderFormRef" :model="orderForm" :rules="rules">
        <el-form-item label="收件人" :label-width="formLabelWidth">
          <el-input v-model="orderForm.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="顾客id" :label-width="formLabelWidth">
          <el-input v-model="orderForm.customerId" autocomplete="off" />
        </el-form-item>
        <el-form-item label="订单状态" :label-width="formLabelWidth">
          <template>
            <el-select v-model="orderForm.orderStatus" placeholder="请选择" style="width: 100%">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </template>
        </el-form-item>
        <el-form-item label="地区" :label-width="formLabelWidth">
          <el-cascader
            v-model="orderForm.address"
            :placeholder="orderForm.address"
            style="width: 100%"
            size="large"
            :options="pcaTextArr"
          />
        </el-form-item>
        <el-form-item label="详细地址" :label-width="formLabelWidth">
          <el-input v-model="orderForm.region" autocomplete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrder">确 定</el-button>
      </div>
    </el-dialog>

    <!--订单详情-->
    <el-dialog
      title="订单详情"
      :visible.sync="dialogOrderListFormVisible"
      @close="clearForm"
    >
      <el-table
        :data="orderData"
        style="width: 100%"
      >

        <el-table-column label="礼品编号" width="180">
          <template slot-scope="scope">
            <el-input v-model="scope.row.giftId" autocomplete="off" />
          </template>
        </el-table-column>
        <el-table-column label="礼品名称" width="180">
          <template slot-scope="scope">
            <el-input v-model="scope.row.giftsEntity.name" :disabled="true" autocomplete="off" />
          </template>
        </el-table-column>
        <el-table-column label="个数" width="180">
          <template slot-scope="scope">
            <el-input v-model="scope.row.number" autocomplete="off" />
          </template>
        </el-table-column>
        <el-table-column label="价格" width="180">
          <template slot-scope="scope">
            <div slot="reference">{{ calcResult(scope.row.number, scope.row.giftsEntity.price) }}</div>
          </template>
        </el-table-column>

      </el-table>

      <div slot="footer" class="dialog-footer">
        <el-button type="success" @click="handleAddDetails">添 加</el-button>
        <el-button type="primary" @click="saveOrderProduct">修 改</el-button>
        <el-button @click="dialogOrderListFormVisible = false;orderId=0">返 回</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>
import orderApi from '@/api/order'
import { pcaTextArr } from 'element-china-area-data'

export default {
  data() {
    return {
      total: 0,
      dialogFormVisible: false,
      dialogOrderListFormVisible: false,
      orderForm: {},
      formLabelWidth: '130px',
      title: '',
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      orderList: [],
      orderData: [],
      decimalLength: 2, // 设置保留两位小数
      orderId: 0,
      options: [{
        value: '未开始',
        label: '未开始'
      }, {
        value: '运输中',
        label: '运输中'
      }, {
        value: '未收货',
        label: '未收货'
      }, {
        value: '已完成',
        label: '已完成'
      }],
      pcaTextArr
    }
  },
  created() {
    this.getOrderList()
  },
  methods: {
    handleAddDetails() {
      this.orderData.push({
        orderId: this.orderId,
        giftId: 0,
        giftsEntity: {
          name: '',
          price: 0
        },
        number: 0
      })
    },
    calcResult(number, price) {
      const result = number * price
      return result.toFixed(this.decimalLength)
    },
    openDetailsUI(id) {
      this.orderId = id
      orderApi.getOrderProduct(id).then(response => {
        this.orderData = response.data
      })
      this.dialogOrderListFormVisible = true
    },
    dateFormat(row, column) {
      const moment = require('moment')
      const date = row[column.property]
      return moment(date).format('YYYY - MM - DD')
    },
    deleteOrder(order) {
      this.$confirm(`您确认删除用户 ${order.username} ?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        orderApi.deleteOrderById(order.orderId).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.getOrderList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    openEditUI(id) {
      if (id == null) {
        this.title = '新增订单'
      } else {
        this.title = '修改订单'
        // 根据id查询用户数据
        orderApi.getOrderById(id).then(response => {
          this.orderForm = response.data
        })
      }
      this.dialogFormVisible = true
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getOrderList()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getOrderList()
    },
    getOrderList() {
      orderApi.getOrderList(this.searchModel).then(response => {
        this.orderList = response.data.rows
        this.total = response.data.total
      })
    },
    clearForm() {
      this.orderForm = {}
      this.$refs.orderFormRef.clearValidate()
    },
    saveOrderProduct() {
      orderApi.saveOrderProduct(this.orderData).then(response => {
        this.$message({
          message: response.message,
          type: 'success'
        })
      })
    },
    saveOrder() {
      // 触发表单验证
      this.$refs.orderFormRef.validate((valid) => {
        if (valid) {
          this.orderForm.address = JSON.stringify(this.orderForm.address)
          // 提交请求给后台
          orderApi.saveOrder(this.orderForm).then(response => {
            // 成功提示
            this.$message({
              message: response.message,
              type: 'success'
            })
            // 关闭对话框
            this.dialogFormVisible = false
            // 刷新表格
            this.getOrderList()
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style>
#search .el-input {
  width: 85%;
  margin-right: 10px;
}

.el-dialog .el-input {
  width: 85%;
}
</style>
