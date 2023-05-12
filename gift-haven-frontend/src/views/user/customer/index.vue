<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <el-input
            v-model="searchModel.name"
            placeholder="顾客姓名"
            clearable
          />
          <el-button
            type="primary"
            round
            icon="el-icon-search"
            @click="getCustomerList"
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
      <el-table :data="customerList" stripe style="width: 100%">
        <el-table-column label="#" type="index" width="80" />
        <el-table-column prop="customerId" label="顾客ID" width="180" />
        <el-table-column prop="name" label="顾客名" width="180" />
        <el-table-column prop="password" label="顾客密码" width="180" />
        <el-table-column prop="avatar" label="顾客头像" width="180">
          <template slot-scope="scope">
            <el-popover placement="top-start" title="" trigger="hover">
              <img :src="scope.row.avatar" alt="" style="width: 150px;height: 150px">
              <img slot="reference" :src="scope.row.avatar" style="width: 30px;height: 30px">
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column prop="phone" label="手机号" width="180" />
        <el-table-column prop="address" label="地址" width="180" />
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" circle @click="openEditUI(scope.row.customerId)" />
            <el-button type="danger" icon="el-icon-delete" size="mini" circle @click="deleteCustomer(scope.row)" />
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
      <el-form ref="userFormRef" :model="customerForm" :rules="rules">
        <el-form-item
          label="用户名"
          prop="name"
          :label-width="formLabelWidth"
        >
          <el-input v-model="customerForm.name" autocomplete="off" />
        </el-form-item>
        <el-form-item
          v-if="customerForm.id == null || customerForm.id == undefined"
          label="登录密码"
          prop="password"
          :label-width="formLabelWidth"
        >
          <el-input
            v-model="customerForm.password"
            type="password"
            autocomplete="off"
          />
        </el-form-item>
        <el-form-item label="头像路径" :label-width="formLabelWidth">
          <el-input v-model="customerForm.avatar" autocomplete="off" />
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input v-model="customerForm.phone" autocomplete="off" />
        </el-form-item>
        <el-form-item label="地址" :label-width="formLabelWidth">
          <el-input v-model="customerForm.address" autocomplete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveCustomer">确 定</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>
import customerApi from '@/api/customer'
export default {
  data() {
    return {
      total: 0,
      dialogFormVisible: false,
      customerForm: {},
      formLabelWidth: '130px',
      title: '',
      searchModel: {
        pageNo: 1,
        pageSize: 10
      },
      customerList: []
    }
  },
  created() {
    this.getCustomerList()
  },
  methods: {
    deleteCustomer(user) {
      this.$confirm(`您确认删除用户 ${user.username} ?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        customerApi.deleteCustomerById(user.customerId).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.getCustomerList()
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
        this.title = '新增顾客'
      } else {
        this.title = '修改顾客'
        // 根据id查询用户数据
        customerApi.getCustomerById(id).then(response => {
          this.customerForm = response.data
        })
      }
      this.dialogFormVisible = true
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getCustomerList()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getCustomerList()
    },
    getCustomerList() {
      customerApi.getCustomerList(this.searchModel).then(response => {
        this.customerList = response.data.rows
        this.total = response.data.total
      })
    },
    clearForm() {
      this.customerForm = {}
      this.$refs.userFormRef.clearValidate()
    },
    saveCustomer() {
      // 触发表单验证
      this.$refs.userFormRef.validate((valid) => {
        if (valid) {
          // 提交请求给后台
          customerApi.saveCustomer(this.customerForm).then(response => {
            // 成功提示
            this.$message({
              message: response.message,
              type: 'success'
            })
            // 关闭对话框
            this.dialogFormVisible = false
            // 刷新表格
            this.getCustomerList()
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
