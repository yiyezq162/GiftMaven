<template>
  <div>
    <!-- 搜索栏 -->
    <el-card id="search">
      <el-row>
        <el-col :span="20">
          <el-input
            v-model="searchModel.name"
            placeholder="商品名称"
            clearable
          />
          <el-button
            type="primary"
            round
            icon="el-icon-search"
            @click="getGiftsList"
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
      <el-table :data="giftList" stripe style="width: 100%">
        <el-table-column label="#" type="index" width="80" />
        <el-table-column prop="giftId" label="商品id" width="180" />
        <el-table-column prop="name" label="商品名称" width="180" />
        <el-table-column prop="price" label="商品价格" width="180" />
        <el-table-column prop="stock" label="库存" width="180" />
        <el-table-column prop="img" label="商品图片" width="180">
          <template slot-scope="scope">
            <el-popover placement="top-start" title="" trigger="hover">
              <img :src="scope.row.img" alt="" style="width: 300px;height: 300px">
              <img slot="reference" :src="scope.row.img" style="width: 50px;height: 50px">
            </el-popover>
          </template>
        </el-table-column>
        <el-table-column prop="description" label="描述" />
        <el-table-column label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-edit" size="mini" circle @click="openEditUI(scope.row.giftId)" />
            <el-button type="danger" icon="el-icon-delete" size="mini" circle @click="deleteGift(scope.row)" />
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
      <el-form ref="userFormRef" :model="giftForm" :rules="rules">
        <el-form-item
          label="商品名称"
          prop="name"
          :label-width="formLabelWidth"
        >
          <el-input v-model="giftForm.name" autocomplete="off" />
        </el-form-item>
        <el-form-item label="商品价格" prop="price" :label-width="formLabelWidth">
          <el-input v-model="giftForm.price" autocomplete="off" />
        </el-form-item>
        <el-form-item label="商品图片" :label-width="formLabelWidth">
          <el-input v-model="giftForm.img" autocomplete="off" />
        </el-form-item>
        <el-form-item label="商品库存" :label-width="formLabelWidth">
          <el-input v-model="giftForm.stock" autocomplete="off" />
        </el-form-item>
        <el-form-item label="商品描述" :label-width="formLabelWidth" >
          <el-input v-model="giftForm.description" autocomplete="off" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="savaGift">确 定</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>
import giftApi from '@/api/gift'

export default {
  data() {
    return {
      total: 0,
      dialogFormVisible: false,
      giftForm: {},
      formLabelWidth: '130px',
      title: '',
      searchModel: {
        pageNo: 1,
        pageSize: 5
      },
      giftList: []
    }
  },
  created() {
    this.getGiftsList()
  },
  methods: {
    deleteGift(gift) {
      this.$confirm(`您确认删除商品 ${gift.name} ?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        giftApi.deleteGiftById(gift.giftId).then(response => {
          this.$message({
            type: 'success',
            message: response.message
          })
          this.getGiftsList()
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
        this.title = '添加商品'
      } else {
        this.title = '修改商品'
        // 根据id查询用户数据
        giftApi.getGiftById(id).then(response => {
          this.giftForm = response.data
        })
      }
      this.dialogFormVisible = true
    },
    handleSizeChange(pageSize) {
      this.searchModel.pageSize = pageSize
      this.getGiftsList()
    },
    handleCurrentChange(pageNo) {
      this.searchModel.pageNo = pageNo
      this.getGiftsList()
    },
    getGiftsList() {
      giftApi.getGiftsList(this.searchModel).then(response => {
        this.giftList = response.data.rows
        this.total = response.data.total
      })
    },
    clearForm() {
      this.giftForm = {}
      this.$refs.userFormRef.clearValidate()
    },
    savaGift() {
      // 触发表单验证
      this.$refs.userFormRef.validate((valid) => {
        if (valid) {
          // 提交请求给后台
          giftApi.savaGift(this.giftForm).then(response => {
            // 成功提示
            this.$message({
              message: response.message,
              type: 'success'
            })
            // 关闭对话框
            this.dialogFormVisible = false
            // 刷新表格
            this.getGiftsList()
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
